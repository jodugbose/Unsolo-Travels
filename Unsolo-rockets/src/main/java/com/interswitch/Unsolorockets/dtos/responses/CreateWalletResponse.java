package com.interswitch.Unsolorockets.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateWalletResponse {

private String mac;
private LocalDateTime transactionDate;
private String transferCode;
private String pin;
private String responseCode;
private String responseCodeGrouping;

}
