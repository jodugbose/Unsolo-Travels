package com.interswitch.Unsolorockets.dtos.responses;

import lombok.Data;

@Data
public class KYCResponse {
    private String status;
    private boolean isVerified;

}
