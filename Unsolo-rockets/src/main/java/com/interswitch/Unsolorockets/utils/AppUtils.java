package com.interswitch.Unsolorockets.utils;

import com.interswitch.Unsolorockets.exceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class AppUtils {

    public Long generateOTP(){
        Random rnd = new Random();
        Long number = (long) rnd.nextInt(999999);
        return  number;
    }

    public boolean validEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public LocalDate createLocalDate(String day, String month, String year) throws UserException {
        if(day != null && month != null && year != null) {
            String date = day + "-" + month + "-" + year;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(date, formatter);
        }
        else {
            throw new UserException("date can not be null", HttpStatus.BAD_REQUEST);
        }

    }
}
