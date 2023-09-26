package com.interswitch.Unsolorockets.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.joda.time.LocalDateTime;

@Entity
@Table(name = "kyc")
@Data
@Builder
public class Kyc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String nin;
    private String status;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
