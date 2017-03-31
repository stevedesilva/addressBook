package com.desilva.validator;

/**
 * Created by stevedesilva
 */
public class ValidatorResult {

    final private String message;
    final private boolean valid;

    public ValidatorResult(String message, boolean valid) {
        this.message = message;
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        return valid;
    }
}
