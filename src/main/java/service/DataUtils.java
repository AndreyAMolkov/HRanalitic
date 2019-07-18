package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.*;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DataUtils {
    private static final Gson GSON = new Gson();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String writeToFile(Candidate candidate, String pathString, boolean append) throws JsonProcessingException {
        String result = null;
        String path;
        if (StringUtils.isEmpty(pathString)) {
            path = "C:\\temp\\HrResecher\\base.txt";
            //  System.out.println("Default path =" + path);
        } else {
            path = pathString;
        }
        String candidatString = (new ObjectMapper()).writeValueAsString(candidate);
        //       String candidatString = GSON.toJson(candidate);

        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, append))) {

            writer.println(candidatString);
            result = candidatString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List <String> readFromFile(String pathString) {
        List <String> result = new ArrayList <>();
        String path;
        if (StringUtils.isEmpty(pathString)) {
            path = "C:\\temp\\HrResecher\\base.txt";
            //  System.out.println("Default path =" + path);
        } else {
            path = pathString;
        }

        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {

            //br returns as stream and convert it into a List
            result = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Candidate> convertStringJsonToCandidate(List<String> listCandidates) throws WrongInputException, NotFoundExeption, IOException {
        List <Candidate> result = null;
        if (listCandidates != null) {
            List <Candidate> list = new ArrayList <>();
            for (String candidate : listCandidates) {
                Candidate convertStringJsonToCandidate = convertStringJsonToCandidate(candidate);
                list.add(convertStringJsonToCandidate);
            }
            result = list;
        }

        return result;
    }

    public static Candidate convertStringJsonToCandidate(String candidateLine) throws IOException, WrongInputException, NotFoundExeption {
        Candidate result;
        if (StringUtils.isEmpty(candidateLine)) {
            return null;
        }
        int check = candidateLine.indexOf("{");
        JSONObject json = new JSONObject(candidateLine.substring(check));
//        BasicInformation basicInformation = null;
        Map <String, EventContact> eventMap;
//        String temperature = null;
//        String source = null;
//        String project = null;
//        String buffer;
//        EventContact resultOfInterview = null;
//        if (json.has(Constants.BASIC_INFORMATION)) {
//            basicInformation = convertStringToBasicInformation(json.get(Constants.BASIC_INFORMATION));
//        }
//
        if (json.has(Constants.EVENT_MAP)) {
            eventMap = OBJECT_MAPPER.readValue(json.get(Constants.EVENT_MAP).toString(), Map.class);

            int check1 = candidateLine.indexOf("{");

        }
//
//        if (json.has(Constants.SOURCE)) {
//            source = getName(json, Arrays.asList(Source.values()), json.get(Constants.SOURCE).toString());
//        }
//
//        if (json.has(Constants.INITIATIVE)) {
//            temperature = getName(json, Arrays.asList(Initiative.values()), json.get(Constants.INITIATIVE).toString());
//        }
//        if (json.has(Constants.PROJECT)) {
//            project = getName(json, Arrays.asList(Project.values()), json.get(Constants.PROJECT).toString());
//        }
//        if (json.has(Constants.RESULT_OF_INTERVIEW)) {
//            resultOfInterview = OBJECT_MAPPER.readValue(json.get(Constants.RESULT_OF_INTERVIEW).toString(), EventContact.class);
//        }
//        result = new Candidate(basicInformation, eventMap, source, temperature, project, resultOfInterview);

//        return result;
        return Constants.OBJECT_MAPPER.readValue(candidateLine, Candidate.class);
    }

    public static BasicInformation convertStringToBasicInformation(Object basicJson) throws IOException, WrongInputException {
        BasicInformation result;
        if (basicJson == null) {
            return null;
        }
        JSONObject json = new JSONObject(basicJson.toString());
        String name = null;
        String patronymic = null;
        String surname = null;
        List <String> phoneList = null;
        String birthday = null;
        if (json.has(Constants.NAME)) {
            name = json.get(Constants.NAME).toString();
        }
        if (json.has(Constants.PATRONYMIC)) {
            patronymic = json.get(Constants.PATRONYMIC).toString();
        }
        if (json.has(Constants.SURNAME)) {
            surname = json.get(Constants.SURNAME).toString();
        }
        if (json.has(Constants.PHONESLIST)) {
            Object obj = json.get(Constants.PHONESLIST);
            phoneList = OBJECT_MAPPER.readValue(obj.toString(), List.class);
        }
        if (json.has(Constants.BIRTHDAY)) {
            birthday = json.get(Constants.BIRTHDAY).toString();
        }

        result = new BasicInformation(name, patronymic, surname, phoneList, birthday);

        return result;
    }

    public static String phoneClear(String phone) throws WrongInputException {
        String result = null;
        if (StringUtils.isEmpty(phone)) {
            throw new WrongInputException(Constants.WRONG_FORMAT_PHONE + Constants.LENGTH_OF_CELL_PHONE + Constants.FOR + result);
        }
        result = phone.replaceAll("\\D+", "");
        if (StringUtils.isEmpty(result) || !(result.length() == Constants.LENGTH_OF_CELL_PHONE)) {
            throw new WrongInputException(Constants.WRONG_FORMAT_PHONE + Constants.LENGTH_OF_CELL_PHONE + Constants.FOR + result);
        }
        return result;
    }

    public static LocalDateTime stringToLocalDate(String date) throws WrongInputException, DateTimeParseException {
        LocalDateTime localDate = null;
        LocalDate localDateBuf;
        if (StringUtils.isEmpty(date)) {
            throw new WrongInputException(Constants.WRONG_FORMAT_DATE + Constants.FORMAT_DATE + Constants.FOR + date);
        }

        String result = convertYYmmDDtoDDmmYY(date).replace(",", ".").replace("-", ".");
        String regex = "^(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)$";
        if (Pattern.matches(regex, result)) {

            localDateBuf = LocalDate.parse(result, Constants.LOCAL_DATE_FORMATTER);
            localDate = LocalDateTime.of(localDateBuf.getYear(), localDateBuf.getMonth(), localDateBuf.getDayOfMonth(), 0, 0);
        } else if (date.contains(Constants.YEAR) && date.contains(Constants.MONTH) && date.contains(Constants.DAY) && date.contains("{")) {
            JSONObject json = new JSONObject(date);
            localDate = LocalDateTime.of(Integer.parseInt(json.get(Constants.YEAR).toString()), Integer.parseInt(json.get(Constants.MONTH).toString()), Integer.parseInt(json.get(Constants.DAY).toString()), 0, 0);
        } else if (date.contains(" ") && Pattern.matches(regex, date.split(" ")[0])) {
            localDateBuf = LocalDate.parse(date.split(" ")[0], Constants.LOCAL_DATE_FORMATTER);
            LocalTime localTime = DataUtils.stringToLocalTime(date.split(" ")[1]);
            localDate = LocalDateTime.of(localDateBuf.getYear(), localDateBuf.getMonth(), localDateBuf.getDayOfMonth(), localTime.getHour(), localTime.getMinute());
        } else {
            throw new WrongInputException(Constants.WRONG_FORMAT_DATE + Constants.FORMAT_DATE + Constants.FOR + date);
        }
        return localDate;
    }


    public static LocalTime stringToLocalTime(String time) throws WrongInputException {
        if (StringUtils.isEmpty(time)) {
            throw new WrongInputException(Constants.WRONG_FORMAT_TIME + Constants.FORMAT_TIME + Constants.FOR + time);
        }
        String result = time.replace(".", ":").replace(",", ":").replace("-", ":");
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        String regex2 = "(([0-1]?[0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]";

        if (!Pattern.matches(regex, result)) {
            if (Pattern.matches(regex2, result)) {
                return LocalTime.parse(result, DateTimeFormatter.ofPattern("HH:mm:ss"));
            } else {
                throw new WrongInputException(Constants.WRONG_FORMAT_TIME + Constants.FORMAT_TIME + Constants.FOR + time);
            }
        }
        return LocalTime.parse(result, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static Map<String, String> getFIOfromString(String fio) throws WrongInputException {
        String linePatronymic;
        String line = Constants.CLEAR_PATTERN.matcher(fio).replaceAll(" ").trim();
        if (!line.contains(" ")) {
            throw new WrongInputException(Constants.WRONG_FORMAT + Constants.WRONG_FORMAT + "Фамилия Имя Отчество");
        }
        Map <String, String> result = new HashMap <>();
        result.put(Constants.SURNAME, line.substring(0, line.indexOf(" ")));
        String lineName = line.substring(line.indexOf(" ") + 1);
        if (!lineName.contains(" ")) {
            result.put(Constants.NAME, lineName);
        } else {
            result.put(Constants.NAME, lineName.substring(0, lineName.indexOf(" ")));
            linePatronymic = lineName.substring(lineName.indexOf(" ") + 1);
            result.put(Constants.PATRONYMIC, linePatronymic);
        }
        return result;
    }

    public static List <String> getListNameOfEnum(EnumInterface[] enumI) {
        List <String> result = new ArrayList <>();
        for (int i = 0; i < enumI.length; i++) {
            result.add(enumI[i].getName());
        }
        return result;
    }

    private static String getName(JSONObject json, List <EnumInterface> listEnum, String buffer) throws NotFoundExeption {
        String result = null;
        String alias;
//        buffer = json.get(Constants.SOURCE).toString();
        for (EnumInterface one : listEnum) {
            alias = one.getName();
            if (!StringUtils.isEmpty(buffer) && buffer.equals(one.toString())) {
                result = alias;
            }
        }
        if (result == null) {
            throw new NotFoundExeption(Constants.NOT_FOUND + result + Constants.FOR + DataUtils.arrayToString(Source.values()));
        }
        return result;
    }

    public static String getComment(Object object) {
        String result;
        if (object instanceof EnumInterface) {
            result = ((EnumInterface) object).getName();
        } else {
            result = object.toString();
        }
        return result;
    }

    private static String convertYYmmDDtoDDmmYY(String date) {
        String result;
        String regex = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";

        if (Pattern.matches(regex, date)) {
            String day = date.substring(date.lastIndexOf("-") + 1);
            String month = date.substring(date.indexOf("-") + 1, date.lastIndexOf("-"));
            String year = date.substring(0, date.indexOf("-"));
            result = day + "." + month + "." + year;

        } else {
            result = date;
        }


        return result;
    }

    public static String arrayToString(Object[] list) {
        StringBuilder builder = new StringBuilder();
        for (Object object : list) {
            builder.append(object.toString() + " ");
        }
        return builder.toString();
    }

    public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy HH:mm");
        return formatter.format(localDateTime);
    }

    public static List<CollectData> candidateToCollectData(List<Candidate> listOfCandidate) throws WrongInputException {
        if (listOfCandidate == null) {
            return null;
        }
        List <CollectData> result = new ArrayList <>();
        Integer count = 0;
        for (Candidate one : listOfCandidate) {
            result.add(new CollectData(one, count));
            one.setPosition(count++);
        }
        return result;
    }

    public static EventContact createEvent(Object comment, String localDate, String localTime) throws WrongInputException {
        EventContact result;
        if (comment != null && StringUtils.isEmpty(comment.toString())) {
            throw new WrongInputException("Error: ввод пустого значения для причины или даты");
        }

        if (!StringUtils.isEmpty(localTime) && !StringUtils.isEmpty(localDate)) {
            result = new EventContact(DataUtils.getComment(comment), localDate, localTime);
        } else {
            result = new EventContact(DataUtils.getComment(comment));
        }


        return result;
    }

    public static EventContact mapToEvent1(Object map) {
        Map <String, String> test = (Map <String, String>) map;
        Object comment;
        Object date;
        Object dateInterview;
        Iterator <String> iterator = test.keySet().iterator();
        String it = iterator.next();
        Object testitog = test.get(it);
        Map <String, String> test1 = (Map <String, String>) testitog;
        Iterator <String> iterator1 = test1.keySet().iterator();
        if (test1.keySet().size() == 2) {
            String it1 = iterator1.next();
            date = test1.get(it1);
            String it2 = iterator1.next();
            comment = test1.get(it2);
            EventContact ev = new EventContact();
            LocalDateTime localDate;

            if (((Map <String, String>) date).keySet().size() == 2) {
                localDate = mapToLocalDateTime((Map <String, String>) date);
                ev.setDateOfComment(localDate);
            }

            ev.setComment(date.toString());


        }
        if (test1.keySet().size() == 3) {
            comment = test1.get(iterator.next());
            date = test1.get(iterator1.next());
            dateInterview = test.get(iterator1.next());

        }

        if (iterator1.hasNext() && iterator1.next() instanceof String) {
            comment = test1.get(iterator1.next());
            date = test1.get(iterator1.next());
            dateInterview = test1.get(iterator.next());
        }
        return null;
    }

    public static Map <String, EventContact> mapToEvent(Object object) {
        if (object instanceof Map) {
        } else {
            return null;
        }
        EventContact eventContact;
        Map <String, String> mapFull = (Map <String, String>) object;
        Map <String, String> mapOne;
        Object mapObject;
        Iterator <String> iteratorFull = mapFull.keySet().iterator();
        Iterator <String> iteratorOne;
        for (String keyFull = iteratorFull.next(); iteratorFull.hasNext(); iteratorFull.next()) {
            mapObject = mapFull.get(keyFull);
            mapOne = (Map <String, String>) mapObject;
            iteratorOne = mapOne.keySet().iterator();
            for (String keyOne = iteratorOne.next(); iteratorOne.hasNext(); iteratorOne.next()) {

            }
        }

//        String it = iterator.next();
//        Object testitog = test.get(it);


        return null;
    }

    public static LocalDateTime mapToLocalDateTime(Map <String, String> map) {
        Object dateMap;
        Map <String, Integer> date = null;
        Object timeMap;
        Map <String, Integer> time = null;
        if (map.containsKey("date")) {
            dateMap = map.get("date");
            date = (Map <String, Integer>) dateMap;

        }
        if (map.containsKey("time")) {
            timeMap = map.get("time");
            time = (Map <String, Integer>) timeMap;
        }

        int year = date.get("year");
        int month = date.get("month");
        int day = date.get("day");
        int hour = time.get("hour");
        int minute = time.get("minute");
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    private EventContact collectEvent(Map <String, String> map, EventContact eventContact) {

        return eventContact;
    }

    public static boolean isInclude(Candidate candidate, List<Candidate> list) throws WrongInputException {
        if (!isNotNullParam(candidate)) {
            throw new WrongInputException("Error: пустые поля фамилии и даты рождения для нового кандидата");
        }
        String surname = candidate.getBasicInformation().getSurname();
        LocalDateTime birthday = candidate.getBasicInformation().getBirthday();

        for (Candidate one : list) {
            if (isNotNullParam(one) && birthday.isEqual(one.getBasicInformation().getBirthday()) && surname.trim().equals(one.getBasicInformation().getSurname().trim())) {
                return true;
            }
        }
        return false;
    }

    public static CollectData getCollectDataViaCandidate(Candidate candidate, List<CollectData> list) throws WrongInputException {
        if (!isNotNullParam(candidate)) {
            throw new WrongInputException("Error: пустые поля фамилии и даты рождения для нового кандидата");
        }
        String surname = candidate.getBasicInformation().getSurname();
        String birthday = candidate.getBasicInformation().getBirthdayString();
        if (list == null) {
            return null;
        }
        for (CollectData one : list) {
            if (isNotNullParam(one) && birthday.equals(one.getBirthday()) && one.getFio().contains(surname)) {
                return one;
            }
        }
        return null;
    }

    private static boolean isNotNullParam(Candidate candidate) {

        return (candidate != null && candidate.getBasicInformation() != null && candidate.getBasicInformation().getBirthday() != null && candidate.getBasicInformation().getSurname() != null && !candidate.getBasicInformation().getSurname().isEmpty());
    }

    private static boolean isNotNullParam(CollectData collectData) {

        return (collectData != null && collectData.getFio() != null && collectData.getBirthday() != null && !collectData.getBirthday().isEmpty() && !collectData.getFio().isEmpty());
    }


}
