package model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import model.constats.Constants;
import model.enums.Initiative;
import model.enums.Project;
import model.enums.Source;
import model.exception.NotFoundException;
import model.exception.WrongInputException;
import org.apache.commons.lang3.StringUtils;
import service.DataUtils;
import validators.AssertBoolean;

import javax.validation.constraints.NotNull;
import java.util.Map;

@JsonIgnoreProperties(value = {"position", "fio"})
public class Candidate {

    private Integer position;
    private BasicInformation BasicInformation;
    private Map <String, EventContact> eventMap;
    private boolean callTo;
    private String initiative;
    private String source;
    private String project;
    private EventContact result;

    public Candidate(model.pojo.BasicInformation basicInformation, Map<String, EventContact> eventMap, String source, String initiative, String project, EventContact result, boolean callTo) throws WrongInputException, NotFoundException {
        BasicInformation = basicInformation;
        this.eventMap = eventMap;
        setSource(source);
        setProject(project);
        setInitiativeRussian(initiative);
        this.result = result;
        this.callTo = callTo;
    }

    public Candidate(model.pojo.BasicInformation basicInformation, Map<String, EventContact> eventMap, String source, String initiative, String project, boolean callTo) {
        BasicInformation = basicInformation;
        this.eventMap = eventMap;
        this.initiative = initiative;
        this.source = source;
        this.project = project;
        this.result = null;
        this.callTo = callTo;
    }

    public Candidate(int position, model.pojo.BasicInformation basicInformation, Map<String, EventContact> eventMap, String initiative, String source, String project, EventContact result, boolean callTo) { // NOSONAR
        this.position = position;
        BasicInformation = basicInformation;
        this.eventMap = eventMap;
        this.initiative = initiative;
        this.source = source;
        this.project = project;
        this.result = result;
        this.callTo = callTo;
    }

    public Candidate() {
    }

    public model.pojo.BasicInformation getBasicInformation() {
        return BasicInformation;
    }

    public void setBasicInformation(model.pojo.BasicInformation basicInformation) {
        BasicInformation = basicInformation;
    }

    public Map <String, EventContact> getEventMap() {
        return eventMap;
    }

    public void setEventMap(Map <String, EventContact> eventMap) {
        this.eventMap = eventMap;
    }

    public String getSource() {
        return source;
    }

    @JsonSetter("source")
    public void setSourceDonTUseOnlyForMapper(String source) {
        this.source = source;
    }

    public void setSource(String source) throws WrongInputException, NotFoundException {
        String result = null;
        String name;
        for (Source one : Source.values()) {
            name = one.getName();
            if (!StringUtils.isEmpty(source) && name.toLowerCase().contains(source.trim().toLowerCase())) {
                if (result != null) {
                    throw new WrongInputException(Constants.WRONG_FORMAT_ENUM + " нашел - " + result + ", " + one.getName() + Constants.FOR + DataUtils.arrayToString(Source.values()));
                }
                result = one.toString();
            }
        }
        if (result == null) {
            throw new NotFoundException(Constants.NOT_FOUND + source + Constants.FOR + DataUtils.arrayToString(Source.values()));
        }
        this.source = result;
    }

    public String getProject() {
        return project;
    }

    @JsonSetter("project")
    public void setProjectDonTUseOnlyForMapper(String project) {
        this.project = project;
    }

    public void setProject(String project) throws WrongInputException, NotFoundException {
        String result = null;
        String name;
        for (Project one : Project.values()) {
            name = one.getName();
            if (!StringUtils.isEmpty(project) && name.toLowerCase().contains(project.trim().toLowerCase())) {
                if (result != null) {
                    throw new WrongInputException(Constants.WRONG_FORMAT_ENUM + " нашел - " + result + ", " + one.getName() + Constants.FOR + DataUtils.arrayToString(Project.values()));
                }
                result = one.toString();
            }
        }
        if (result == null) {
            throw new NotFoundException(Constants.NOT_FOUND + project + Constants.FOR + DataUtils.arrayToString(Project.values()));
        }
        this.project = result;
    }

    public EventContact getResult() {
        return result;
    }

    public void setResult(EventContact result) {
        this.result = result;
    }

    public String getInitiative() {
        return initiative;
    }

    @JsonSetter("initiative")
    public void setInitiative(String initiativeAlias) {
        this.initiative = initiativeAlias;
    }

    public void setInitiativeRussian(String initiativeRussian) throws WrongInputException, NotFoundException {
        String result = null;
        String name;
        for (Initiative one : Initiative.values()) {
            name = one.getName();
            if (!StringUtils.isEmpty(initiativeRussian) && name.toLowerCase().contains(initiativeRussian.trim().toLowerCase())) {
                if (result != null) {
                    throw new WrongInputException(Constants.WRONG_FORMAT_ENUM + " не однозначный результат, нашел - " + result + ", " + one.getName() + " " + Constants.FOR + DataUtils.arrayToString(Initiative.values()));
                }
                result = one.toString();
            }
        }
        if (result == null) {
            throw new NotFoundException(Constants.NOT_FOUND + initiativeRussian + Constants.FOR + DataUtils.arrayToString(Initiative.values()));
        }
        this.initiative = result;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "BasicInformation=" + BasicInformation +
                ", eventMap=" + eventMap +
                ", initiative='" + initiative + '\'' +
                ", source='" + source + '\'' +
                ", project='" + project + '\'' +
                ", result=" + result +
                '}';
    }

    public String getFio() {
        String fio = getBasicInformation().getSurname() + " " + getBasicInformation().getName() + " " + getBasicInformation().getPatronymic();
        return fio.trim();

    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isCallTo() {
        return callTo;
    }

    public void setCallTo(boolean callTo) {
        this.callTo = callTo;
    }
    @NotNull(message = "Not null for CallTo")
    @AssertBoolean()
    @JsonSetter("callTo")
    public void setCallTo(String callTo) {
        if(callTo == null){
            System.out.println("Error of NOT_NULL");
        }
        if (!"true".equals(callTo.toLowerCase()) && !"false".equals(callTo.toLowerCase())) {
            System.out.println("Error of NOT TRUE OR FALSE");
            System.out.println(callTo);
        }
        this.callTo = "true".equals(callTo.toLowerCase());
    }
}
