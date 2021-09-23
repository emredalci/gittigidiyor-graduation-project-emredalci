package dev.patika.customerservice.exception;

public class CreditResultNotCreatedException extends RuntimeException{
    public CreditResultNotCreatedException(String message){
        super(message);
    }
}
