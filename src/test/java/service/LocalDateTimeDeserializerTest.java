package service;

import model.Candidate;
import model.Constants;
import model.EventContact;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LocalDateTimeDeserializerTest {


    @Test
    public void deserializeTest() throws IOException {
        String line = "{\"comment\":\"test\",\"dateOfComment\":\"01.01.0001 01:01:00\",\"dateTimeInterview\":\"02.01.0001 01:01:00\"}";

        EventContact eventContact = Constants.OBJECT_MAPPER.readValue(line, EventContact.class);
        assertTrue(eventContact != null);
        assertEquals("test", eventContact.getComment());
        assertEquals("dateOfInterview", "02.01.0001 01:01:00", eventContact.getDateTimeInterviewString());
        assertEquals("dateOfComment", "01.01.0001 01:01:00", eventContact.getDateTimeOfCommentString());
    }

    @Test
    public void deserializeCandidateTest() throws IOException {
        String line = "{\"eventMap\":{\"Interview\":{\"dateTimeOfCommentString\":\"19.05.2019 22:26:19\",\"comment\":\"приглашен на интервью\",\"dateOfComment\":\"19.05.2019 22:26:19\",\"dateTimeInterview\":\"19.05.2019 12:12:00\"}},\"initiative\":\"WARM\",\"source\":\"HEAD_HUNTER\",\"project\":\"TNS\",\"result\":null,\"basicInformation\":{\"name\":\"Molkov\",\"patronymic\":\"Andrey\",\"surname\":\"Al\",\"phonesList\":[\"89784561232\"],\"birthday\":\"12.12.1981 00:00:00\"}}";

        Candidate testClass = Constants.OBJECT_MAPPER.readValue(line, Candidate.class);
        assertTrue(testClass != null);
//        assertEquals("test", eventContact.getComment());
//        assertEquals("02.01.0001 01:01:00", eventContact.getDateTimeInterviewString());
//        assertEquals("01.01.0001 01:01:00", eventContact.getDateTimeInterviewString());
    }
}