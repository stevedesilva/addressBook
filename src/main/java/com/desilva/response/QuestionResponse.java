package com.desilva.response;

/**
 * Created by stevedesilva on 31/03/2017.
 */
public class QuestionResponse {
    private String question;
    private String answer;

    public QuestionResponse(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question " + question  +
                " - Answer " + answer ;
    }
}
