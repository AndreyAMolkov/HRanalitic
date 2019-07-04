package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.StringUtils;
import service.DataUtils;
import service.LocalDateTimeDeserializer;
import service.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = {"birthdayString"})
public class BasicInformation implements Serializable {
    private String name;
    private String patronymic;
    private String surname;
    private List <String> phonesList;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime birthday;


    public BasicInformation(String name, String patronymic, String surname, List<String> phonesList, String birthday) throws WrongInputException {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        setPhone(phonesList);
        setBirthday(birthday);
    }

    public BasicInformation(String name, String patronymic, String surname, List<String> phonesList, LocalDateTime birthday) throws WrongInputException {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        setPhone(phonesList);
        this.birthday = birthday;
    }

    public BasicInformation(String name, String patronymic, String surname, String phone, String birthday) throws WrongInputException {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        setPhone(phone);
        setBirthday(birthday);
    }

    public BasicInformation(String name, String patronymic, String surname, String phone, LocalDateTime birthday) throws WrongInputException {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        setPhone(phone);
        this.birthday = birthday;
    }

    public BasicInformation() {
    }

    public void setPhone(List<String> phonesList) throws WrongInputException {
        for (String one : phonesList) {
            setPhone(one);
        }
    }

    public boolean setPhone(String phone) throws WrongInputException {
        boolean response = false;
        if (phonesList == null) {
            phonesList = new ArrayList <>();
        }
        String result = DataUtils.phoneClear(phone);
        if (!StringUtils.isEmpty(result)) {
            phonesList.add(result);
            response = true;
        }
        return response;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BasicInformation{" +
                "name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", phonesList=" + phonesList +
                ", birthday=" + birthday +
                '}';
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) throws WrongInputException {

        this.birthday = DataUtils.stringToLocalDate(birthday);
    }

    public String getBirthdayString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        return formatter.format(birthday);
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List <String> getPhonesList() {
        return phonesList;
    }

    public void setPhonesList(List <String> phonesList) {
        this.phonesList = phonesList;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.FORMAT_DATE_TIME_FOR_FORMATTER)
    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
}
