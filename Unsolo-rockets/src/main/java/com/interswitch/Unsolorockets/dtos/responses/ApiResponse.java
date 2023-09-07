package com.interswitch.Unsolorockets.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ApiResponse {
    private boolean success;
    private Object data;
//    private int statusCode;
}
