package com.desilva.validator;

import com.desilva.file.FileReaderImp;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by stevedesilva on 29/03/2017.
 */
public class ApplicationInputValidatorImpTest {


    final private ApplicationInputValidator applicationInputValidator = new ApplicationInputValidatorImp(new FileReaderImp());

    @Test
    public void applicationInputValidator_WhenConstructed_ShouldNotBeNull() {
        assertNotNull(new ApplicationInputValidatorImp(new FileReaderImp()));
    }

    @Test
    public void isInputValid_WhenGivenValidStringArg_ShouldReturnTrueValidatorResult() {
        final String addressBookFileName = getAddressBookFileName();

        String[] validStringArray = new String[]{addressBookFileName};
        ValidatorResult result = applicationInputValidator.isInputValid(validStringArray);

        assertTrue(result.isValid());
        assertEquals("Supplied argument is valid.",result.getMessage());
    }


    @Test
    public void isInputValid_WhenGivenMoreThanOneArg_ShouldReturnFalseValidatorResult() {
        String[] moreThanOneArgStringArray = new String[]{"someValidArgString","anotherArgString"};
        ValidatorResult result = applicationInputValidator.isInputValid(moreThanOneArgStringArray);
        assertFalse(result.isValid());
        assertEquals("One string argument should be supplied.",result.getMessage());

    }

    @Test
    public void isInputValid_WhenGivenNoArg_ShouldReturnFalseValidatorResult() {
        String[] emptyArgArray = new String[]{};
        ValidatorResult result = applicationInputValidator.isInputValid(emptyArgArray);
        assertFalse(result.isValid());
        assertEquals("One string argument should be supplied.",result.getMessage());

    }

    @Test
    public void isInputValid_WhenGivenInvalidFormat_ShouldReturnFalseValidatorResult() {
        String[] emptyArgArray = new String[]{"@@@EE$%T"};
        ValidatorResult result = applicationInputValidator.isInputValid(emptyArgArray);
        assertFalse(result.isValid());
        assertEquals("File does not existing.",result.getMessage());

    }


    @Test
    public void getErrorMessage_WhenGivenMoreThanOneArgError_ShouldReturnUpdatedErrorMessage() {
        String[] moreThanOneArgStringArray = new String[]{"someValidArgString","anotherArgString"};
        ValidatorResult result = applicationInputValidator.isInputValid(moreThanOneArgStringArray);
        String errorMessage = applicationInputValidator.getErrorMessage(result.getMessage());
        assertEquals("Error found running application : One string argument should be supplied. Correct usage : java -jar addressBook.jar <address file>",errorMessage);
    };


    @Test
    public void getErrorMessage_WhenGivenNoArgError_ShouldReturnUpdatedErrorMessage() {
        String[] emptyArgArray = new String[]{};
        ValidatorResult result = applicationInputValidator.isInputValid(emptyArgArray);
        String errorMessage = applicationInputValidator.getErrorMessage(result.getMessage());
        assertEquals("Error found running application : One string argument should be supplied. Correct usage : java -jar addressBook.jar <address file>",errorMessage);
    };


    @Test
    public void getErrorMessage_WhenGivenInvalidArgsError_ShouldReturnUpdatedErrorMessage() {
        String[] invalidArgArray = new String[]{"@@@EE$%T"};
        ValidatorResult result = applicationInputValidator.isInputValid(invalidArgArray);
        String errorMessage = applicationInputValidator.getErrorMessage(result.getMessage());
        assertEquals("Error found running application : File does not existing. Correct usage : java -jar addressBook.jar <address file>",errorMessage);
    };




    // TODO move into test helper
    private String getAddressBookFileName() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("AddressBook").getFile());
        return file.getAbsolutePath();
    }



}