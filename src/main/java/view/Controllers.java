package view;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.constats.Constants;
import model.enums.*;
import model.exception.WrongInputException;
import model.pojo.BasicInformation;
import model.pojo.Candidate;
import model.pojo.CollectData;
import model.pojo.EventContact;
import org.apache.commons.lang3.StringUtils;
import service.CreateListCandidates;
import service.DataUtils;
import service.property.Properties;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


public class Controllers {
    @FXML
    TabPane generalTabPane;
    @FXML
    Tab tabCandidate;
    @FXML
    Tab tabListCandidates;
    @FXML
    public ComboBox <CheckMenuItem> comboBoxSettingReport;
    @FXML
    public ChoiceBox choiceBoxSettingReport;
    @FXML
    Label labelPosition;
    @FXML
    TableView <CollectData> tableViewCandidate;
    @FXML
    Pane paneStart;
    @FXML
    TextArea textAreaFIO;
    @FXML
    TextArea textAreaBirthday;
    @FXML
    TextArea textAreaPhone;
    @FXML
    TextField textFieldTimeForInterview;
    @FXML
    TextArea textAreaMessage;
    @FXML
    TextField textFieldSearch;
    @FXML
    ChoiceBox choiceBoxSource;
    @FXML
    ChoiceBox choiceBoxInitiative;
    @FXML
    ChoiceBox choiceBoxProject;
    @FXML
    ChoiceBox choiceBoxAnswerCall;
    @FXML
    ChoiceBox choiceBoxRejectFromInterview;
    @FXML
    RadioButton radioButtonCallYes;
    @FXML
    RadioButton radioButtonCallNo;
    @FXML
    Button buttonRecord;
    @FXML
    Button buttonEditFound;
    @FXML
    Button buttonSearch;
    @FXML
    Button buttonCreateCandidate;
    @FXML
    Button buttonTest;
    @FXML
    TableView <CollectData> tableViewReport;
    @FXML
    DatePicker datePickerForInterview;
    @FXML
    Tab tabPaneNow;
    @FXML
    Tab tabPane1;
    @FXML
    TextField textFieldPane1;
    @FXML
    Tab tabPane2;
    @FXML
    TextField textFieldPane2;
    @FXML
    Tab tabPane3;
    @FXML
    TextField textFieldPane3;
    @FXML
    Tab tabPane4;
    @FXML
    TextField textFieldPane4;
    @FXML
    Tab tabPane5;
    @FXML
    TextField textFieldPane5;
    @FXML
    Tab tabPane6;
    @FXML
    TextField textFieldPane6;
    //3 block
    @FXML
    ChoiceBox choiceBoxEstimateOfHr;
    @FXML
    TextArea textAreaCommentOfHr;
    @FXML
    TextArea textAreaCommentOfTest;
    @FXML
    ChoiceBox choiceBoxGeneralResult;
    //4block
    @FXML
    ChoiceBox choiceBoxResultOfTraining;
    @FXML
    RadioButton radioButton1;
    @FXML
    RadioButton radioButton2;
    @FXML
    RadioButton radioButton3;
    @FXML
    RadioButton radioButton4;
    @FXML
    RadioButton radioButton5;
    @FXML
    TextArea textAreaEstimateOFCoach;
    @FXML
    TextArea textAreaResultOfAdaptation;
    private List <RadioButton> radioButtonTrainingList;
    @FXML
    Menu menuReport;
    @FXML
    Button buttonReport;
    private ObservableList <CollectData> observableListCandidate = FXCollections.observableArrayList();
    private List <Candidate> listCandidate;
    private ToggleGroup groupCall;
    private ToggleGroup groupAdaptation;
    private List <CollectData> collectDataList;
    private int positionSearch;
    private boolean firstOpenListOfCandidates;
    private CreateListCandidates createTabOfCandidates;

    public static void autoResizeColumns(TableView<?> table) {
        //Set the right policy
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.getColumns().stream().forEach(column ->
        {
            //Minimal width = columnheader
            javafx.scene.text.Text t = new Text(column.getText());
            double max = t.getLayoutBounds().getWidth();
            for (int i = 0; i < table.getItems().size(); i++) {
                //cell must not be empty
                if (column.getCellData(i) != null) {
                    t = new Text(column.getCellData(i).toString());
                    double calcwidth = t.getLayoutBounds().getWidth();
                    //remember new max-width
                    if (calcwidth > max) {
                        max = calcwidth;
                    }
                }
            }
            //set the new max-widht with some extra space
            column.setPrefWidth(max + 10.0d);
        });
    }
    public void initialize() {
        firstOpenListOfCandidates = true;
        textAreaMessage.setWrapText(true);// the new line for a message
        radioButtonCallYes.setSelected(true);
        groupCall = new ToggleGroup();
        radioButtonCallNo.setToggleGroup(groupCall);
        radioButtonCallYes.setToggleGroup(groupCall);

        radioButton1.setSelected(true);
        groupAdaptation = new ToggleGroup();
        radioButton1.setToggleGroup(groupAdaptation);
        radioButton2.setToggleGroup(groupAdaptation);
        radioButton3.setToggleGroup(groupAdaptation);
        radioButton4.setToggleGroup(groupAdaptation);
        radioButton5.setToggleGroup(groupAdaptation);
        radioButtonTrainingList = new ArrayList <>();
        radioButtonTrainingList.add(radioButton1);
        radioButtonTrainingList.add(radioButton2);
        radioButtonTrainingList.add(radioButton3);
        radioButtonTrainingList.add(radioButton4);
        radioButtonTrainingList.add(radioButton5);

        onRadioButtonAnswerYes();
        choiceBoxSource.getItems().addAll(DataUtils.getListNameOfEnum(Source.values()));
        choiceBoxInitiative.getItems().addAll(DataUtils.getListNameOfEnum(Initiative.values()));
        choiceBoxProject.getItems().addAll(DataUtils.getListNameOfEnum(Project.values()));
        choiceBoxAnswerCall.getItems().addAll(DataUtils.getListNameOfEnum(ResultOfCall.values()));
        choiceBoxRejectFromInterview.getItems().addAll(DataUtils.getListNameOfEnum(AnswerOfCallConversation.values()));
        choiceBoxEstimateOfHr.getItems().addAll(DataUtils.getListNameOfEnum(EstimateOfHR.values()));
        choiceBoxGeneralResult.getItems().addAll(DataUtils.getListNameOfEnum(ResultInterview.values()));
        choiceBoxResultOfTraining.getItems().addAll(ResultOfAdaptation.PASSED.getName());
        listCandidate = new ArrayList <>(0);

        String message;
        try {
            listCandidate = DataUtils.convertStringJsonToCandidate(DataUtils.readFromFile(null));
            message = "программа готова к работе загруженно " + listCandidate.size() + " кандидатов ";


            //           tableViewCandidate.setItems(observableListCandidate);
            collectDataList = DataUtils.candidateToCollectData(listCandidate);
            this.createTabOfCandidates = createListCandidates();

        } catch (Exception e) {
            message = "ERROR: загрузка базы произошла с ошибками - " + e.toString();
            e.printStackTrace();
        }

        createMassage(message);

    }

    public void onCreateSettingTabCandidates() {
        String message = "Успешно применены настройки видимости списка кандидатов";
        if (firstOpenListOfCandidates) {
            List<String> list;
            try {
                list = Properties.getSettingForListCandidates();
                for (String one : list) {
                    createTabOfCandidates.setVisibleTableColumn(one, false);
                }
            } catch (IOException e) {
                message = "Error: Ошибка применения настроек видимости кандидатов: " + e;
            }
            createMassage(message);
            this.firstOpenListOfCandidates = false;
        }

    }
    public void onCreateCandidate() throws Exception {
        Candidate candidate;
        String source;
        String initiative;
        String project;
        try {
            Map <String, String> fio = DataUtils.getFIOfromString(textAreaFIO.getText());
            BasicInformation basicInformation = new BasicInformation(fio.get(Constants.SURNAME), fio.get(Constants.NAME), fio.get(Constants.PATRONYMIC), textAreaPhone.getText(), textAreaBirthday.getText());
            source = choiceBoxSource.getSelectionModel().getSelectedItem().toString();
            initiative = choiceBoxInitiative.getSelectionModel().getSelectedItem().toString();
            project = choiceBoxProject.getSelectionModel().getSelectedItem().toString();
            candidate = new Candidate(basicInformation, collectMapEvent(), source, initiative, project, radioButtonCallYes.isSelected());

//            if (tbcFIO.isVisible())
//                tbcFIO.setVisible(false);
//            else
//                tbcFIO.setVisible(true);

        } catch (Exception e) {
            createMassage(e.toString());
            throw new Exception(e);
        }
        Integer position;
        String positionSting = labelPosition.getText();

        if (!positionSting.isEmpty() && StringUtils.isNumeric(positionSting)) {
            position = Integer.parseInt(positionSting);
            rewriteCandidate(candidate);
            collectDataList.set(position, new CollectData(candidate, position));
        } else if (!DataUtils.isInclude(candidate, listCandidate)) {
            position = collectDataList.size() - 1;
            listCandidate.add(candidate);
            collectDataList.add(new CollectData(candidate, position));
        } else {
            CollectData collectData = DataUtils.getCollectDataViaCandidate(candidate, collectDataList);
            String message;
            if (collectData != null) {
                message = "Error: сандидат ранее был записан в базу как " + collectData.getFio() + ", номер " + collectData.getPosition();
                createMassage(message);
                throw new WrongInputException("Error: сандидат ранее был записан в базу как " + collectData.getFio() + ", номер " + collectData.getPosition());
            } else {
                message = "Error: не успешное создание кандидата";
                createMassage(message);
                throw new WrongInputException(message);
            }
        }

        if (!collectDataList.isEmpty()) {
            observableListCandidate.clear();
            observableListCandidate.addAll(collectDataList);
        }
        createMassage(candidate.toString());


    }

    public Map<String, EventContact> collectMapEvent() throws WrongInputException {
        Map <String, EventContact> result = new HashMap <>();

        if (choiceBoxAnswerCall.getValue() != null) {
            result.put(Constants.CALL + 0, DataUtils.createEvent(choiceBoxAnswerCall.getValue(), null, null));
        }
        createCallAnswerNo(Constants.CALL + 1, tabPane1.getText(), textFieldPane1.getText(), result);
        createCallAnswerNo(Constants.CALL + 2, tabPane2.getText(), textFieldPane2.getText(), result);
        createCallAnswerNo(Constants.CALL + 3, tabPane3.getText(), textFieldPane3.getText(), result);
        createCallAnswerNo(Constants.CALL + 4, tabPane4.getText(), textFieldPane4.getText(), result);
        createCallAnswerNo(Constants.CALL + 5, tabPane5.getText(), textFieldPane5.getText(), result);
        createCallAnswerNo(Constants.CALL + 6, tabPane6.getText(), textFieldPane6.getText(), result);

        // second of block
        String dateInterview = null;
        if (datePickerForInterview.getValue() != null) {
            dateInterview = datePickerForInterview.getValue().toString();
        }

        String timeInterview = textFieldTimeForInterview.getText();
        Object reject = choiceBoxRejectFromInterview.getSelectionModel().getSelectedItem();
        if (radioButtonCallYes.isSelected()) {
            if (!StringUtils.isEmpty(dateInterview) && !StringUtils.isEmpty(timeInterview)) {
                result.put(Constants.INTERVIEW, DataUtils.createEvent(AnswerOfCallConversation.INTERVIEW, dateInterview, timeInterview));
            } else if (reject != null && !StringUtils.isEmpty(reject.toString())) {
                result.put(Constants.INTERVIEW, DataUtils.createEvent(reject, null, null));
            } else
                throw new WrongInputException("Error: ввод пустого значения для собеседования");
        } else if (radioButtonCallNo.isSelected()) {
            if (choiceBoxAnswerCall.getSelectionModel().getSelectedItem() != null) {
                result.put(Constants.INTERVIEW, DataUtils.createEvent(choiceBoxAnswerCall.getSelectionModel().getSelectedItem(), dateInterview, timeInterview));
            } else
                throw new WrongInputException("Error: выберите причину");
        } else {
            throw new WrongInputException("Error: Не выбран результат дозвона - да или нет");
        }

        //3
        if (choiceBoxEstimateOfHr.getSelectionModel().getSelectedItem() != null) {
            result.put(Constants.ESTIMATE_OF_HR, DataUtils.createEvent(choiceBoxEstimateOfHr.getSelectionModel().getSelectedItem(), null, null));
        }

        if (!StringUtils.isEmpty(textAreaCommentOfHr.getText())) {
            isCommentOfHr();
            result.put(Constants.COMMENT_HR, DataUtils.createEvent(textAreaCommentOfHr.getText(), null, null));
        }

        if (!StringUtils.isEmpty(textAreaCommentOfTest.getText())) {
            isCommentOfHr();
            result.put(Constants.RESULT_OF_TEST, DataUtils.createEvent(textAreaCommentOfTest.getText(), null, null));
        }

        if (choiceBoxGeneralResult.getSelectionModel().getSelectedItem() != null) {
            isCommentOfHr();
            result.put(Constants.RESULT_OF_INTERVIEW_GENERAL, DataUtils.createEvent(choiceBoxGeneralResult.getSelectionModel().getSelectedItem(), null, null));
        }
        //4
        if (choiceBoxResultOfTraining.getValue() != null && isInterviewOk()) {
            result.put(Constants.RESULT_OF_TRAINING, DataUtils.createEvent(choiceBoxResultOfTraining.getSelectionModel().getSelectedItem(), null, null));
        }
        String notPassed;
        if ((notPassed = getWhyNotPassedTraining()) != null && isInterviewOk()) {
            result.put(Constants.NOT_PASSED_TRAINING, DataUtils.createEvent(notPassed, null, null));
        }
        if (!StringUtils.isEmpty(textAreaEstimateOFCoach.getText()) && isInterviewOk()) {
            result.put(Constants.COMMENT_ESTIMATE_OF_COACH, DataUtils.createEvent(textAreaEstimateOFCoach.getText(), null, null));
        }
        if (!StringUtils.isEmpty(textAreaResultOfAdaptation.getText()) && isInterviewOk()) {
            result.put(Constants.RESULT_OF_ADAPTATION, DataUtils.createEvent(textAreaResultOfAdaptation.getText(), null, null));
        }
        return result;
    }


    public void createCallAnswerNo(String nameNUmberTab, String date, String message, Map<String, EventContact> map) throws WrongInputException {
        if (!StringUtils.isEmpty(date) && !StringUtils.isEmpty(message)) {
            EventContact eventContact = DataUtils.createEvent(message, null, null);
            eventContact.setDateOfComment(date);
            map.put(nameNUmberTab, eventContact);
        }
    }

    public void onWriteToFile() throws JsonProcessingException {
        // 1 leg  - will be rewrite the whole file;
        boolean append = false;
        for (Candidate one : listCandidate) {
            DataUtils.writeToFile(one, null, append);
            append = true;
        }
        createMassage("загруженно " + listCandidate.size() + " кандидатов ");
        clearAll();
    }

    private void createMassage(String line) {
        Platform.runLater(() -> textAreaMessage.appendText(line + "\n"));
    }

    private void rewriteCandidate(Candidate candidate) throws WrongInputException {
        int position = Integer.parseInt(labelPosition.getText());
        if (listCandidate.get(position).getPosition() != position) {
            throw new WrongInputException("Error: сбой поиска номера кандидата, кандидат не записан");
        }
        listCandidate.set(position, candidate);
        createMassage(candidate.getFio() + " обновлён успешно");
    }

    public void clearAll() {
        textAreaFIO.setText("");
        textAreaBirthday.setText("");
        textAreaPhone.setText("");
        choiceBoxSource.valueProperty().setValue(null);
        choiceBoxInitiative.valueProperty().setValue(null);
        choiceBoxProject.valueProperty().setValue(null);
        clearFieldCall();
        clearCallBlock();
        clearDisableHRBlock(true, true);
        clearDisableTrainingBlock(true, true);
        labelPosition.setText("");
    }

    public void clearCallBlock() {

        choiceBoxAnswerCall.valueProperty().setValue(null);
        choiceBoxRejectFromInterview.valueProperty().setValue(null);
        datePickerForInterview.valueProperty().setValue(null);
        textFieldTimeForInterview.setText(null);
    }

    public void clearDisableHRBlock(boolean visible, boolean clear) {
        setVisibleClearChoiceBox(choiceBoxEstimateOfHr, visible, clear);
        setVisibleClearTextArea(textAreaCommentOfHr, visible, clear);
        setVisibleClearTextArea(textAreaCommentOfTest, visible, clear);
        setVisibleClearChoiceBox(choiceBoxGeneralResult, visible, clear);
    }

    public void clearDisableTrainingBlock(boolean visible, boolean clear) {
        setVisibleClearChoiceBox(choiceBoxResultOfTraining, visible, clear);
        clearRadioButtonTraining(visible, clear);
        setVisibleClearTextArea(textAreaEstimateOFCoach, visible, clear);
        setVisibleClearTextArea(textAreaResultOfAdaptation, visible, clear);
    }

    public void clearRadioButtonTraining(boolean visible, boolean clear) {

        for (RadioButton button : radioButtonTrainingList) {
            button.setSelected(!clear);
            button.setVisible(visible);
        }

    }

    public void onRadioButtonAnswerNo() {
        choiceBoxAnswerCall.setDisable(false);
        choiceBoxRejectFromInterview.setDisable(true);
        datePickerForInterview.setDisable(true);
        textFieldTimeForInterview.setDisable(true);
        clearCallBlock();
        clearDisableHRBlock(false, true);
        clearDisableTrainingBlock(false, true);
    }

    public void onRadioButtonAnswerYes() {
        choiceBoxAnswerCall.setDisable(true);
        choiceBoxRejectFromInterview.setDisable(false);
        datePickerForInterview.setDisable(false);
        datePickerForInterview.getEditor().clear();
        textFieldTimeForInterview.setDisable(false);
        clearCallBlock();
        clearDisableHRBlock(true, true);
        clearDisableTrainingBlock(false, true);
    }

    public void onResultInterview() {
        clearDisableTrainingBlock(true, true);
    }

    public void disableRejectInterview() {
        setVisibleClearChoiceBox(choiceBoxRejectFromInterview, true, true);
        clearDisableHRBlock(true, true);
        clearDisableTrainingBlock(true, true);
    }

    public void disableInterview() {
        setVisibleClearDatePicker(datePickerForInterview, true, true);
        setVisibleClearFieldArea(textFieldTimeForInterview, true, true);
        clearDisableHRBlock(false, true);
        clearDisableTrainingBlock(false, true);
    }

    private void setVisibleClearDatePicker(DatePicker datePicker, boolean visible, boolean clear) {
        datePicker.setVisible(visible);
        if (clear) {
            datePicker.getEditor().clear();
        }
    }

    private void setVisibleClearTextArea(TextArea textArea, boolean visible, boolean clear) {
        textArea.setDisable(!visible);
        if (clear) {
            textArea.setText("");
        }
    }

    private void setVisibleClearFieldArea(TextField textField, boolean visible, boolean clear) {
        textField.setDisable(!visible);
        if (clear) {
            textField.setText("");
        }
    }

    private void setVisibleClearChoiceBox(ChoiceBox choiceBox, boolean visible, boolean clear) {
        choiceBox.setDisable(!visible);
        if (clear) {
            choiceBox.valueProperty().setValue(null);
        }
    }

    public void loadedTestCandidate() {
        textAreaFIO.setText("Molkov Andrey Al");
        textAreaBirthday.setText("12.12.1981");
        textAreaPhone.setText("89784561232");
        choiceBoxSource.getSelectionModel().selectFirst();
        choiceBoxProject.getSelectionModel().selectFirst();
        choiceBoxInitiative.getSelectionModel().selectFirst();
    }

    private void clearFieldCall() {

        tabPane1.setText("");
        textFieldPane1.setText("");
        tabPane2.setText("");
        textFieldPane2.setText("");
        tabPane3.setText("");
        textFieldPane3.setText("");
        tabPane4.setText("");
        textFieldPane4.setText("");
        tabPane5.setText("");
        textFieldPane5.setText("");
        tabPane6.setText("");
        textFieldPane6.setText("");


    }

    public void loadedCandidate(Candidate candidate, CollectData collectData) throws WrongInputException {
        labelPosition.setText(String.valueOf(collectData.getPosition()));
        textAreaFIO.setText(collectData.getFio());
        textAreaBirthday.setText(collectData.getBirthday());
        textAreaPhone.setText(collectData.getPhone());

        setPositionChoiceBox(choiceBoxSource, collectData.getSource(), Source.values());
        setPositionChoiceBox(choiceBoxProject, collectData.getProject(), Project.values());
        setPositionChoiceBox(choiceBoxInitiative, collectData.getInitiative(), Initiative.values());
        //secondBlock
        choiceBoxAnswerCall.setValue(collectData.getCallTo());
        if (collectData.getCommentOfInterview() != null) {
            LocalDateTime localDateTime = DataUtils.stringToLocalDate(collectData.getDateOfInterview());
            String time = LocalTime.of(localDateTime.getHour(), localDateTime.getMinute()).toString();
            textFieldTimeForInterview.setText(time);
            datePickerForInterview.setValue(localDateTime.toLocalDate());
            disableRejectInterview();
        }

        if (candidate.isCallTo()) {
            radioButtonCallYes.setSelected(true);
            prepareFieldCallYes(collectData);
        } else {
            radioButtonCallNo.setSelected(true);
            onRadioButtonAnswerNo();
            prepareFieldCallNo(collectData);
        }


        //thirdBlock
        textAreaCommentOfHr.setText(collectData.getCommentOfHr());
        choiceBoxEstimateOfHr.getSelectionModel().select(collectData.getEstimateHR() - 1);
        textAreaCommentOfTest.setText(collectData.getCommentOfTest());
        setPositionChoiceBox(choiceBoxGeneralResult, collectData.getGeneralResultOfInterview(), AnswerOfCallConversation.values());
//        if(choiceBoxGeneralResult.isShowing()){
//            disableRejectInterview();
//        }
        //forthBlock
        textAreaEstimateOFCoach.setText(collectData.getEstimateOFCoach());
        textAreaResultOfAdaptation.setText(collectData.getResultOfAdaptation());
        setPositionChoiceBox(choiceBoxGeneralResult, collectData.getGeneralResultOfInterview(), ResultInterview.values());
        setCheckBoxFall(radioButtonTrainingList, collectData.getGeneralResultOfInterview(), ResultInterview.values());
        setPositionChoiceBox(choiceBoxResultOfTraining, collectData.getResultOfTraining(), ResultOfAdaptation.values());

    }

    private void prepareFieldCallNo(CollectData collectData) {
        tabPane1.setText(collectData.getDateOfCommentOfCall0());
        textFieldPane1.setText(collectData.getCommentOfCall0());
        tabPane2.setText(collectData.getDateOfCommentOfCall1());
        textFieldPane2.setText(collectData.getCommentOfCall1());
        tabPane3.setText(collectData.getDateOfCommentOfCall2());
        textFieldPane3.setText(collectData.getCommentOfCall2());
        tabPane4.setText(collectData.getDateOfCommentOfCall3());
        textFieldPane4.setText(collectData.getCommentOfCall3());
        tabPane5.setText(collectData.getDateOfCommentOfCall4());
        textFieldPane5.setText(collectData.getCommentOfCall4());
        tabPane6.setText(collectData.getDateOfCommentOfCall5());
        textFieldPane6.setText(collectData.getCommentOfCall5());
    }
    private void setCheckBoxFall(List <RadioButton> groupRadioButtonList, String line, ResultInterview[] values) {
        String buttonName;
        for (RadioButton button : groupRadioButtonList) {
            buttonName = button.getText();
            if (line.contains(buttonName)) {
                button.setSelected(true);
            }
        }
    }

    private void setPositionChoiceBox(ChoiceBox choiceBox, String line, EnumInterface[] enumInterfaceList) {
        this.positionSearch = 0;
        String param = "";
        String buf;
        try {
            for (EnumInterface enumInterface : enumInterfaceList) {

                buf = enumInterface.findName(line);

                if (buf != null) {
                    param = buf;
                }
            }
            for (ListIterator it = choiceBox.getItems().listIterator(); it.hasNext(); ) {
                Object obj = it.next();
                if (param.contains(obj.toString())) {
                    choiceBox.getSelectionModel().select(positionSearch);
                    return;
                }
                positionSearch++;
            }
        } catch (WrongInputException wrongInputException) {
            createMassage(wrongInputException.getMessage() + ": " + line + ": " + choiceBox);
        }
    }


    private String getWhyNotPassedTraining() {
        String result = null;
        for (RadioButton radioButton : radioButtonTrainingList) {
            if (radioButton.isSelected()) {
                result = radioButton.getText();
            }
        }
        return result;
    }

    public boolean isInterviewOk() {
        boolean result = false;

        Object object = choiceBoxGeneralResult.getSelectionModel().getSelectedItem();
        if (object != null && object.toString().equals(ResultInterview.TRAINING.getName())) {
            result = true;
        } else {
            clearDisableTrainingBlock(false, true);
        }
        return result;
    }

    public void onRadioButtonTrainingFall() {
        setVisibleClearChoiceBox(choiceBoxResultOfTraining, true, true);
        isInterviewOk();
    }

    public void onChoiceBoxTrainingOk() {
        clearRadioButtonTraining(true, true);
        isInterviewOk();
    }

    private void prepareFieldCallYes(CollectData collectData) {
        tabPane1.setText(collectData.getDateOfCommentOfCall6());
        textFieldPane1.setText(collectData.getCommentOfCall6());
        tabPane2.setText(collectData.getDateOfCommentOfCall1());
        textFieldPane2.setText(collectData.getCommentOfCall1());
        tabPane3.setText(collectData.getDateOfCommentOfCall2());
        textFieldPane3.setText(collectData.getCommentOfCall2());
        tabPane4.setText(collectData.getDateOfCommentOfCall3());
        textFieldPane4.setText(collectData.getCommentOfCall3());
        tabPane5.setText(collectData.getDateOfCommentOfCall4());
        textFieldPane5.setText(collectData.getCommentOfCall4());
        tabPane6.setText(collectData.getDateOfCommentOfCall5());
        textFieldPane6.setText(collectData.getCommentOfCall5());
    }

    public boolean isCommentOfHr() throws WrongInputException {
        Object object = choiceBoxEstimateOfHr.getSelectionModel().getSelectedItem();
        if (object == null) {
            createMassage("ERROR: Не проставлена оценка HR  в третьем блоке");
            throw new WrongInputException("ERROR: Не проставлена оценка HR  в третьем блоке");
        }
        return true;
    }

    public CreateListCandidates createListCandidates() throws IOException {
        removeTabAll();
        ObservableList <CollectData> observableList = FXCollections.observableArrayList();
        CreateListCandidates createListCandidates = new CreateListCandidates(tableViewReport, observableList, collectDataList);
        menuReport.getItems().addAll(createListCandidates.createCheckBoxListAll());
        ObservableList<CustomMenuItem> items = FXCollections.observableArrayList();
        items.addAll(createListCandidates.getCheckMenuItemList());
        addEvent();
        autoResizeColumns(tableViewReport);
        return createListCandidates;
    }

    private void addEvent() {
        tableViewReport.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onChoose();
                generalTabPane.getSelectionModel().select(0);// choose the tab for rewrite candidate

            }
        });
    }

    private Candidate getCandidate(int position) {
        return listCandidate.get(position);
    }

    private void onChoose() {
        if (tableViewReport.getSelectionModel().getSelectedItem() != null) {
            CollectData selectedPerson = tableViewReport.getSelectionModel().getSelectedItem();
            Candidate candidate = getCandidate(selectedPerson.getPosition());
            String message = "done";
            try {
                clearAll();
                loadedCandidate(candidate, selectedPerson);
            } catch (Exception e) {
                message = "ERROR: загрузка кандидата " + candidate.getFio() + " произошла с ошибками - " + e.toString();
                e.printStackTrace();
            }
            createMassage(message);
        }

    }

    private void removeTabAll() {
        ObservableList<TableColumn<CollectData, ?>> list = tableViewReport.getColumns();
        if (list.size() > 1) {
            list.remove(1, list.size());
        }

    }

//    public void fileOpen() {
//        File directory = getPathFromDirectoryChooser();
//        if (directory == null || !directory.exists()) {
//            return;
//        }
//        textFieldSource.setText(directory.getPath());
//    }

//    private File getPathFromDirectoryChooser() {
//        DirectoryChooser directoryChooser = new DirectoryChooser();
//        directoryChooser.setInitialDirectory(new File(basePath));
//        return directoryChooser.showDialog(null);
//    }

//    public void onCreateBasePathSetting() throws IOException {
//        File directory = getPathFromDirectoryChooser();
//
//        Properties.saveProperties(BASE_PATH, directory.getPath());
//        Properties.saveProperties(EXTENSION_FILE, Boolean.toString(checkBoxJava.isSelected()));
//        Properties.saveProperties(TO_LOWER_CASE, Boolean.toString(checkBoxLowCase.isSelected()));
//        Properties.saveProperties(PATH_WITH_TEST, Boolean.toString(checkBoxPathWithTest.isSelected()));
//        Properties.saveProperties(DON_T_USE_EXTENSION, textFieldDontUseExtesion.getText().trim());
//        basePath = directory.getPath();
//        textFieldBasePath.setText(basePath);
//        textFieldSource.setText(basePath);
//    }

}
