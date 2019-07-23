package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.constats.Constants;
import model.exception.WrongInputException;
import model.pojo.EventContact;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class LocalDateTimeSerializerTest {
    @Test
    public void serialize() throws WrongInputException, JsonProcessingException {

        EventContact eventContact = new EventContact("test");
        eventContact.setDateOfComment(LocalDateTime.of(1, 1, 1, 1, 1));
        assertEquals("{\"comment\":\"test\",\"dateOfComment\":\"01.01.0001 01:01:00\",\"dateTimeInterview\":null}", Constants.OBJECT_MAPPER.writeValueAsString(eventContact));
    }
}