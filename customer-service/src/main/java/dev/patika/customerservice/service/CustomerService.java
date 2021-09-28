package dev.patika.customerservice.service;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerLoggerResponseDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.dto.CustomerUpdateDTO;
import dev.patika.customerservice.exception.CustomerIsAlreadyExistException;
import dev.patika.customerservice.exception.NotFoundCustomerException;
import dev.patika.customerservice.mapper.CustomerLoggerMapper;
import dev.patika.customerservice.mapper.CustomerMapper;
import dev.patika.customerservice.model.CreditResult;
import dev.patika.customerservice.model.Customer;
import dev.patika.customerservice.model.CustomerLogger;
import dev.patika.customerservice.model.enumeration.Status;
import dev.patika.customerservice.repository.CreditResultRepository;
import dev.patika.customerservice.repository.CreditScoreRepository;
import dev.patika.customerservice.repository.CustomerLoggerRepository;
import dev.patika.customerservice.repository.CustomerRepository;
import dev.patika.customerservice.util.CalculateCreditResult;
import dev.patika.customerservice.util.CreateCustomerLogger;
import dev.patika.customerservice.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService implements BaseService<CustomerService>{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final RestTemplate restTemplate;
    private final CreditScoreRepository creditScoreRepository;
    private final CreditResultRepository creditResultRepository;
    private final CustomerLoggerRepository customerLoggerRepository;
    private final CustomerLoggerMapper customerLoggerMapper;

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    /** The method implements the customer save operation. If the customer already exist in the database, the method
     * throws an exception(CustomerIsAlreadyExistException). If no customer exist in the database, the saving process
     * will be done successfully on the condition that fields are correct.
     *
     * @param customerDTO customer data transfer object. Created to not ask for unnecessary information from
     *                    the client
     * @return CustomerResponseDTO. customer response data transfer object.
     *                    Created to be able to hide some fields from client
     */
    public CustomerResponseDTO saveCustomer(CustomerDTO customerDTO){
        logger.info("Customer Service saving process is started");
        boolean isExist = customerRepository.existsByNationalId(customerDTO.getNationalId());
        if(isExist){
            logger.error("Customer service saving process error : "+ ErrorMessage.CUSTOMER_IS_ALREADY_EXIST);
            throw new CustomerIsAlreadyExistException(ErrorMessage.CUSTOMER_IS_ALREADY_EXIST);
        }
        Customer customer=customerMapper.mapFromCustomerDTOtoCustomer(customerDTO);
        customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = customerMapper.mapFromCustomertoCustomerResponseDTO(customer);
        logger.info("Customer Service saving process is done successfully : " +customerResponseDTO.toString());
        return  customerResponseDTO;

    }

    /** The method implements the customer update processs. The nationalId field is necessary to find customers
     * from database. The registered customer's national id can not be changed. So, the national id field does not
     * exist in customerUpdateDTO.
     *  If the customer does not exist in the database the method throws an exception(NotFoundCustomerException).
     *  If all conditions are correct, the update process will be done successfully.
     *
     * @param customerUpdateDTO customer data transfer obejct for update process.
     *                          Created to not ask for unnecessary information from the client
     * @param nationalId national id. This field is necessary to get customer from database
     * @return CustomerResponseDTO customer response data transfer object.
                                  Created to be able to hide some fields from client
     */

    public CustomerResponseDTO updateCustomer(CustomerUpdateDTO customerUpdateDTO, String nationalId){
        logger.info("Customer Service update customer process is started");

        Optional<Customer> customer = customerRepository.findByNationalId(nationalId);
        if (!customer.isPresent()){
            logger.error("Customer Service update customer process error" +ErrorMessage.CUSTOMER_IS_NOT_FOUND);
            throw new NotFoundCustomerException(ErrorMessage.CUSTOMER_IS_NOT_FOUND);
        }
        CustomerLogger customerLogger = CreateCustomerLogger.createCustomerLogger(customerUpdateDTO,customer.get());
        customerLoggerRepository.save(customerLogger);
        CustomerMapper.INSTANCE.mapFromCustomerUpdateDTOtoCustomer(customerUpdateDTO,customer.get());
        customer.get().setLastModifiedDate(Instant.now());
        customerRepository.save(customer.get());
        CustomerResponseDTO customerResponseDTO = customerMapper.mapFromCustomertoCustomerResponseDTO(customer.get());
        logger.info("Customer Service update customer process is done successfully");
        return customerResponseDTO;

    }

    /** The method implements the customer delete processs. If the customer does not exist in the database,
     * the method throw an exception(NotFoundCustomerException).
     * If the customer exists in the database, the delete process will be done successfully.
     *
     * @param nationalId national id. This field is necessary to delete customer from database
     * @return CustomerResponseDTO. customer response data transfer object.
     *                            Created to be able to hide some fields from client
     */

    public CustomerResponseDTO deleteCustomer(String nationalId){
        logger.info("Customer Service delete customer process is started");
        Optional<Customer> foundCustomer=customerRepository.findByNationalId(nationalId);
        if(!foundCustomer.isPresent()){
            logger.error("Customer Service delete customer process error : " +ErrorMessage.CUSTOMER_IS_NOT_FOUND);
            throw new NotFoundCustomerException(ErrorMessage.CUSTOMER_IS_NOT_FOUND);
        }
        CustomerResponseDTO customerResponseDTO = customerMapper.mapFromCustomertoCustomerResponseDTO(foundCustomer.get());
        customerRepository.deleteByNationalId(nationalId);
        logger.info("Customer Service delete customer process is done successfully");
        return customerResponseDTO;
    }

    /** The method implements apply loan processs. If the customer does not exist in the database,
     * the method throw an exception(NotFoundCustomerException).
     * If the credit result is rejected, the notification service call will not works. But the result will be save
     * in database
     * If the credit result is accepted, the notification service call will works.
     * @param nationalId national id. This field is necessary to apply for loan.
     * @return String. The method returns CreditResult entity as string.
     */

    public String applyForLoan(String nationalId){
        logger.info("Customer Service credit card application process is started");
        Optional<Customer> customer = customerRepository.findByNationalId(nationalId);
        if (!customer.isPresent()){
            logger.error("Customer service applying for loan process error : "+ErrorMessage.CUSTOMER_IS_NOT_FOUND);
            throw new NotFoundCustomerException(ErrorMessage.CUSTOMER_IS_NOT_FOUND);
        }
        char lastNumber = nationalId.charAt(10);
        double score = creditScoreRepository.findCreditScore(lastNumber);
        CreditResult creditResult= CalculateCreditResult.calculate(score,customer.get());
        creditResultRepository.save(creditResult);
        if(creditResult.getStatus().equals(Status.ACCEPT)){
            logger.info("Notification service is calling");
            ResponseEntity<String> smsNotification =
                    restTemplate.getForEntity("http://NOTIFICATION-SERVICE/notification/"+nationalId+"/"+customer.get().getPhoneNumber(),String.class);
            logger.info(smsNotification.getBody());
        }
        logger.info("Customer Service credit card application process is done successfully");
        return creditResult.toString();

    }

    /** The method implements the customer find processs.
     * If customer does not exist, the method return empty list.
     *
     * @return CustomerResponseDTO list.
     */
    public List<CustomerResponseDTO> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::mapFromCustomertoCustomerResponseDTO)
                .collect(Collectors.toList());
    }

    /** The method implements the customer looger find processs by national id.
     * If customer does not exist, the method return empty list
     *
     * @param nationalId
     * @return CustomerLoggerResponseDTO list.
     */
    public List<CustomerLoggerResponseDTO> getAllCustomerLoggersByNationalId(String nationalId){
        return customerLoggerRepository.findAllByNationalId(nationalId)
                .stream()
                .map(customerLoggerMapper::mapFromCustomerLoggertoCustomerLoggerResponseDTO)
                .collect(Collectors.toList());
    }



}
