package dev.patika.cloudgateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FallBackController {

    @GetMapping("/customerServiceFallBack")
    public ResponseEntity<String> customerServiceFallBackMethod() {
        return new ResponseEntity<>("Customer service is taking longer than expected. Please try again ", HttpStatus.GATEWAY_TIMEOUT);
    }

    @GetMapping("/notificationServiceFallback")
    public ResponseEntity<String> notificationServiceFallBackMethod() {
        return new ResponseEntity<>("Notification service is taking longer than expected. Please try again",HttpStatus.GATEWAY_TIMEOUT);
    }
}
