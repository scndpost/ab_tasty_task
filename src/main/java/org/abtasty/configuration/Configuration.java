package org.abtasty.configuration;

import org.aeonbits.owner.Config;

@Config.Sources({"file:.env"})
public interface Configuration extends Config {

    @Key("BASE_ABTASTY_URI")
    String baseAbTastyUri();
    @Key("USER_EMAIL")
    String userEmail();
    @Key("USER_PASSWORD")
    String userPassword();
    @Key("USER_PHONE_NUMBER")
    String userPhoneNumber();

}
