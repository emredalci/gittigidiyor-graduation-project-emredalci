package dev.patika.customerservice.util;

import dev.patika.customerservice.dto.CustomerUpdateDTO;
import dev.patika.customerservice.model.Customer;
import dev.patika.customerservice.model.CustomerLogger;



public class CreateCustomerLogger {


    public static CustomerLogger createCustomerLogger(CustomerUpdateDTO customerUpdateDTO, Customer customer){
        CustomerLogger customerLogger = new CustomerLogger();
        customerLogger.setFirstNameBefore(customer.getFirstName());
        customerLogger.setFirstNameAfter(customerUpdateDTO.getFirstName());
        customerLogger.setLastNameBefore(customer.getLastName());
        customerLogger.setLastNameAfter(customerUpdateDTO.getLastName());
        customerLogger.setIncomeBefore(customer.getIncome());
        customerLogger.setIncomeAfter(customerUpdateDTO.getIncome());
        customerLogger.setPhoneNumberBefore(customer.getPhoneNumber());
        customerLogger.setPhoneNumberAfter(customerUpdateDTO.getPhoneNumber());
        customerLogger.setNationalId(customer.getNationalId());
        return customerLogger;
    }
}
