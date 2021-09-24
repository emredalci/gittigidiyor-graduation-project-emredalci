package dev.patika.notificationservice.controller;

import dev.patika.notificationservice.service.SmsNotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Mock
    SmsNotificationService smsNotificationService;

    @InjectMocks
    NotificationController notificationController;

    @Test
    void should_ReturnResponseEntityString_When_SendSms() {
        //given
        String expected = "Notification is sended";
        when(smsNotificationService.send(anyString(),anyString(),anyString())).thenReturn(expected);
        //when
        String nationalId = "13758028554";
        String phoneNumber = "05395064707";
        ResponseEntity<String> actual=notificationController.sendSms(nationalId,nationalId);
        //then
        assertAll(
                () -> assertNotNull(actual.getBody())
        );
    }
}