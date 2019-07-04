package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.StringUtils;
import service.DataUtils;
import service.LocalDateTimeDeserializer;
import service.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@JsonIgnoreProperties(value = {"dateTimeInterviewString", "dateTime", "dateTimeOfCommentString"})
public class EventContact implements Serializable {
    @JsonProperty("comment")
    private String comment;
    //   @JsonFormat(shape = JsonFormat.Shape.ANY, pattern = Constants.FORMAT_DATE_TIME_FOR_FORMATTER)
    @JsonProperty("dateOfComment")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateOfComment;
    @JsonProperty("dateTimeInterview")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateTimeInterview;

    public EventContact(String comment, String localDateForInterview, String localTimeForInterview) throws WrongInputException {
        if (StringUtils.isEmpty(comment) || !(AnswerOfCallConversation.INTERVIEW.getNameLowerCase()).contains(comment.toLowerCase())) {
            throw new WrongInputException(Constants.WRONG_FORMAT + Constants.FOR + " регистрации времени интерьвью");
        }
        this.comment = comment;
        setDateTime();
        setDateTimeInterview(localDateForInterview, localTimeForInterview);
    }

    public EventContact(String comment) throws WrongInputException {
        if (StringUtils.isEmpty(comment) || (AnswerOfCallConversation.INTERVIEW.getNameLowerCase()).contains(comment.toLowerCase())) {
            throw new WrongInputException(Constants.WRONG_FORMAT + Constants.FOR + " регистрации события");
        }
        this.comment = comment;
        setDateTime();
        this.dateTimeInterview = null;
    }

    public EventContact(String comment, LocalDateTime dateOfComment, LocalDateTime dateTimeInterview) {
        this.comment = comment;
        this.dateOfComment = dateOfComment;
        this.dateTimeInterview = dateTimeInterview;
    }

    public EventContact() {
    }

    public LocalDateTime getDateTimeInterview() {
        return dateTimeInterview;
    }

    public void setDateTimeInterview(LocalDateTime dateTimeInterview) {
        this.dateTimeInterview = dateTimeInterview;
    }

    public String getDateTimeInterviewString() {
        if (dateTimeInterview != null) {
            return Constants.DATE_FORMATTER.format(dateTimeInterview);
        }
        return null;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDateTime() {
        return dateOfComment;
    }

    private void setDateTimeInterview(String localDateForInterview, String localTimeForInterview) throws WrongInputException {
        LocalDateTime localDateTime = DataUtils.stringToLocalDate(localDateForInterview);
        LocalDate localDate = LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
        LocalTime localTime = DataUtils.stringToLocalTime(localTimeForInterview);
        this.dateTimeInterview = LocalDateTime.of(localDate, localTime);
    }

    private void setDateTime() {
        this.dateOfComment = LocalDateTime.now();
    }

    public String getDateTimeOfCommentString() {
        return Constants.DATE_FORMATTER.format(dateOfComment);
    }

    @Override
    public String toString() {
        return "EventContact{" +
                "comment='" + comment + '\'' +
                ", dateOfComment=" + dateOfComment +
                ", dateTimeInterview=" + dateTimeInterview +
                '}';
    }

    public LocalDateTime getDateOfComment() {
        return dateOfComment;
    }

    public void setDateOfComment(String localDateTime) {
        this.dateOfComment = LocalDateTime.parse(localDateTime, Constants.DATE_FORMATTER);
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.FORMAT_DATE_TIME_FOR_FORMATTER)
    public void setDateOfComment(LocalDateTime dateOfComment) {
        this.dateOfComment = dateOfComment;
    }

}
