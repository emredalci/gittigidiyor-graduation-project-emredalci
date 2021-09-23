package dev.patika.notificationservice.service;

import dev.patika.notificationservice.model.SmsNotification;
import dev.patika.notificationservice.repository.SmsNotificationRepository;
import dev.patika.notificationservice.util.NotificationMessage;
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
        SmsNotification smsNotification=SmsNotification.builder().nationalId(nationalId).phoneNumber(address).body(messageBody).build();
        smsNotificationRepository.save(smsNotification);
        logger.info("Notification Service process is done successfully. Adress : " +smsNotification.getPhoneNumber());
        return NotificationMessage.NOTIFICATION_IS_SUCCESS;
    }
}
