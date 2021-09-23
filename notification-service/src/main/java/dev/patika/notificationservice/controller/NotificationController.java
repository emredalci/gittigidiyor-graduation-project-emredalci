package dev.patika.notificationservice.controller;

import dev.patika.notificationservice.service.SmsNotificationService;
import dev.patika.notificationservice.util.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private final SmsNotificationService smsNotificationService;

    @GetMapping("/{nationalId}/{phoneNumber}")
    public ResponseEntity<String> sendSms(@PathVariable String nationalId, @PathVariable String phoneNumber){
        smsNotificationService.send(nationalId,phoneNumber,NotificationMessage.SEND_SMS_NOTIFICATION_BODY);
        return ResponseEntity.ok(NotificationMessage.NOTIFICATION_IS_SUCCESS);
    }
}
