package com.desilva;


import com.desilva.file.FileReader;
import com.desilva.file.FileReaderImp;
import com.desilva.record.RecordCollectorImpl;
import com.desilva.validator.ApplicationInputValidator;
import com.desilva.validator.ApplicationInputValidatorImp;
import com.desilva.validator.ValidatorResult;

import static java.lang.System.err;

/**
 * Created by stevedesilva
 */
public class Application {

    public static void main(String args[]) {
        runJobWrapper(args);
    }

    private static void runJobWrapper(String[] args) {
        final FileReader fileReader = new FileReaderImp();
        final ApplicationInputValidator validator = new ApplicationInputValidatorImp(fileReader);
        final ValidatorResult result = validator.isInputValid(args);

        if (!result.isValid()) {
            err.println(validator.getErrorMessage(result.getMessage()));
            System.exit(0);
        } else {
            final QuestionWorker jobToRun = new QuestionWorkerImp(fileReader, new RecordCollectorImpl());
            jobToRun.askQuestions(args[0]);
        }

    }

}
