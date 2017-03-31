package com.desilva.validator;

/**
 * Created by stevedesilva
 */
public interface ApplicationInputValidator {

    ValidatorResult isInputValid(String[] args);

    String getErrorMessage(String errorMessage);
}
