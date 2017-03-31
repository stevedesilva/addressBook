package com.desilva.validator;


import com.desilva.file.FileReader;

/**
 * Created by stevedesilva
 */
public class ApplicationInputValidatorImp implements ApplicationInputValidator {

    private FileReader fileReader;

    public ApplicationInputValidatorImp(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public ValidatorResult isInputValid(String[] args) {
        if (args.length != 1) {
            return new ValidatorResult("One string argument should be supplied.", false);
        }


        if (!fileReader.fileValidator(args[0])) {
            return new ValidatorResult("File does not existing.", false);
        }

        return new ValidatorResult("Supplied argument is valid.", true);
    }

    @Override
    public String getErrorMessage(String errorMessage) {
        return String.format("Error found running application : %s Correct usage : java -jar addressBook.jar <address file>", errorMessage);
    }

}
