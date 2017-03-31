package com.desilva.response;

/**
 * Created by stevedesilva on 31/03/2017.
 */



import com.desilva.record.DayRangeRecordCustomCollector;
import com.desilva.record.Record;

import java.util.*;

public class QuestionsImpl implements Questions   {
    public static final String RESPONSE_FORMAT = "= %s";
    public static final String NO_NAME_FOUND = "NO NAME FOUND";
    final private List<Record> records;

    public QuestionsImpl(List<Record> records) {
        this.records = records;
    }

    @Override
    public List<QuestionResponse> getQuestions() {
        return getQuestionResponses(records);
    }

    private List<QuestionResponse> getQuestionResponses(List<Record> records) {
        List<QuestionResponse> questions = new ArrayList<>();
        questions.add(questionOne(records));
        questions.add(questionTwo(records));
        questions.add(questionThree(records));
        return questions;
    }

    private QuestionResponse questionOne(List<Record> records) {
        QuestionResponse response = new QuestionResponse("(1) How many males are in the address book?");
        long result = records.stream().filter(rec -> Record.Gender.MALE.equals(rec.getGender())).count();
        setResponseMessage(response, result);
        return response;
    }

    private void setResponseMessage(QuestionResponse response, long result) {
        response.setAnswer(String.format(RESPONSE_FORMAT, result));
    }

    private QuestionResponse questionTwo(List<Record> records) {
        QuestionResponse response = new QuestionResponse("(2) Who is the oldest person in the address book?");
        Comparator<Record> cmp = Comparator.comparing(Record::getBirth);

        Optional<Record> recordOptional = Optional.of(Collections.min(records, cmp));
        String name;
        if(recordOptional.isPresent()){
            name = recordOptional.get().getName();
            response.setAnswer(String.format(RESPONSE_FORMAT, name));
        }
        else {
            name = NO_NAME_FOUND;
        }
        response.setAnswer(String.format(RESPONSE_FORMAT, name));
        return response;
    }

    private QuestionResponse questionThree(List<Record> records) {
        QuestionResponse response = new QuestionResponse("(3) How many days older is Bill than Paul?");
        Long result = records.stream().collect(new DayRangeRecordCustomCollector("Paul Robinson", "Bill McKnight"));
        setResponseMessage(response, result);
        return response;
    }


}
