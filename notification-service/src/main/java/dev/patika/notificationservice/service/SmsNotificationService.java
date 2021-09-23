package dev.patika.notificationservice.service;

import dev.patika.notificationservice.model.SmsNotification;
import dev.patika.notificationservice.repository.SmsNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsNotificationService implements  BaseNotificationService<SmsNotification>{

    private final SmsNotificationRepository smsNotificationRepository;

    Logger logger = LoggerFactory.getLogger(SmsNotificationService.class);


    @Override
    public String send(String nationalId, String address, String messageBody) {
        return null;
    }
}
