package com.rocky.util;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;


public class CommonUtil {

    public static Boolean isValidEmail(String email){
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }
}
