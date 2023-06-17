package fivvy.backend_challenge.exception;

import fivvy.backend_challenge.configuration.MessageSourceConfigurer;

/**
 * Exception for when a Disclaimer is not found in the database
 */
public class DisclaimerNotFoundException extends Exception {

    public DisclaimerNotFoundException() {
        super(new MessageSourceConfigurer().getMessage("error_disclaimer_not_found"));
    }
}
