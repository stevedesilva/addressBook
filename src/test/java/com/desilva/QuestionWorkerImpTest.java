package com.desilva;

import com.desilva.file.FileReaderImp;
import com.desilva.record.RecordCollectorImpl;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by stevedesilva on 30/03/2017.
 */
public class QuestionWorkerImpTest {

    @Test
    public void askQuestions_WhenCreated_ShouldNotBeNull() {
        QuestionWorker questionWorker = new QuestionWorkerImp(new FileReaderImp(), new RecordCollectorImpl());
        assertNotNull(questionWorker);
    }


}