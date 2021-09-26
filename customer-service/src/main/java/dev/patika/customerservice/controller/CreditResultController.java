package dev.patika.customerservice.controller;


import dev.patika.customerservice.dto.CreditResultResponseDTO;
import dev.patika.customerservice.service.CreditResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/result")
@CrossOrigin
public class CreditResultController {

    private final CreditResultService creditResultService;

    @GetMapping("/{nationalId}")
    public ResponseEntity<List<CreditResultResponseDTO>> findCreditResult(@PathVariable String nationalId){
        return ResponseEntity.ok(creditResultService.findByNationalId(nationalId));
    }
}
