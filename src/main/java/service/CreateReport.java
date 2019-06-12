package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Candidate;
import model.CollectData;
import model.Loger;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CreateReport {
    ComboBox <CheckMenuItem> comboBox;
    List <TableColumn> tableColumnListAll;
    private Loger logerInstance = new Loger();
    private List <CollectData> collectDataList;
    private List <TableColumn> tableColumnsListFirstBlock;
    private List <CheckBox> checkBoxListFirstBlock;
    private List <TableColumn> tableColumnsListSecondBlock;
    private List <CheckBox> checkBoxListSecondBlock;
    private List <TableColumn> tableColumnsListThirdBlock;
    private List <CheckBox> checkBoxListThirdBlock;
    private List <TableColumn> tableColumnsListForthBlock;
    private List <CheckBox> checkBoxListForthBlock;
    private TableView tableView;
    private TableColumn <CollectData, String> comment;
    private TableColumn <CollectData, String> dateOfComment;
    private TableColumn <CollectData, String> dateOfInterview;
    private ObservableList <CollectData> listObservable = FXCollections.observableArrayList();
    private List <CheckMenuItem> checkMenuItemList;

    public CreateReport() {
    }

    public CreateReport(TableView tableView) {
        this.tableView = tableView;
    }

    public CreateReport(TableView tableView, ObservableList <CollectData> listObservable, List <CollectData> collectDataList) {
        String nameMethod = "CreateReport";
        this.tableView = tableView;
        this.listObservable = listObservable;
        this.collectDataList = collectDataList;
        tableView.setItems(listObservable);
        listObservable.addAll(collectDataList);
        createFirstBlock(getTableColumnsListFirstBlock());
        createSecondBlock(getTableColumnsListSecondBlock());
        createThirdBlock(getTableColumnsListThirdBlock());
        createForthBlock(getTableColumnsListForthBlock());
    }

    private void createFirstBlock(List <TableColumn> tableColumnList) {
        String nameMethod = "CreateReport";
        loger(nameMethod, "start");

        TableColumn <CollectData, String> tbcPositionOfArray = new TableColumn <>("Номер");
        TableColumn <CollectData, String> tbcFIO = new TableColumn <>("ФИО");
        TableColumn <CollectData, String> tbcPhone = new TableColumn <>("ТЕЛЕФОН");
        TableColumn <CollectData, String> tbcBirthday = new TableColumn <>("ДЕНЬ рождения");
        TableColumn <CollectData, String> tbcSource = new TableColumn <>("Инициатива");
        TableColumn <CollectData, String> tbcInitiative = new TableColumn <>("Ресурс");
        TableColumn <CollectData, String> tbcProject = new TableColumn <>("Проект");

        tbcPositionOfArray.setCellValueFactory(new PropertyValueFactory <>("position"));
        tbcFIO.setCellValueFactory(new PropertyValueFactory <>("fio"));
        tbcBirthday.setCellValueFactory(new PropertyValueFactory <>("birthday"));
        tbcPhone.setCellValueFactory(new PropertyValueFactory <>("phone"));
        tbcSource.setCellValueFactory(new PropertyValueFactory <>("source"));
        tbcInitiative.setCellValueFactory(new PropertyValueFactory <>("initiative"));
        tbcProject.setCellValueFactory(new PropertyValueFactory <>("project"));
        tableColumnList.add(tbcPositionOfArray);
//        loger(nameMethod,tbcPositionOfArray);
//        loger(nameMethod,tbcFIO);
//        loger(nameMethod,tbcPhone);
//        loger(nameMethod,tbcSource);
//        loger(nameMethod,tbcInitiative);
        tableColumnList.add(tbcFIO);
        tableColumnList.add(tbcPhone);
        tableColumnList.add(tbcSource);
        tableColumnList.add(tbcInitiative);
        tableColumnList.add(tbcProject);

        tableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onChoose();
            }
        });
//        listObservable = dataUtiles.getLineList(listResult);
//        tableView.setItems(listObservable);
        tableView.getColumns().addAll(tableColumnList);
        tableColumnsListFirstBlock.addAll(tableColumnList);
        loger(nameMethod, "end");
    }

    private void createSecondBlock(List <TableColumn> tableColumnList) {
        String nameMethod = "createSecondBlock";
        loger(nameMethod, "start");
//        if(tableColumnsListSecondBlock ==null){
//            this.tableColumnsListSecondBlock = new ArrayList <>();
//        }
        //call to
        createEventBlock(tableColumnList, "callTo", null, null, "дозвон");
        createEventBlock(tableColumnList, "commentOfCall0", "dateOfCommentOfCall0", null, "Звонок 0");
        createEventBlock(tableColumnList, "commentOfCall1", "dateOfCommentOfCall1", null, "Звонок 1");
        createEventBlock(tableColumnList, "commentOfCall2", "dateOfCommentOfCall2", null, "Звонок 2");
        createEventBlock(tableColumnList, "commentOfCall3", "dateOfCommentOfCall3", null, "Звонок 3");
        createEventBlock(tableColumnList, "commentOfCall4", "dateOfCommentOfCall4", null, "Звонок 4");
        createEventBlock(tableColumnList, "commentOfCall5", "dateOfCommentOfCall5", null, "Звонок 5");
        createEventBlock(tableColumnList, "commentOfCall6", "dateOfCommentOfCall6", null, "Звонок 6");
        createEventBlock(tableColumnList, "commentOfInterview", "dateOfCommentOfInterview", "dateOfInterview", "Интервью");
        createEventBlock(tableColumnList, "rejectOfInterview", "dateOfRejectOfInterview", null, "Отказ от интервью");
        loger(nameMethod, "end");
    }

    private void createThirdBlock(List <TableColumn> tableColumnList) {
        String nameMethod = "createThirdBlock";
        loger(nameMethod, "start");
        createEventBlock(tableColumnList, "estimateHR", null, null, "Оценка HR");
        createEventBlock(tableColumnList, "commentOfHr", null, null, "Коментарий HR");
        createEventBlock(tableColumnList, "commentOfTest", null, null, "Результат теста");
        createEventBlock(tableColumnList, "generalResultOfInterview", null, null, "Результат интервью");
        loger(nameMethod, "end");
    }

    private void createForthBlock(List <TableColumn> tableColumnList) {
        String nameMethod = "createForthBlock";
        loger(nameMethod, "start");
        createEventBlock(tableColumnList, "resultOfTraining", null, null, "Результат тренинга");
        createEventBlock(tableColumnList, "notPassedTraining", null, null, "Не закончил тренинга");
        createEventBlock(tableColumnList, "estimateOFCoach", null, null, "Оценка Тренера");
        createEventBlock(tableColumnList, "resultOfAdaptation", null, null, "Результат адаптации");
        loger(nameMethod, "end");
    }


    private void createEventBlock(List <TableColumn> tableColumnList, String commentField, String dateField, String dateArrangementField, String name) {
        String nameMethod = "createEventBlock";
        String message = getMessage(commentField, dateField, dateArrangementField, name);
        loger(nameMethod, "for " + message);
        if (tableView == null) {
            return;
        }

        comment = new TableColumn <>(name);
        comment.setCellValueFactory(new PropertyValueFactory <>(commentField));

        List <TableColumn> list = new ArrayList <>();
        list.add(comment);
        loger(nameMethod, "comment: " + comment);
//        listObservable = dataUtiles.getLineList(listResult);
//        tableView.setItems(listObservable);
        if (!StringUtils.isEmpty(dateField)) {
            dateOfComment = new TableColumn <>(name + " дата создания");
            dateOfComment.setCellValueFactory(new PropertyValueFactory <>(dateField));
            list.add(dateOfComment);
            loger(nameMethod, "dateOfComment: " + dateOfComment);
        }
        if (!StringUtils.isEmpty(dateArrangementField)) {
            dateOfInterview = new TableColumn <>(name + " дата назначения");
            dateOfInterview.setCellValueFactory(new PropertyValueFactory <>(dateArrangementField));
            list.add(dateOfInterview);
            loger(nameMethod, "dateOfStart: " + dateOfInterview);
        }
        tableView.getColumns().addAll(list);
        tableColumnList.addAll(list);
    }

    public List <CheckMenuItem> createCheckBoxList(List <TableColumn> tableColumnList) {

        List <CheckMenuItem> checkMenuItemList = new ArrayList <>();
        for (TableColumn one : tableColumnList) {
            checkMenuItemList.add(new CheckMenuItem(one.getText()));
        }
        return checkMenuItemList;
    }

    public List <CheckMenuItem> createCheckBoxListAll() {
        tableColumnListAll = new ArrayList <>();
        tableColumnListAll.addAll(getTableColumnsListFirstBlock());
        tableColumnListAll.addAll(getTableColumnsListSecondBlock());
        tableColumnListAll.addAll(getTableColumnsListThirdBlock());
        tableColumnListAll.addAll(getTableColumnsListForthBlock());
        comboBox = new ComboBox <>();
        comboBox.setPromptText("Настройки отчета");
        comboBox.setEditable(true);
        checkMenuItemList = new ArrayList <>();
        EventHandler <ActionEvent> event = new EventHandler <ActionEvent>() {
            public void handle(ActionEvent e) {
                setVisibleTableColumn(e, ((CheckMenuItem) e.getSource()));
            }
        };

        for (TableColumn one : tableColumnListAll) {
            CheckMenuItem checkMenuItem = new CheckMenuItem(one.getText());
            checkMenuItem.setOnAction(event);
            checkMenuItemList.add(checkMenuItem);
        }
        return checkMenuItemList;
    }

    private void setVisibleTableColumn(Object obj, CheckMenuItem checkMenuItem) {
        int position = checkMenuItemList.indexOf(checkMenuItem);
//       loger("setVisibleTableColumn","checkMenuItem " + checkMenuItem.getText());
//        loger("setVisibleTableColumn","check position " + position);

        TableColumn tableColumn = tableColumnListAll.get(position);
        boolean visible = tableColumn.isVisible();
        tableColumn.setVisible(!visible);

    }

    //    private void setVisibleTableColumn(String name){
//        checkMenuItemList.
//        TableColumn tableColumn = tableColumnList.get(position);
//        boolean visible = tableColumn.isVisible();
//        tableColumn.setVisible(!visible);
//
//    }
    private void onChoose() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Candidate selectedPerson = (Candidate) tableView.getSelectionModel().getSelectedItem();
            openCandidate(selectedPerson.getPosition());
        }
    }

    private void openCandidate(int position) {
        //
        //
        //
        //
        //
        //
        //
        //

    }

    public List <TableColumn> getTableColumnsListFirstBlock() {
        if (tableColumnsListFirstBlock == null) {
            this.tableColumnsListFirstBlock = new ArrayList <>();
        }
        return tableColumnsListFirstBlock;
    }

    public void setTableColumnsListFirstBlock(List <TableColumn> tableColumnsListFirstBlock) {
        this.tableColumnsListFirstBlock = tableColumnsListFirstBlock;
    }

    public List <CheckBox> getCheckBoxListFirstBlock() {
        return checkBoxListFirstBlock;
    }

    public void setCheckBoxListFirstBlock(List <CheckBox> checkBoxListFirstBlock) {
        this.checkBoxListFirstBlock = checkBoxListFirstBlock;
    }

    public List <TableColumn> getTableColumnsListSecondBlock() {
        if (tableColumnsListSecondBlock == null) {
            this.tableColumnsListSecondBlock = new ArrayList <>();
        }
        return tableColumnsListSecondBlock;
    }

    public void setTableColumnsListSecondBlock(List <TableColumn> tableColumnsListSecondBlock) {
        this.tableColumnsListSecondBlock = tableColumnsListSecondBlock;
    }

    public List <CheckBox> getCheckBoxListSecondBlock() {
        return checkBoxListSecondBlock;
    }

    public void setCheckBoxListSecondBlock(List <CheckBox> checkBoxListSecondBlock) {
        this.checkBoxListSecondBlock = checkBoxListSecondBlock;
    }

    public List <TableColumn> getTableColumnsListThirdBlock() {
        if (tableColumnsListThirdBlock == null) {
            this.tableColumnsListThirdBlock = new ArrayList <>();
        }
        return tableColumnsListThirdBlock;
    }

    public void setTableColumnsListThirdBlock(List <TableColumn> tableColumnsListThirdBlock) {
        this.tableColumnsListThirdBlock = tableColumnsListThirdBlock;
    }

    public List <CheckBox> getCheckBoxListThirdBlock() {
        return checkBoxListThirdBlock;
    }

    public void setCheckBoxListThirdBlock(List <CheckBox> checkBoxListThirdBlock) {
        this.checkBoxListThirdBlock = checkBoxListThirdBlock;
    }

    public List <TableColumn> getTableColumnsListForthBlock() {
        if (tableColumnsListForthBlock == null) {
            this.tableColumnsListForthBlock = new ArrayList <>();
        }
        return tableColumnsListForthBlock;
    }

    public void setTableColumnsListForthBlock(List <TableColumn> tableColumnsListForthBlock) {
        this.tableColumnsListForthBlock = tableColumnsListForthBlock;
    }

    public List <CheckBox> getCheckBoxListForthBlock() {
        return checkBoxListForthBlock;
    }

    public void setCheckBoxListForthBlock(List <CheckBox> checkBoxListForthBlock) {
        this.checkBoxListForthBlock = checkBoxListForthBlock;
    }

    public TableView getTableView() {
        return tableView;
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    public TableColumn <CollectData, String> getComment() {
        return comment;
    }

    public void setComment(TableColumn <CollectData, String> comment) {
        this.comment = comment;
    }

    public TableColumn <CollectData, String> getDateOfComment() {
        return dateOfComment;
    }

    public void setDateOfComment(TableColumn <CollectData, String> dateOfComment) {
        this.dateOfComment = dateOfComment;
    }

    public TableColumn <CollectData, String> getDateOfInterview() {
        return dateOfInterview;
    }

    public void setDateOfInterview(TableColumn <CollectData, String> dateOfInterview) {
        this.dateOfInterview = dateOfInterview;
    }

    public ObservableList <CollectData> getListObservable() {
        return listObservable;
    }

    public void setListObservable(ObservableList <CollectData> listObservable) {
        this.listObservable = listObservable;
    }

    private void loger(String nameMethod, TableColumn <CollectData, String> tableColumn) {
        String message = tableColumn.getText();
        loger(nameMethod, message);
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
