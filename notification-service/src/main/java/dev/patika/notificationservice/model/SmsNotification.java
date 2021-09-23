package dev.patika.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsNotification extends AbstractBaseEntity{

    @Column(nullable = false)
    private String nationalId;

    @Column(nullable = false)
    private String phoneNumber;


    @Column(nullable = false)
    private String body;
}
