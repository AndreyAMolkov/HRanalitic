package model;

import service.DataUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectData {
    private final String COMMENT = "comment";
    private final String DATE_OF_COMMENT = "dateOfComment";
    private final String DATE_TIME_INTERVIEW = "dateTimeInterview";
    private Loger logerInstance = new Loger();
    private int position;
    private Candidate candidate;

    private String fio;
    private String phone;
    private String birthday;
    private String initiative;
    private String source;
    private String project;
    private Boolean callTo;
    // result
    private String commentOfResult;
    private String dateOfCommentOfResult;
    private String dateTimeInterviewOfResult;
    //last call0
    private String commentOfCall0;
    private String dateOfCommentOfCall0;
    //last call1
    private String commentOfCall1;
    private String dateOfCommentOfCall1;
    //last call2
    private String commentOfCall2;
    private String dateOfCommentOfCall2;
    //last call3
    private String commentOfCall3;
    private String dateOfCommentOfCall3;
    //last call4
    private String commentOfCall4;
    private String dateOfCommentOfCall4;
    //last call5
    private String commentOfCall5;
    private String dateOfCommentOfCall5;
    //last call6
    private String commentOfCall6;
    private String dateOfCommentOfCall6;
    //reject interview
    private String rejectOfInterview;
    private String dateOfrejectOfInterview;

    //interview
    private String commentOfInterview;
    private String dateOfCommentOfInterview;
    private String dateOfInterview;
    //3 block
    private int estimateHR;
    private String commentOfHr;
    private String commentOfTest;
    private String generalResultOfInterview;
    //4block
    private String resultOfTraining;
    private String notPassedTraining;
    private String estimateOFCoach;
    private String resultOfAdaptation;

    public CollectData() {
    }

    public CollectData(Candidate candidate, Integer count) throws WrongInputExeption {
        this.candidate = candidate;
        BasicInformation basicInformation = candidate.getBasicInformation();
        this.position = count;
        setFio(candidate);
        setPhone(basicInformation);
        setBirthday(basicInformation);
        this.initiative = candidate.getInitiative();
        this.source = candidate.getSource();
        this.project = candidate.getProject();
        setResultOfCandidate(candidate.getResult());
        setCall(candidate.getEventMap());
        this.callTo = candidate.isCallTo();
        setInterview(candidate.getEventMap());
        setEstimateHR(candidate.getEventMap());
        this.commentOfHr = setOnePositionOfEvent(Constants.COMMENT_HR);
        this.commentOfTest = setOnePositionOfEvent(Constants.RESULT_OF_TEST);
        this.generalResultOfInterview = setOnePositionOfEvent(Constants.RESULT_OF_INTERVIEW_GENERAL);
        this.resultOfTraining = setOnePositionOfEvent(Constants.RESULT_OF_TRAINING);
        this.notPassedTraining = setOnePositionOfEvent(Constants.NOT_PASSED_TRAINING);
        this.estimateOFCoach = setOnePositionOfEvent(Constants.COMMENT_ESTIMATE_OF_COACH);
        this.resultOfAdaptation = setOnePositionOfEvent(Constants.RESULT_OF_ADAPTATION);
    }

    private String setOnePositionOfEvent(String key) throws WrongInputExeption {
        Map <String, EventContact> eventMap = candidate.getEventMap();
        EventContact eventContact = getEventContact(eventMap, key);
        if (eventContact != null) {
            return eventContact.getComment();
        }
        return "";
    }

    private void setInterview(Map <String, EventContact> eventMap) {
        Map <String, String> map;
        EventContact eventContact = getEventContact(eventMap, Constants.INTERVIEW);
        if (eventContact != null) {
            map = getmapFromEvent(eventContact);
            if (map.get(COMMENT).equals(AnswerOfCallConversation.INTERVIEW.getName())) {
                setCommentOfInterview(map.get(COMMENT));
                setDateOfCommentOfInterview(map.get(DATE_OF_COMMENT));
                setDateOfInterview(map.get(DATE_TIME_INTERVIEW));
            } else {
                setRejectOfInterview(map.get(COMMENT));
                setDateOfRejectOfInterview(map.get(DATE_OF_COMMENT));
            }

        }
    }

    private void setCall(Map <String, EventContact> eventMap) {
        setCall0(eventMap, 0);
        setCall1(eventMap, 1);
        setCall2(eventMap, 2);
        setCall3(eventMap, 3);
        setCall4(eventMap, 4);
        setCall5(eventMap, 5);
        setCall6(eventMap, 6);
    }

    private void setCall0(Map <String, EventContact> eventMap, int position) {
        EventContact eventContact = getEventContact(eventMap, Constants.CALL + position);
        if (eventContact == null) {
            return;
        }
        Map <String, String> map = getmapFromEvent(eventContact);
        setCommentOfCall0(map.get(COMMENT));
        setDateOfCommentOfCall0(map.get(DATE_OF_COMMENT));
    }

    private void setCall1(Map <String, EventContact> eventMap, int position) {
        EventContact eventContact = getEventContact(eventMap, Constants.CALL + position);
        if (eventContact == null) {
            return;
        }
        Map <String, String> map = getmapFromEvent(eventContact);
        setCommentOfCall1(map.get(COMMENT));
        setDateOfCommentOfCall1(map.get(DATE_OF_COMMENT));
    }

    private void setCall2(Map <String, EventContact> eventMap, int position) {
        EventContact eventContact = getEventContact(eventMap, Constants.CALL + position);
        if (eventContact == null) {
            return;
        }
        Map <String, String> map = getmapFromEvent(eventContact);
        setCommentOfCall2(map.get(COMMENT));
        setDateOfCommentOfCall2(map.get(DATE_OF_COMMENT));
    }

    private void setCall3(Map <String, EventContact> eventMap, int position) {
        EventContact eventContact = getEventContact(eventMap, Constants.CALL + position);
        if (eventContact == null) {
            return;
        }
        Map <String, String> map = getmapFromEvent(eventContact);
        setCommentOfCall3(map.get(COMMENT));
        setDateOfCommentOfCall3(map.get(DATE_OF_COMMENT));
    }

    private void setCall4(Map <String, EventContact> eventMap, int position) {
        EventContact eventContact = getEventContact(eventMap, Constants.CALL + position);
        if (eventContact == null) {
            return;
        }
        Map <String, String> map = getmapFromEvent(eventContact);
        setCommentOfCall4(map.get(COMMENT));
        setDateOfCommentOfCall4(map.get(DATE_OF_COMMENT));
    }

    private void setCall5(Map <String, EventContact> eventMap, int position) {
        EventContact eventContact = getEventContact(eventMap, Constants.CALL + position);
        if (eventContact == null) {
            return;
        }
        Map <String, String> map = getmapFromEvent(eventContact);
        setCommentOfCall5(map.get(COMMENT));
        setDateOfCommentOfCall5(map.get(DATE_OF_COMMENT));
    }

    private void setCall6(Map <String, EventContact> eventMap, int position) {
        EventContact eventContact = getEventContact(eventMap, Constants.CALL + position);
        if (eventContact == null) {
            return;
        }
        Map <String, String> map = getmapFromEvent(eventContact);
        setCommentOfCall6(map.get(COMMENT));
        setDateOfCommentOfCall6(map.get(DATE_OF_COMMENT));
    }

    private EventContact getEventContact(Map <String, EventContact> map, String key) {
        //       try {
        if (map.isEmpty() || !map.containsKey(key)) {
            return null;
        }
        return map.get(key);
////            EventContact eventContact = OBJECTMAPPER.convertValue(map.get(key), EventContact.class);
//            Object object = map.get(key);
//            OBJECTMAPPER.findAndRegisterModules();
//            Map<String,String> test = (Map<String, String>) object;
//
//            test.keySet().size();
//            EventContact eventContact = OBJECTMAPPER.convertValue(object, EventContact.class);
//  //          EventContact eventContact = map.get(key);
//            return eventContact;
//        }catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

    }

    private void setResultOfCandidate(EventContact eventContact) {
        if (eventContact == null) {
            return;
        }
        Map <String, String> map = getmapFromEvent(eventContact);
        this.commentOfResult = map.get(COMMENT);
        this.dateOfCommentOfResult = map.get(DATE_OF_COMMENT);
        this.dateTimeInterviewOfResult = map.get(DATE_TIME_INTERVIEW);
    }

    private Map <String, String> getmapFromEvent(EventContact eventContact) {
        if (eventContact == null) {
            return null;
        }
        Map <String, String> map = new HashMap <>();
        map.put(COMMENT, eventContact.getComment());
        map.put(DATE_OF_COMMENT, eventContact.getDateTimeOfCommentString());
        map.put(DATE_TIME_INTERVIEW, eventContact.getDateTimeInterviewString());
        return map;
    }

    private void setBirthday(BasicInformation basicInformation) {
        if (basicInformation.getBirthday() != null) {
            this.birthday = basicInformation.getBirthdayString();
        }
    }

    private void setPhone(BasicInformation basicInformation) {
        List <String> list = basicInformation.getPhonesList();
        if (list != null)
            this.phone = list.get(0);
    }

    private void setFio(Candidate candidate) {
        this.fio = candidate.getFIO();
    }

    public String getDateOfCommentOfResult() {
        return dateOfCommentOfResult;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getInitiative() {
        return initiative;
    }

    public void setInitiative(String initiative) {
        this.initiative = initiative;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCommentOfResult() {
        return commentOfResult;
    }

    public void setCommentOfResult(String commentOfResult) {
        this.commentOfResult = commentOfResult;
    }

    public void setDateOfCommentOfResult(String dateOfCommentOfResult) {
        this.dateOfCommentOfResult = dateOfCommentOfResult;
    }

    public String getCommentOfCall0() {
        return commentOfCall0;
    }

    public void setCommentOfCall0(String commentOfCall0) {
        this.commentOfCall0 = commentOfCall0;
    }

    public LocalDateTime getDateOfCommentOfCall0() throws WrongInputExeption {
        try {
            return DataUtils.stringToLocalDate(dateOfCommentOfCall0);
        } catch (WrongInputExeption e) {
            return null;
        }

    }

    public void setDateOfCommentOfCall0(LocalDateTime dateOfCommentOfCall0) {
        this.dateOfCommentOfCall0 = DataUtils.convertLocalDateTimeToString(dateOfCommentOfCall0);
    }

    public void setDateOfCommentOfCall0(String dateOfCommentOfCall0) {
        this.dateOfCommentOfCall0 = dateOfCommentOfCall0;
    }

    public String getDateOfCommentOfInterview() {
        return dateOfCommentOfInterview;
    }

    public String getCommentOfInterview() {
        return commentOfInterview;
    }

    public void setCommentOfInterview(String commentOfInterview) {
        this.commentOfInterview = commentOfInterview;
    }

    public void setDateOfCommentOfInterview(LocalDateTime dateOfCommentOfInterview) {
        this.dateOfCommentOfInterview = DataUtils.convertLocalDateTimeToString(dateOfCommentOfInterview);
    }

    public void setDateOfCommentOfInterview(String dateOfCommentOfInterview) {
        this.dateOfCommentOfInterview = dateOfCommentOfInterview;
    }

    public String getDateOfInterview() {
//        loger("getDateOfInterview", dateOfInterview);
        return dateOfInterview;
    }

    public void setDateOfInterview(LocalDateTime dateOfInterview) {
        this.dateOfInterview = DataUtils.convertLocalDateTimeToString(dateOfInterview);
    }

    public void setDateOfInterview(String dateOfInterview) {
        this.dateOfInterview = dateOfInterview;
    }

    public void setEstimateHR(int estimateHR) {
        this.estimateHR = estimateHR;
    }

    public int getEstimateHR() {
        //       loger("getEstimateHR", Integer.toString(estimateHR));
        return estimateHR;
    }

    public void setCommentOfHr(String commentOfHr) {
        this.commentOfHr = commentOfHr;
    }

    public String getCommentOfTest() {
        return commentOfTest;
    }

    public void setCommentOfTest(String commentOfTest) {
        this.commentOfTest = commentOfTest;
    }

    private void setEstimateHR(Map <String, EventContact> eventMap) {
        EventContact eventContact = getEventContact(eventMap, Constants.ESTIMATE_OF_HR);
        if (eventContact == null) {
            return;
        }
        String estimate = eventContact.getComment();
        if (estimate != null) {
            this.estimateHR = Integer.parseInt(estimate);
        } else {
            this.estimateHR = 0;
        }


    }

    public String getCommentOfHr() {
//        loger("getCommentOfHr", commentOfHr);
        return commentOfHr;
    }

    public String getResultOfTraining() {
        return resultOfTraining;
    }

    public void setResultOfTraining(String resultOfTraining) {
        this.resultOfTraining = resultOfTraining;
    }

    public String getNotPassedTraining() {
        return notPassedTraining;
    }

    public void setNotPassedTraining(String notPassedTraining) {
        this.notPassedTraining = notPassedTraining;
    }

    public String getEstimateOFCoach() {
        return estimateOFCoach;
    }

    public void setEstimateOFCoach(String estimateOFCoach) {
        this.estimateOFCoach = estimateOFCoach;
    }

    public String getResultOfAdaptation() {
        return resultOfAdaptation;
    }

    public void setResultOfAdaptation(String resultOfAdaptation) {
        this.resultOfAdaptation = resultOfAdaptation;
    }

    public String getGeneralResultOfInterview() {
        return generalResultOfInterview;
    }

    public void setGeneralResultOfInterview(String generalResultOfInterview) {
        this.generalResultOfInterview = generalResultOfInterview;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public String getCOMMENT() {
        return COMMENT;
    }

    public Boolean getCallTo() {
        return callTo;
    }

    public void setCallTo(Boolean callTo) {
        this.callTo = callTo;
    }

    public String getDateTimeInterviewOfResult() {
        return dateTimeInterviewOfResult;
    }

    public void setDateTimeInterviewOfResult(String dateTimeInterviewOfResult) {
        this.dateTimeInterviewOfResult = dateTimeInterviewOfResult;
    }

    public String getCommentOfCall1() {
        return commentOfCall1;
    }

    public void setCommentOfCall1(String commentOfCall1) {
        this.commentOfCall1 = commentOfCall1;
    }

    public String getDateOfCommentOfCall1() {
        return dateOfCommentOfCall1;
    }

    public void setDateOfCommentOfCall1(String dateOfCommentOfCall1) {
        this.dateOfCommentOfCall1 = dateOfCommentOfCall1;
    }

    public String getCommentOfCall2() {
        return commentOfCall2;
    }

    public void setCommentOfCall2(String commentOfCall2) {
        this.commentOfCall2 = commentOfCall2;
    }

    public String getDateOfCommentOfCall2() {
        return dateOfCommentOfCall2;
    }

    public void setDateOfCommentOfCall2(String dateOfCommentOfCall2) {
        this.dateOfCommentOfCall2 = dateOfCommentOfCall2;
    }

    public String getCommentOfCall3() {
        return commentOfCall3;
    }

    public void setCommentOfCall3(String commentOfCall3) {
        this.commentOfCall3 = commentOfCall3;
    }

    public String getDateOfCommentOfCall3() {
        return dateOfCommentOfCall3;
    }

    public void setDateOfCommentOfCall3(String dateOfCommentOfCall3) {
        this.dateOfCommentOfCall3 = dateOfCommentOfCall3;
    }

    public String getCommentOfCall4() {
        return commentOfCall4;
    }

    public void setCommentOfCall4(String commentOfCall4) {
        this.commentOfCall4 = commentOfCall4;
    }

    public String getDateOfCommentOfCall4() {
        return dateOfCommentOfCall4;
    }

    public void setDateOfCommentOfCall4(String dateOfCommentOfCall4) {
        this.dateOfCommentOfCall4 = dateOfCommentOfCall4;
    }

    public String getCommentOfCall5() {
        return commentOfCall5;
    }

    public void setCommentOfCall5(String commentOfCall5) {
        this.commentOfCall5 = commentOfCall5;
    }

    public String getDateOfCommentOfCall5() {
        return dateOfCommentOfCall5;
    }

    public void setDateOfCommentOfCall5(String dateOfCommentOfCall5) {
        this.dateOfCommentOfCall5 = dateOfCommentOfCall5;
    }

    public String getCommentOfCall6() {
        return commentOfCall6;
    }

    public void setCommentOfCall6(String commentOfCall6) {
        this.commentOfCall6 = commentOfCall6;
    }

    public String getDateOfCommentOfCall6() {
        return dateOfCommentOfCall6;
    }

    public void setDateOfCommentOfCall6(String dateOfCommentOfCall6) {
        this.dateOfCommentOfCall6 = dateOfCommentOfCall6;
    }

    public String getRejectOfInterview() {
        return rejectOfInterview;
    }

    public void setRejectOfInterview(String rejectOfInterview) {
        this.rejectOfInterview = rejectOfInterview;
    }

    public String getDateOfRejectOfInterview() {
        return dateOfrejectOfInterview;
    }

    public void setDateOfRejectOfInterview(String dateOfRejectOfInterview) {
        this.dateOfrejectOfInterview = dateOfrejectOfInterview;
    }

    private void loger(String nameMethod, String message) {
        logerInstance.printMessage(nameMethod, message);
    }

    private String getMessage(String... args) {
        String s = "";
        for (int i = 0; i < args.length; i++)
            s += "%s ";
        return String.format(s, args);

    }
}
