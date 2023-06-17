package fivvy.backend_challenge.exception;

import fivvy.backend_challenge.configuration.MessageSourceConfigurer;

/**
 * Exception for when no id is provided
 */
public class IdMissingException extends Exception {
        public IdMissingException() {
            super(new MessageSourceConfigurer().getMessage("error_missing_id"));
        }
}
