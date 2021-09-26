package dev.patika.cloudgateway.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FallBackController {

    @GetMapping("/customerServiceFallBack")
    public String customerServiceFallBackMethod() {
        return "Customer service is taking longer than expected. Please try again ";
    }

    @GetMapping("/notificationServiceFallback")
    public String notificationServiceFallBackMethod() {
        return "Notification service is taking longer than expected. Please try again";
    }
}
