package dev.patika.customerservice.service;

import dev.patika.customerservice.dto.CreditResultResponseDTO;
import dev.patika.customerservice.mapper.CreditResultMapper;
import dev.patika.customerservice.repository.CreditResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CreditResultService implements BaseService<CreditResultService>{

    private final CreditResultRepository creditResultRepository;
    private final CreditResultMapper creditResultMapper;

    public List<CreditResultResponseDTO> findByNationalId(String nationalId){
        return creditResultRepository.findAllByCustomerNationalId(nationalId)
                .stream().map(creditResultMapper::mapFromCreditResulttoCreditResultResponseDTO)
                .collect(Collectors.toList());
    }

}
