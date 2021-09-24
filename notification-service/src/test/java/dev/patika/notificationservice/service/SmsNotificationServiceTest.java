package dev.patika.notificationservice.service;

import dev.patika.notificationservice.model.SmsNotification;
import dev.patika.notificationservice.repository.SmsNotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SmsNotificationServiceTest {
    @Mock
    SmsNotificationRepository smsNotificationRepository;

    @InjectMocks
    SmsNotificationService smsNotificationService;

    @Test
    void should_ReturnString_When_Send() {
        //when
        SmsNotification smsNotification=SmsNotification.builder().nationalId("13758028554").phoneNumber("05395064707").body("Sended").build();
        when(smsNotificationRepository.save(any())).thenReturn(smsNotification);
        //given
        String nationalId = "13758028554";
        String address = "05395064707";
        String messageBody = "Sended";
        String actual=smsNotificationService.send(nationalId,address,messageBody);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertNotNull(smsNotification)
        );

    }


}