package com.example.proga_lab8.controllers;

import com.example.proga_lab8.NikolaususFX;
import com.example.proga_lab8.controllers.localObject.CityAndGovernor;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ResourceBundle;

public class TableController implements Initializable {

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
    private NikolaususFX nikolaususFX;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void setNikolaususFX(NikolaususFX nikolaususFX) {
        this.nikolaususFX = nikolaususFX;
    }

    public ObservableList<CityAndGovernor> getData() {
        ObservableList<CityAndGovernor> data = FXCollections.observableArrayList();
        for (int i = 0; i < 100; ++i) {
            data.add(new CityAndGovernor(i+1, "Москва", "10,30", LocalDate.of(1941, Month.DECEMBER, 10), 1512L, 12421L, 512, 199, 1, 1, 1, 1, "Крутой чел", LocalDate.of(2000, Month.APRIL, 12)));
        }
        return data;
    }

    public void create() {

    }

    public void change() {

    }

    public void delete() {

    }

    public void toMainMenu() {
        try {
            nikolaususFX.showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
