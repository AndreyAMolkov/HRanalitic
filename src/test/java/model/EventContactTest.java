package model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EventContactTest {

    @Test
    public void setComment() throws WrongInputException {
        EventContact testClass = new EventContact("check");
        assertEquals("check", testClass.getComment());
    }

    @Test(expected = WrongInputException.class)
    public void EventContactInterviewWrong() throws WrongInputException {
        EventContact testClass = new EventContact("check", "12.12.2015", "12.12");
    }

    @Test
    public void EventContactInterview() throws WrongInputException {
        EventContact testClass = new EventContact(AnswerOfCallConversation.INTERVIEW.getName(), "12.12.2015", "12.12");
        assertEquals(AnswerOfCallConversation.INTERVIEW.getName(), testClass.getComment());
        assertNotNull(testClass.getDateTimeInterviewString());
    }

    @Test(expected = WrongInputException.class)
    public void EventContactWrong() throws WrongInputException {
        EventContact testClass = new EventContact(AnswerOfCallConversation.INTERVIEW.getName());
    }

    @Test
    public void setLocalDateTimeForComment() throws WrongInputException {
        EventContact testClass = new EventContact("check");
        LocalDateTime expected = testClass.getDateTime().minusDays(1);
        testClass.setDateOfComment(expected.toString());
        assertEquals(expected, testClass.getDateTime());
    }
}