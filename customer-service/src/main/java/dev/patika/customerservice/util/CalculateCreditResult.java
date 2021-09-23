package dev.patika.customerservice.util;

import dev.patika.customerservice.model.CreditResult;
import dev.patika.customerservice.model.Customer;
import dev.patika.customerservice.model.enumeration.Status;

public class CalculateCreditResult {
    public static CreditResult calculate(double score, Customer customer){
        double income = customer.getIncome();
        String nationalId = customer.getNationalId();
        if(score <500){
            return CreditResult.builder().status(Status.REJECT).creditLimit(0).customerNationalId(nationalId).build();
        }else if (score >500 && score<1000 && income <5000){
            return CreditResult.builder().status(Status.ACCEPT).creditLimit(10000).customerNationalId(nationalId).build();
        }else if(score >500 && score<1000 && income >= 5000){
            return CreditResult.builder().status(Status.ACCEPT).creditLimit(20000).customerNationalId(nationalId).build();
        }else if(score >=1000){
            return CreditResult.builder().status(Status.ACCEPT).creditLimit(income*4).customerNationalId(nationalId).build();
        }else{
            throw new RuntimeException(ErrorMessage.CREDIT_RESULT_NOT_CREATED);
        }


    }
}
