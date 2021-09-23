package dev.patika.notificationservice.service;

public interface BaseNotificationService <T>{

    String send(String nationalId,String address,String messageBody);
}
