package dev.patika.customerservice.exception;

import com.fasterxml.jackson.core.JsonParseException;
import dev.patika.customerservice.model.ErrorResponse;
import dev.patika.customerservice.model.ErrorResponseValidation;
import dev.patika.customerservice.repository.ErrorResponseRepository;
import dev.patika.customerservice.repository.ErrorResponseValidationRepository;
import dev.patika.customerservice.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorResponseRepository errorResponseRepository;
    private final ErrorResponseValidationRepository errorResponseValidationRepository;

    @ExceptionHandler({CustomerIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(CustomerIsAlreadyExistException exception){
        ErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundCustomerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(NotFoundCustomerException exception){
        ErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CreditResultNotCreatedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(CreditResultNotCreatedException exception){
        ErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorResponse> handleException(HttpRequestMethodNotSupportedException exception){
        ErrorResponse response = prepareErrorResponse(HttpStatus.METHOD_NOT_ALLOWED,exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({ConnectException.class})
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public ResponseEntity<ErrorResponse> handleException(ConnectException exception){
        ErrorResponse response = prepareErrorResponse(HttpStatus.GATEWAY_TIMEOUT,exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.GATEWAY_TIMEOUT);
    }



    @ExceptionHandler({IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(IllegalStateException exception){
        ErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exception.getMessage());
        return new ResponseEntity<>(response ,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({JsonParseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(JsonParseException exception){
        ErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, ErrorMessage.JSON_PARSE_ERROR);
        return new ResponseEntity<>(response ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ErrorResponseValidation>> handleException(MethodArgumentNotValidException exception){
        List<ErrorResponseValidation> errorResponseValidationList = new ArrayList<>();
        for (FieldError fieldError:exception.getFieldErrors()){
            ErrorResponseValidation responseValidation= prepareErrorResponseValidation(HttpStatus.BAD_REQUEST, fieldError.getField(), fieldError.getDefaultMessage());
            errorResponseValidationRepository.save(responseValidation);
            errorResponseValidationList.add(responseValidation);
        }

        return new ResponseEntity<>(errorResponseValidationList,HttpStatus.BAD_REQUEST);
    }



    private ErrorResponse prepareErrorResponse(HttpStatus httpStatus, String message) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(httpStatus.value());
        response.setMessage(message);
        errorResponseRepository.save(response);
        return response;
    }

    private ErrorResponseValidation prepareErrorResponseValidation(HttpStatus httpStatus,String field,String rule){
        ErrorResponseValidation responseValidation = new ErrorResponseValidation();
        responseValidation.setStatus(httpStatus.value());
        responseValidation.setField(field);
        responseValidation.setRule(rule);
        errorResponseValidationRepository.save(responseValidation);
        return responseValidation;
    }
}
