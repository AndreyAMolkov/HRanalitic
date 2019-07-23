package model.constats;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public final class Constants {
    public static final String FORMAT_DATE_TIME_FOR_FORMATTER = "dd.MM.uuuu HH:mm:ss";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern(FORMAT_DATE_TIME_FOR_FORMATTER);
    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.uuuu");
    public static final Pattern CLEAR_PATTERN = Pattern.compile("[\\s]+");
    public static final String BASIC_INFORMATION = "BasicInformation";
    public static final String NAME = "name";
    public static final String PATRONYMIC = "patronymic";
    public static final String SURNAME = "surname";
    public static final String PHONESLIST = "phonesList";
    public static final String BIRTHDAY = "birthday";
    public static final String EVENT_MAP = "eventMap";
    public static final String SOURCE = "source";
    public static final String PROJECT = "project";
    public static final String INITIATIVE = "initiative";
    public static final String RESULT_OF_INTERVIEW = "result";
    public static final int LENGTH_OF_CELL_PHONE = 11;
    public static final String FORMAT_DATE = "дд.мм.гггг";
    public static final String FORMAT_TIME = "чч:мм";
    public static final String EMPTY = "пусто";
    public static final String FOR = " для ";
    public static final String WRONG_FORMAT = " Неправильный формат ";
    public static final String WRONG_FORMAT_PHONE = WRONG_FORMAT + "сотового телефона - длина должна быть ";
    public static final String WRONG_FORMAT_DATE = WRONG_FORMAT + "даты - ";
    public static final String WRONG_FORMAT_TIME = WRONG_FORMAT + "времени - ";
    public static final String WRONG_FORMAT_ENUM = " Неоднозначный результат, найдено больше одного";
    public static final String NOT_FOUND = " Не нашел значения для ";
    public static final String MONTH = "month";
    public static final String YEAR = "year";
    public static final String DAY = "day";

    public static final String CALL = "Call";
    public static final String INTERVIEW = "Interview";
    public static final String ESTIMATE_OF_HR = "EstimateOfHR";
    public static final String COMMENT_HR = "HRComment";
    public static final String RESULT_OF_TEST = "ResultOfTest";
    public static final String RESULT_OF_INTERVIEW_GENERAL = "GeneralResultOfInterview";
    public static final String RESULT_OF_TRAINING = "ResultOfTraining";
    public static final String NOT_PASSED_TRAINING = "NotPassedTraining";
    public static final String COMMENT_ESTIMATE_OF_COACH = "EstimateOFCoachComment";
    public static final String RESULT_OF_ADAPTATION = "ResultOfAdaptation";
    public static final String PROPERTIES_NAME = "properties";
    public static final String PATH_DIR = "C:\\temp\\HRResearcher\\";
    public static final String DATA_BASE = PATH_DIR + "base.txt";

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private Constants() {
    }
}
