package dev.patika.customerservice.exception;

import dev.patika.customerservice.model.ErrorResponse;
import dev.patika.customerservice.repository.ErrorResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorResponseRepository errorResponseRepository;
    @ExceptionHandler({CustomerIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(CustomerIsAlreadyExistException exception){
        ErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse prepareErrorResponse(HttpStatus httpStatus, String message) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(httpStatus.value());
        response.setMessage(message);
        errorResponseRepository.save(response);
        return response;
    }
}
