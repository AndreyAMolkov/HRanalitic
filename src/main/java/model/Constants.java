package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public final class Constants {
    public final static String FORMAT_DATE_TIME_FOR_FORMATTER = "dd.MM.uuuu HH:mm:ss";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern(FORMAT_DATE_TIME_FOR_FORMATTER);
    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.uuuu");
    //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy HH:mm");
    public static final Pattern CLEAR_PATTERN = Pattern.compile("[\\s]+");
    public final static String BASIC_INFORMATION = "BasicInformation";
    public final static String NAME = "name";
    public final static String PATRONYMIC = "patronymic";
    public final static String SURNAME = "surname";
    public final static String PHONESLIST = "phonesList";
    public final static String BIRTHDAY = "birthday";
    public final static String EVENT_MAP = "eventMap";
    public final static String SOURCE = "source";
    public final static String PROJECT = "project";
    public final static String INITIATIVE = "initiative";
    public final static String RESULT_OF_INTERVIEW = "result";
    public final static int LENGTH_OF_CELL_PHONE = 11;
    public final static String FORMAT_DATE = "дд.мм.гггг";
    public final static String FORMAT_TIME = "чч:мм";
    public final static String EMPTY = "пусто";
    public final static String FOR = " для ";
    public final static String WRONG_FORMAT = "Неправильный формат ";
    public final static String WRONG_FORMAT_PHONE = WRONG_FORMAT + "сотового телефона - длина должна быть ";
    public final static String WRONG_FORMAT_DATE = WRONG_FORMAT + "даты - ";
    public final static String WRONG_FORMAT_TIME = WRONG_FORMAT + "времени - ";
    public final static String WRONG_FORMAT_ENUM = "Неоднозначный результат, найдено больше одного";
    public final static String NOT_FOUND = "Не нашел значения для ";
    public final static String MONTH = "month";
    public final static String YEAR = "year";
    public final static String DAY = "day";

    public final static String CALL = "Call";
    public final static String INTERVIEW = "Interview";
    public final static String ESTIMATE_OF_HR = "EstimateOfHR";
    public final static String COMMENT_HR = "HRComment";
    public final static String RESULT_OF_TEST = "ResultOfTest";
    public final static String RESULT_OF_INTERVIEW_GENERAL = "GeneralResultOfInterview";
    public final static String RESULT_OF_TRAINING = "ResultOfTraining";
    public final static String NOT_PASSED_TRAINING = "NotPassedTraining";
    public final static String COMMENT_ESTIMATE_OF_COACH = "EstimateOFCoachComment";
    public final static String RESULT_OF_ADAPTATION = "ResultOfAdaptation";


    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
}
