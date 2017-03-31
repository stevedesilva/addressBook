package com.desilva;


import com.desilva.file.FileReader;
import com.desilva.record.Record;
import com.desilva.record.RecordCollector;
import com.desilva.response.QuestionResponse;
import com.desilva.response.Questions;
import com.desilva.response.QuestionsImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.System.err;

/**
 * Created by stevedesilva.
 */
public class QuestionWorkerImp implements QuestionWorker {

    private FileReader fileReader;
    private RecordCollector recordCollector;


    public QuestionWorkerImp(FileReader fileReader, RecordCollector recordCollector) {
        this.fileReader = fileReader;
        this.recordCollector = recordCollector;
    }

    public void askQuestions(String file) {

        Optional<Stream<String>>  fileReaderResult = fileReader.fileToStream(file);
        if (fileReaderResult.isPresent()) {
            Optional<List<Record>> recordCollectorResult = recordCollector.streamToRecordMapper(fileReaderResult.get());
            if (recordCollectorResult.isPresent()) {
                displayQuestionResponse(recordCollectorResult);
            } else {
                err.println("Unable to convert csv file to records.");
                System.exit(0);
            }
        }
        else {
            err.println("Unable to convert csv file to records. Exiting application ");
            System.exit(0);
        }

    }

    private void displayQuestionResponse(Optional<List<Record>>  recordCollectorResult) {
        if(recordCollectorResult.isPresent()){
            Questions questions = new QuestionsImpl(recordCollectorResult.get());
            final List<QuestionResponse> results = questions.getQuestions();
            results.stream().forEach(System.out::println);
        }
        else {
            err.println(String.format("No questions present. Exiting application "));
            System.exit(0);
        }
    }

}
