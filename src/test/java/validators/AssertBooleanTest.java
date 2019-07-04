package validators;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.*;
import org.junit.Test;
import service.DataUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AssertBooleanTest {

    @Test
    public void checkBooleanTest() throws JsonProcessingException, WrongInputException {


    Candidate testClass;
    Map <String, EventContact> mapEvent = new HashMap <>();
        mapEvent.put(ResultOfCall.NO_ANSWER.getName(),new

    EventContact(ResultOfCall.NO_ANSWER.getName()));
    BasicInformation basicInformation = new BasicInformation("Andrey", "Алексеевич", "Бобков", "8-908-162-89-25", "16.12-2010");
    testClass =new Candidate(basicInformation, mapEvent, "hh","мы","хар");

    LocalDateTime begin = LocalDateTime.now();

    String result = DataUtils.writeToFile(testClass, null, true);
        testClass.setCallTo("true");
        assertEquals(true,testClass.isCallTo());
        testClass.setCallTo("false");
        assertEquals(false,testClass.isCallTo());

}
@Test//(expected = AssertionError.class)
public void checkBooleanTestException() throws WrongInputException, JsonProcessingException {
        Candidate testClass;
        Map <String, EventContact> mapEvent = new HashMap <>();
        mapEvent.put(ResultOfCall.NO_ANSWER.getName(),new EventContact(ResultOfCall.NO_ANSWER.getName()));
        BasicInformation basicInformation = new BasicInformation("Andrey", "Алексеевич", "Бобков", "8-908-162-89-25", "16.12-2010");
        testClass =new Candidate(basicInformation, mapEvent, "hh","мы","хар");

  //      try {
            testClass.setCallTo("ggg");
 //       }catch (AssertionError e){
            System.out.println("catch");
//            assertEquals("Введите только true или false  ",e.getMessage());
 //           throw new AssertionError (e);
  //      }

 //   Assert.fail("must be Exception for setterBoolean");


    }
    @Test//(expected = NullPointerException.class)
    public void checkBooleanTestExceptionNull() throws WrongInputException {


        Candidate testClass;
        Map <String, EventContact> mapEvent = new HashMap <>();
        mapEvent.put(ResultOfCall.NO_ANSWER.getName(),new EventContact(ResultOfCall.NO_ANSWER.getName()));
        BasicInformation basicInformation = new BasicInformation("Andrey", "Алексеевич", "Бобков", "8-908-162-89-25", "16.12-2010");
        testClass =new Candidate(basicInformation, mapEvent, "hh","мы","хар");


            testClass.setCallTo(null);

 //       Assert.fail("must be Exception for setterBoolean = null");

    }
}
