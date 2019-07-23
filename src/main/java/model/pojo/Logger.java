package model.pojo;

import service.DataUtils;

import java.time.LocalDateTime;

public class Logger {
    private String message;
    private String nameOfMethod;
    private String time;

    public void printMessage(String nameOfMethod, String message) {
        this.message = message;
        this.nameOfMethod = nameOfMethod;
        setTime(DataUtils.convertLocalDateTimeToString(LocalDateTime.now()));
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return " " + getTime() + ": " + getNameOfMethod() + " :" + getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNameOfMethod() {
        return nameOfMethod;
    }

    public void setNameOfMethod(String nameOfMethod) {
        this.nameOfMethod = nameOfMethod;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
