package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.pojo.CollectData;
import model.pojo.Logger;
import org.apache.commons.lang3.StringUtils;
import service.property.Properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateListCandidates {
    private List<String> currentCheckedListSetting;
    //    private ComboBox<CheckMenuItem> comboBox;
    private List<TableColumn> tableColumnListAll;
    private Logger loggerInstance = new Logger();
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
    private List<CustomMenuItem> checkMenuItemList;
    private int counterWorkaround;

    public CreateListCandidates() {
        currentCheckedListSetting = new ArrayList<>();
    }

    public CreateListCandidates(TableView tableView) {
        currentCheckedListSetting = new ArrayList<>();
        this.tableView = tableView;
    }

    public CreateListCandidates(TableView tableView, ObservableList<CollectData> listObservable, List<CollectData> collectDataList) {
        currentCheckedListSetting = new ArrayList<>();
        String nameMethod = "CreateListCandidates";
        this.tableView = tableView;
        this.listObservable = listObservable;
        tableView.setItems(listObservable);
        this.listObservable.addAll(collectDataList);

        createFirstBlock();
        createSecondBlock();
        createThirdBlock();
        createForthBlock();
    }

    private void createFirstBlock() {
        String nameMethod = "CreateListCandidates";
//        logger(nameMethod, "start");
        getTableColumnsListFirstBlock();
        TableColumn <CollectData, String> tbcPositionOfArray = new TableColumn <>("Номер");
        TableColumn <CollectData, String> tbcFIO = new TableColumn <>("ФИО");
        TableColumn<CollectData, String> tbcPhone = new TableColumn<>("Телефон");
        TableColumn<CollectData, String> tbcBirthday = new TableColumn<>("День рождения");
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
        tableColumnsListFirstBlock.add(tbcPositionOfArray);
        tableColumnsListFirstBlock.add(tbcFIO);
        tableColumnsListFirstBlock.add(tbcBirthday);
        tableColumnsListFirstBlock.add(tbcPhone);
        tableColumnsListFirstBlock.add(tbcSource);
        tableColumnsListFirstBlock.add(tbcInitiative);
        tableColumnsListFirstBlock.add(tbcProject);

//        tableView.setOnMouseClicked((MouseEvent event) -> {
//            if (event.getClickCount() > 1) {
//                onChoose();
//            }
//        });
//        listObservable = dataUtiles.getLineList(listResult);
//        tableView.setItems(listObservable);
        tableView.getColumns().addAll(tableColumnsListFirstBlock);
        //       this.tableColumnsListFirstBlock.addAll(tableColumnList);
//        logger(nameMethod, "end");

    }

    private void createSecondBlock() {
        String nameMethod = "createSecondBlock";
//        logger(nameMethod, "start");
        getTableColumnsListSecondBlock();
        createEventBlock(tableColumnsListSecondBlock, "callTo", null, null, "дозвон");
        createEventBlock(tableColumnsListSecondBlock, "commentOfCall0", "dateOfCommentOfCall0", null, "Звонок 0");
        createEventBlock(tableColumnsListSecondBlock, "commentOfCall1", "dateOfCommentOfCall1", null, "Звонок 1");
        createEventBlock(tableColumnsListSecondBlock, "commentOfCall2", "dateOfCommentOfCall2", null, "Звонок 2");
        createEventBlock(tableColumnsListSecondBlock, "commentOfCall3", "dateOfCommentOfCall3", null, "Звонок 3");
        createEventBlock(tableColumnsListSecondBlock, "commentOfCall4", "dateOfCommentOfCall4", null, "Звонок 4");
        createEventBlock(tableColumnsListSecondBlock, "commentOfCall5", "dateOfCommentOfCall5", null, "Звонок 5");
        createEventBlock(tableColumnsListSecondBlock, "commentOfCall6", "dateOfCommentOfCall6", null, "Звонок 6");
        createEventBlock(tableColumnsListSecondBlock, "commentOfInterview", "dateOfCommentOfInterview", "dateOfInterview", "Интервью");
        createEventBlock(tableColumnsListSecondBlock, "rejectOfInterview", "dateOfRejectOfInterview", null, "Отказ от интервью");
        //       logger(nameMethod, "end");
    }

    private void createThirdBlock() {
        String nameMethod = "createThirdBlock";
//        logger(nameMethod, "start");
        getTableColumnsListThirdBlock();
        createEventBlock(tableColumnsListThirdBlock, "estimateHR", null, null, "Оценка HR");
        createEventBlock(tableColumnsListThirdBlock, "commentOfHr", null, null, "Коментарий HR");
        createEventBlock(tableColumnsListThirdBlock, "commentOfTest", null, null, "Результат теста");
        createEventBlock(tableColumnsListThirdBlock, "generalResultOfInterview", null, null, "Результат интервью");
//        logger(nameMethod, "end");
    }

    private void createForthBlock() {
        String nameMethod = "createForthBlock";
//        logger(nameMethod, "start");
        getTableColumnsListForthBlock();
        createEventBlock(tableColumnsListForthBlock, "resultOfTraining", null, null, "Результат тренинга");
        createEventBlock(tableColumnsListForthBlock, "notPassedTraining", null, null, "Не закончил тренинга");
        createEventBlock(tableColumnsListForthBlock, "estimateOFCoach", null, null, "Оценка Тренера");
        createEventBlock(tableColumnsListForthBlock, "resultOfAdaptation", null, null, "Результат адаптации");
//        logger(nameMethod, "end");
    }


    private void createEventBlock(List <TableColumn> tableColumnList, String commentField, String dateField, String dateArrangementField, String name) {
        String nameMethod = "createEventBlock";
        String message = getMessage(commentField, dateField, dateArrangementField, name);
        //       logger(nameMethod, "for " + message);
        if (tableView == null) {
            return;
        }

        comment = new TableColumn <>(name);
        comment.setCellValueFactory(new PropertyValueFactory <>(commentField));

        List <TableColumn> list = new ArrayList <>();
        list.add(comment);
//        logger(nameMethod, "comment: " + comment);
//        listObservable = dataUtiles.getLineList(listResult);
//        tableView.setItems(listObservable);
        if (!StringUtils.isEmpty(dateField)) {
            dateOfComment = new TableColumn <>(name + " дата создания");
            dateOfComment.setCellValueFactory(new PropertyValueFactory <>(dateField));
            list.add(dateOfComment);
            //           logger(nameMethod, "dateOfComment: " + dateOfComment);
        }
        if (!StringUtils.isEmpty(dateArrangementField)) {
            dateOfInterview = new TableColumn <>(name + " дата назначения");
            dateOfInterview.setCellValueFactory(new PropertyValueFactory <>(dateArrangementField));
            list.add(dateOfInterview);
            //           logger(nameMethod, "dateOfStart: " + dateOfInterview);
        }
        tableView.getColumns().addAll(list);
        tableColumnList.addAll(list);
    }

//    public List <CheckMenuItem> createCheckBoxList(List <TableColumn> tableColumnList) {
//
//        List <CheckMenuItem> checkMenuItemList = new ArrayList <>();
//        for (TableColumn one : tableColumnList) {
//            checkMenuItemList.add(new CheckMenuItem(one.getText()));
//        }
//        return checkMenuItemList;
//    }

    public List<CustomMenuItem> createCheckBoxListAll() {
        ComboBox<CheckMenuItem> comboBox;
        tableColumnListAll = new ArrayList <>();
        tableColumnListAll.addAll(getTableColumnsListFirstBlock());
        tableColumnListAll.addAll(getTableColumnsListSecondBlock());
        tableColumnListAll.addAll(getTableColumnsListThirdBlock());
        tableColumnListAll.addAll(getTableColumnsListForthBlock());
        comboBox = new ComboBox <>();
        comboBox.setPromptText("Настройки отчета");
        comboBox.setEditable(true);
        checkMenuItemList = new ArrayList <>();
        EventHandler<ActionEvent> eventVisible = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                setVisibleTableColumnFromCheckItem((CustomMenuItem) e.getSource());
            }
        };
        EventHandler<ActionEvent> eventSaveSettings = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                saveSettingsToProperty();
            }
        };
        CheckBox checkBox;
        List<String> listOfNameSettings = new ArrayList<>();
        String name = "";
        try {
            listOfNameSettings = Properties.getSettingForListCandidates();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (TableColumn one : tableColumnListAll) {
            name = one.getText();
            checkBox = new CheckBox(name);

            checkBox.setStyle("-fx-text-fill: black");
            CustomMenuItem checkMenuItem = new CustomMenuItem(checkBox);
            checkMenuItem.setText(name);
            checkMenuItem.setHideOnClick(false);
            checkMenuItem.setOnAction(eventVisible);
            checkMenuItemList.add(checkMenuItem);
            if (!name.isEmpty() && listOfNameSettings.contains(name)) {
                checkBox.setSelected(true);
            }
        }
        // add save settings checkBox
        checkBox = new CheckBox("Сохранить настройки");
        checkBox.setStyle("-fx-text-fill: red");
        CustomMenuItem checkMenuItem = new CustomMenuItem(checkBox);
        checkMenuItem.setOnAction(eventSaveSettings);
        checkMenuItem.setHideOnClick(false);
        checkMenuItemList.add(checkMenuItem);
        return checkMenuItemList;
    }

    private void saveSettingsToProperty() {
        try {
            Properties.saveProperties(Properties.LIST_CANDIDATES_NAME, currentCheckedListSetting);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setVisibleTableColumnFromCheckItem(CustomMenuItem checkMenuItem) {
        if (counterWorkaround == 1) {
            counterWorkaround = 0;
            return;
        }
        ++counterWorkaround;
//       logger("setVisibleTableColumn","checkMenuItem " + checkMenuItem.getText() );

//        logger("setVisibleTableColumn", "колонка:" + checkMenuItem.getText());
        setVisibleTableColumn(getPosition(checkMenuItem), checkMenuItem.getText(), !((CheckBox) checkMenuItem.getContent()).isSelected());
    }

    private void setVisibleTableColumn(Integer position, String name, boolean isVisible) {

        if (position == null) {
            logger("setVisibleTableColumn", "Error: Неуспешная настройка видимости колонки, позиция не найдена ");
            return;
        }
        //       logger("setVisibleTableColumn","check position " + position);
        TableColumn tableColumn = tableColumnListAll.get(position);

        //       logger("setVisibleTableColumn","tableColumn: " + tableColumn.getText() + " is " + tableColumn.isVisible());
        tableColumn.setVisible(isVisible);
        if (isVisible) {
            currentCheckedListSetting.remove(name);
        } else {
            currentCheckedListSetting.add(name);
        }

    }

    public void setVisibleTableColumn(String name, boolean isVisible) {
        logger("setVisibleTableColumn: ", name + " is " + isVisible);
        setVisibleTableColumn(getPosition(name), name, isVisible);

    }
    private Integer getPosition(CustomMenuItem checkMenuItem) {
        int max = checkMenuItemList.size();
        String expected = checkMenuItem.getText();
        String actual;
        for (int i = 0; i < max; i++) {
            actual = checkMenuItemList.get(i).getText();
            if (actual.equals(expected)) {
                return i;
            }
        }
        return null;
    }

    private Integer getPosition(String checkMenuItem) {
        int max = checkMenuItemList.size();
        String expected = checkMenuItem;
        String actual;
        for (int i = 0; i < max; i++) {
            actual = checkMenuItemList.get(i).getText();
            if (expected.equals(actual)) {
                return i;
            }
        }
        return null;
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

    private void logger(String nameMethod, TableColumn<CollectData, String> tableColumn) {
        String message = tableColumn.getText();
        logger(nameMethod, message);
    }

    private void logger(String nameMethod, String message) {
        loggerInstance.printMessage(nameMethod, message);
    }

    private String getMessage(String... args) {
        String s = "";
        for (int i = 0; i < args.length; i++)
            s += "%s ";
        return String.format(s, args);

    }

    public List<CustomMenuItem> getCheckMenuItemList() {
        return checkMenuItemList;
    }
}
