package com.interswitch.Unsolorockets.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ApiResponse {
    private boolean success;
    private Object data;
//    private int statusCode;
}
