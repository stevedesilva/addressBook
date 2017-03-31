package com.desilva.response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by stevedesilva on 31/03/2017.
 */
public class QuestionResponseTest {
    @Test
    public void questionResponse_WhenConstructed_ShouldNotBeNull() {
        assertNotNull(new QuestionResponse("Question Text"));
    }

    @Test
    public void getQuestion_WhenConstructed_ShouldReturnQuestionString() {
        QuestionResponse questionResponse = new QuestionResponse("Question Text");
        String result = questionResponse.getQuestion();
        assertEquals("Question Text",result);

    }

    @Test
    public void getAnswer_WhenConstructed_ShouldReturnAnswerString() {
        QuestionResponse questionResponse = new QuestionResponse("Question Text");
        questionResponse.setAnswer("Answer Text");
        String result = questionResponse.getAnswer();
        assertEquals("Answer Text",result);
    }

}