package dev.patika.customerservice.exception;

public class CustomerIsAlreadyExistException extends RuntimeException{
    public CustomerIsAlreadyExistException(String message){
        super(message);
    }
}
