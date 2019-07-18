package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DataUtilsTest {
    BasicInformation basicInformation;
    Candidate testClassCandidate;
    Map<String, EventContact> mapEvent;


    @Before
    public void prepare() throws WrongInputException {
        basicInformation = new BasicInformation("Andrey", "Алексеевич", "Бобков", "8-908-162-89-25", "16.12-2010");
        testClassCandidate = new Candidate(basicInformation, mapEvent, "hh", "мы", "хар", false);
        mapEvent = new HashMap<>();
        mapEvent.put(ResultOfCall.NO_ANSWER.getName(), new EventContact(ResultOfCall.NO_ANSWER.getName()));
    }

    @Test
    public void recordToFile() throws WrongInputException, JsonProcessingException {
        String result = DataUtils.writeToFile(testClassCandidate, null, true);
        assertNotNull(result);
    }

    @Test
    public void convertLocalDateTime() throws WrongInputException {
        String date = "21.06.2019 19:23:00";
        LocalDateTime localDateTime = DataUtils.stringToLocalDate(date);

        assertNotNull(localDateTime);
    }
    @Test
    public void readFromFile() {
        LocalDateTime begin = LocalDateTime.now();
        System.out.println(begin);
        List <String> result = DataUtils.readFromFile(null);
        System.out.println(result.size() + " - " + LocalDateTime.now());

        assertTrue(result.size() > 0);
    }

    @Test
    public void stringToDate() throws WrongInputException {

        assertEquals(LocalDate.parse("01.11.2010", DateTimeFormatter.ofPattern("d.MM.yyyy")), DataUtils.stringToLocalDate("1,11,2010"));
    }

    @Test(expected = WrongInputException.class)
    public void stringToDateWrong() throws WrongInputException {

        DataUtils.stringToLocalDate("11,111,2010");
    }

    @Test
    public void phoneClear() throws WrongInputException {
        assertEquals("89101652525", DataUtils.phoneClear("8b910.165g2525"));
    }

    @Test(expected = WrongInputException.class)
    public void phoneClearWrongLength() throws WrongInputException {
        DataUtils.phoneClear("8910165525");
    }

    @Test
    public void convertStringJsonToCandidate() throws IOException, NotFoundExeption, WrongInputException {
        List <Candidate> result;
        result = DataUtils.convertStringJsonToCandidate(DataUtils.readFromFile(null));
        assertNotNull(result);
    }

    @Test
    public void convertStringJsonToCandidateString() throws IOException, WrongInputException, NotFoundExeption {
        Candidate result;
        String line = "{\"BasicInformation\":{\"name\":\"Andrey\",\"patronymic\":\"Алексеевич\",\"surname\":\"Бобков\",\"phonesList\":[\"89081628925\"],\"birthday\":\"16.12-2010\"},\"eventMap\":{\"OUR_INITIATIVE\":{\"comment\":\"не берет трубку\",\"dateOfComment\":\"2019-05-03T12:20:11.199\"}},\"temperature\":\"мы нашли\",\"source\":\"hh.ru\",\"project\":\"Хартия\"}";
        result = DataUtils.convertStringJsonToCandidate(line);
        assertNotNull(result);
    }

    @Test
    public void convertJsonToBasicInformation() throws WrongInputException, IOException {
        BasicInformation result;
        String line = "{\"name\":\"Andrey\",\"patronymic\":\"Алексеевич\",\"surname\":\"Бобков\",\"phonesList\":[\"89081628925\"],\"birthday\":\"16.12.2010\"}";
        result = DataUtils.convertStringToBasicInformation(line);
        assertNotNull(result);
        assertEquals("Andrey", result.getName());
        assertEquals("Алексеевич", result.getPatronymic());
        assertEquals("Бобков", result.getSurname());
        assertEquals("89081628925", result.getPhonesList().get(0));
        assertEquals("16.12.2010", result.getBirthdayString());

    }

    @Test
    public void stringToLocalTime() throws WrongInputException {
        assertEquals(LocalTime.parse("12:12"), DataUtils.stringToLocalTime("12.12"));
    }

    @Test(expected = WrongInputException.class)
    public void stringToLocalTimeWrong() throws WrongInputException {
        DataUtils.stringToLocalTime("12.60");
    }

    @Test
    public void getFIOfromString() throws WrongInputException {
        String line = " Мушкин    Казимир   Владимирович  ";
        Map <String, String> map;
        map = DataUtils.getFIOfromString(line);

        assertNotNull(map);
        assertEquals("Мушкин", map.get(Constants.SURNAME));
        assertEquals("Казимир", map.get(Constants.NAME));
        assertEquals("Владимирович", map.get(Constants.PATRONYMIC));
    }

    @Test(expected = WrongInputException.class)
    public void testGetFIOfromStringWrong() {
        String line = "Мушкин    ";
        Map <String, String> map;
    }

    @Test
    public void testGetFIOfromString2() throws WrongInputException {
        String line = " Мушкин    Казимир  ";
        Map <String, String> map;
        map = DataUtils.getFIOfromString(line);
        assertNotNull(map);
        assertEquals("Мушкин", map.get(Constants.SURNAME));
        assertEquals("Казимир", map.get(Constants.NAME));
    }

    @Test
    public void testIsNotNullParam() throws WrongInputException {
        BasicInformation wrongBasicInformation = new BasicInformation("Andrey", "Алексеевич", "Бобков", "8-908-162-89-25", "17.12-2010");
        Candidate wrongCandidate = new Candidate(wrongBasicInformation, mapEvent, "hh", "мы", "хар", false);

        List<Candidate> list = new ArrayList<>();
        list.add(testClassCandidate);
        list.add(wrongCandidate);
        assertTrue(DataUtils.isInclude(testClassCandidate, list));

        assertFalse(DataUtils.isInclude(testClassCandidate, new ArrayList<>()));

        list.clear();
        list.add(wrongCandidate);
        assertFalse(DataUtils.isInclude(testClassCandidate, list));

        wrongBasicInformation.setBirthday((LocalDateTime) null);
        assertFalse(DataUtils.isInclude(testClassCandidate, list));

        wrongBasicInformation.setBirthday(testClassCandidate.getBasicInformation().getBirthday());
        wrongBasicInformation.setSurname(testClassCandidate.getBasicInformation().getSurname() + "  ");
        assertTrue(DataUtils.isInclude(testClassCandidate, list));

        wrongBasicInformation.setSurname("  ");
        assertFalse(DataUtils.isInclude(testClassCandidate, list));

    }

    @Test(expected = WrongInputException.class)
    public void testIsNotNullParamException() throws WrongInputException {
        DataUtils.isInclude(null, new ArrayList<>());
    }

    public void testGetCollectDataViaCandidate() throws WrongInputException {
        CollectData collectData = new CollectData(testClassCandidate, 0);
        List<CollectData> list = new ArrayList<>();
        list.add(null);
        list.add(collectData);
        CollectData actual = DataUtils.getCollectDataViaCandidate(testClassCandidate, list);
        assertNotNull(actual);
        assertEquals(0, actual.getPosition());
    }
}