package com.interswitch.Unsolorockets.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "wallet_tbl")
public class Wallet extends Base{
    private String bankName;
    private String accountNumber;
    private BigDecimal balance;
    private String virtualAccountRef;
    @Column(nullable = false)
    private Long userId;




}
