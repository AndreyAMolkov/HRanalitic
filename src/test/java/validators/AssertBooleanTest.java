package validators;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.*;
import org.junit.Assert;
import org.junit.Test;
import service.DataUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AssertBooleanTest {

    @Test
    public void checkBooleanTest() throws JsonProcessingException, WrongInputExeption {


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
    public void checkBooleanTestException() throws WrongInputExeption, JsonProcessingException {
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
    public void checkBooleanTestExceptionNull() throws WrongInputExeption {


        Candidate testClass;
        Map <String, EventContact> mapEvent = new HashMap <>();
        mapEvent.put(ResultOfCall.NO_ANSWER.getName(),new EventContact(ResultOfCall.NO_ANSWER.getName()));
        BasicInformation basicInformation = new BasicInformation("Andrey", "Алексеевич", "Бобков", "8-908-162-89-25", "16.12-2010");
        testClass =new Candidate(basicInformation, mapEvent, "hh","мы","хар");


            testClass.setCallTo(null);

 //       Assert.fail("must be Exception for setterBoolean = null");

    }
}
