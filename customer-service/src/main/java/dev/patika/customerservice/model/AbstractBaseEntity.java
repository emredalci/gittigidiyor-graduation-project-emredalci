package dev.patika.customerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    @JsonIgnore
    @Column(nullable = false,updatable = false)
    private Instant createdDate = Instant.now();

    @LastModifiedDate
    @Column(nullable = false)
    private Instant lastModifiedDate = Instant.now();
}
