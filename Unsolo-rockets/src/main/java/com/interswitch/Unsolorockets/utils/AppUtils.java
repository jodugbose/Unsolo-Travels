package com.interswitch.Unsolorockets.utils;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AppUtils {
    public Long generateOTP(){
        Random rnd = new Random();
        Long number = (long) rnd.nextInt(999999);
        return  number;
    }
}
