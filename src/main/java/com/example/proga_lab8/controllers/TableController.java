package com.example.proga_lab8.controllers;

import com.example.proga_lab8.NikolaususFX;
import com.example.proga_lab8.controllers.localObject.CityAndGovernor;
import com.example.proga_lab8.my_programm.enums.Climate;
import com.example.proga_lab8.my_programm.enums.StandardOfLiving;
import com.example.proga_lab8.my_programm.obj.City;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class TableController extends BaseController {

    @FXML private TableView<CityAndGovernor> tableView;
    @FXML private TableColumn<CityAndGovernor, Integer> city_idColumn;
    @FXML private TableColumn<CityAndGovernor, String> city_nameColumn;
    @FXML private TableColumn<CityAndGovernor, String> coordinatesColumn;
    @FXML private TableColumn<CityAndGovernor, LocalDate> creation_dateColumn;
    @FXML private TableColumn<CityAndGovernor, Long> areaColumn;
    @FXML private TableColumn<CityAndGovernor, Long> populationColumn;
    @FXML private TableColumn<CityAndGovernor, Integer> metersAboveSeaLevelColumn;
    @FXML private TableColumn<CityAndGovernor, Integer> carCodeColumn;
    @FXML private TableColumn<CityAndGovernor, Integer> climate_idColumn;
    @FXML private TableColumn<CityAndGovernor, Integer> standardOfLiving_idColumn;
    @FXML private TableColumn<CityAndGovernor, Integer> creator_idColumn;
    @FXML private TableColumn<CityAndGovernor, Integer> governor_idColumn;
    @FXML private TableColumn<CityAndGovernor, String> governor_nameColumn;
    @FXML private TableColumn<CityAndGovernor, LocalDate> birthdateColumn;

    @FXML
    private Button create_button;
    @FXML
    private Button change_button;
    @FXML
    private Button delete_button;
    @FXML
    private Button mainMenu_button;

    public void loadTable() {
        city_idColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, Integer>("city_id"));
        city_nameColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, String>("city_name"));
        coordinatesColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, String>("coordinates"));
        creation_dateColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, LocalDate>("creation_date"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, Long>("area"));
        populationColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, Long>("population"));
        metersAboveSeaLevelColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, Integer>("metersAboveSeaLevel"));
        carCodeColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, Integer>("carCode"));
        climate_idColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, Integer>("climate_id"));
        standardOfLiving_idColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, Integer>("standardOfLiving_id"));
        creator_idColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, Integer>("creator_id"));
        governor_idColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, Integer>("governor_id"));
        governor_nameColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, String>("governor_name"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<CityAndGovernor, LocalDate>("birthdate"));

        tableView.setItems(this.getData());
    }

    public ObservableList<CityAndGovernor> getData() {
        ObservableList<CityAndGovernor> data = FXCollections.observableArrayList();

        for (Map.Entry<Integer, City> entry : nikolaususFX.getClient().getTable().entrySet()) {
            City city = entry.getValue();
            data.add(new CityAndGovernor(city.getId(), city.getName(), city.getCoordinates(), nikolaususFX.fmt.format(city.getCreationDate()),
                    city.getArea(), city.getPopulation(), city.getMetersAboveSeaLevel(), city.getCarCode(), Climate.getIdByName(city.getClimate()),
                    StandardOfLiving.getIdByName(city.getStandardOfLiving()), city.getCreator_id(), null, null, null));
        }

        return data;
    }

    public void create() {
        System.out.println("Открыть окошко создания");
    }

    public void change() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            System.out.println("Открыть окошко создание города (но только на изменение и если есть права) " + tableView.getSelectionModel().getSelectedItem().getCity_id());
        }
    }

    public void delete() {
//        System.out.println("Удалить город (но только если есть права) " + tableView.getSelectionModel().getSelectedItem().getCity_id());
        nikolaususFX.getClient().add_message("remove_key " + tableView.getSelectionModel().getSelectedItem().getCity_id());
        Date dateNow = new Date();
        while (nikolaususFX.result_of_delete == null) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {}

            if (new Date().getTime() - dateNow.getTime() > 1000) {
                nikolaususFX.result_of_delete = "error " + "Сервер умер";
            }
        }
        this.callAlert("Ответ", nikolaususFX.result_of_delete.substring(nikolaususFX.result_of_delete.split(" ")[0].length() + 1));
        nikolaususFX.result_of_delete = null;
        this.loadTable();
    }

    public void toMainMenu() {
        try {
            nikolaususFX.showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
