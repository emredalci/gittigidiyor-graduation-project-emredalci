package dev.patika.notificationservice.repository;

import dev.patika.notificationservice.model.SmsNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsNotificationRepository extends JpaRepository<SmsNotification,Long> {
}
