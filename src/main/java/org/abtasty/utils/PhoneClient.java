package org.abtasty.utils;

import lombok.experimental.UtilityClass;
import net.bytebuddy.utility.RandomString;

public class PhoneClient {

    private String phoneNumber;

    public PhoneClient(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String performMagicAndGetPhoneMfaCodeHere() {
        // Do magic for phoneNumber
        return "123456";
    }
}
