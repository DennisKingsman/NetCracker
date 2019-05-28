package com.example.auth.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordUtil {

    private static final Logger logger = LoggerFactory.getLogger(PasswordUtil.class);

    public boolean checkPassword(String password, String doublePassword){
        if (password.length() != doublePassword.length()){
            logger.info(" " + password.length());
            logger.info(" " + doublePassword.length());
            return false;
        }


        for(int i = 0; i < password.length(); ++i){
            if(password.charAt(i) != doublePassword.charAt(i)){
                logger.info("Error at de char");
                logger.info(" " + password.charAt(i));
                logger.info(" " + doublePassword.charAt(i));
                return false;
            }
        }

        return true;
    }
}
