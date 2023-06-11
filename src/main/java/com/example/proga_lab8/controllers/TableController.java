package com.example.proga_lab8.controllers;

import com.example.proga_lab8.controllers.localObject.CityAndGovernor;
import com.example.proga_lab8.my_program.enums.Climate;
import com.example.proga_lab8.my_program.enums.StandardOfLiving;
import com.example.proga_lab8.my_program.obj.City;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class TableController extends BaseController {

    public Pane mapUI;
    public Button back_button;
    public Button delete_button2;
    public TextField inputName;
    public TextField inputArea;
    public TextField inputCoordinates;
    public TextField inputPopulation;
    public TextField inputMetersAboveSeaLevel;
    public TextField inputCarCode;
    public ChoiceBox inputClimate;
    public ChoiceBox inputStandardOfLiving;
    public Label outputId;
    public Label outputCreatorId;
    public Label outputCreationDate;
    public Button save_button;
    public Pane editPane;
    public Label propName;
    public Label propId;
    public Label propArea;
    public Label propCoords;
    public Label propPopulation;
    public Label propMASL;
    public Label propCarCode;
    public Label propClimate;
    public Label propStandardOfLiving;
    public Label propCreator;
    public Label propCreationDate;
    public Label labelCity;
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
    private boolean needCreate;

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

    public void onCreate() {
        needCreate = true;
        delete_button2.setVisible(false);
        outputId.setText("?");
        outputCreatorId.setText(nikolaususFX.getClient().getId());
        outputCreationDate.setText("?");
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(mapUI.layoutXProperty(), 0)
                ),
                new KeyFrame(new Duration(250),
                        new KeyValue(mapUI.layoutXProperty(), -1280)
                )
        );
        timeline.play();
    }

    public void onEdit() {
        needCreate = false;
        delete_button2.setVisible(true);
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            City city = nikolaususFX.getClient().get_city_by_id(tableView.getSelectionModel().getSelectedItem().getCity_id());

            if (city != null) {
                inputName.setText(city.getName());
                outputId.setText(city.getId().toString());
                inputArea.setText(Long.toString(city.getArea()));
                inputCoordinates.setText(city.getCoordinates());
                inputPopulation.setText(city.getPopulation().toString());
                inputMetersAboveSeaLevel.setText(city.getMetersAboveSeaLevel().toString());
                inputCarCode.setText(Integer.toString(city.getCarCode()));
                inputClimate.setValue(city.getClimate().toString());
                inputStandardOfLiving.setValue(city.getStandardOfLiving().toString());
                outputCreatorId.setText(city.getCreator_id().toString());
                outputCreationDate.setText(nikolaususFX.fmt.format(city.getCreationDate()));

                Timeline timeline = new Timeline();
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(mapUI.layoutXProperty(), 0)
                        ),
                        new KeyFrame(new Duration(250),
                                new KeyValue(mapUI.layoutXProperty(), -1280)
                        )
                );
                timeline.play();
            }
        }
    }

    public void onDelete() {
//        System.out.println("Удалить город (но только если есть права) " + tableView.getSelectionModel().getSelectedItem().getCity_id());
        try {
            nikolaususFX.getClient().add_message("remove_key " + tableView.getSelectionModel().getSelectedItem().getCity_id());
            Date dateNow = new Date();
            while (nikolaususFX.result_of_delete == null) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }

                if (new Date().getTime() - dateNow.getTime() > 1000) {
                    nikolaususFX.result_of_delete = "error " + "Сервер умер";
                }
            }
            this.callAlert("Ответ", nikolaususFX.result_of_delete.substring(nikolaususFX.result_of_delete.split(" ")[0].length() + 1));
            nikolaususFX.result_of_delete = null;
            this.loadTable();
        } catch (Exception e) {}
    }

    public void toMainMenu() {
        try {
            nikolaususFX.showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSave(ActionEvent event) {
        if (!needCreate) {
            int id = tableView.getSelectionModel().getSelectedItem().getCity_id();
            City city = nikolaususFX.getClient().get_city_by_id(id);

            if (city != null) {
                String res = city.setNewData(inputName.getText(), inputCoordinates.getText(), inputArea.getText(), inputPopulation.getText(), inputMetersAboveSeaLevel.getText(), inputCarCode.getText(), inputClimate.getValue().toString(), inputStandardOfLiving.getValue().toString());
                if (res.split(" ")[0].strip().equals("success")) {
                    res = "update " + id + " " + res.substring(res.split(" ")[0].length() + 1);
                    nikolaususFX.getClient().add_message(res);
                    Date dateNow = new Date();
                    while (nikolaususFX.result_of_change == null) {
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                        }

                        if (new Date().getTime() - dateNow.getTime() > 1000) {
                            nikolaususFX.result_of_change = "error " + "Сервер умер";
                        }
                    }
                    this.callAlert("Результат", nikolaususFX.result_of_change.substring(nikolaususFX.result_of_change.split(" ")[0].length() + 1));
                    nikolaususFX.result_of_change = null;
                    nikolaususFX.showTable();
                } else {
                    callAlert("Ошибка", res.substring(res.split(" ")[0].length() + 1));
                }
            } else {
                callAlert("Ошибка", "Город с таким id был кем-то удалён");
                this.nikolaususFX.showTable();
            }
        } else {
            // create  {"lafs", [10, 10], 124214, 2142, 22, 41, 1, 1}
            nikolaususFX.getClient().add_message("create {\"" + inputName.getText() + "\", [" + inputCoordinates.getText() + "], " + inputArea.getText() + ", " +
                    inputPopulation.getText() + ", " + inputMetersAboveSeaLevel.getText() + ", " + inputCarCode.getText() + ", " +
                    Climate.getIdByName(Climate.getClimateByString(inputClimate.getValue().toString())) + ", " +
                    StandardOfLiving.getIdByName(StandardOfLiving.getStandardByString(inputStandardOfLiving.getValue().toString())) + "}");
            Date dateNow = new Date();
            while (nikolaususFX.result_of_create == null) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {}

                if (new Date().getTime() - dateNow.getTime() > 1000) {
                    nikolaususFX.result_of_create = "error " + "Сервер умер";
                }
            }
            this.callAlert("Результат", nikolaususFX.result_of_create.substring(nikolaususFX.result_of_create.split(" ")[0].length() + 1));
            nikolaususFX.result_of_create = null;
            nikolaususFX.showTable();
        }
    }

    public void onBack(ActionEvent event) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(mapUI.layoutXProperty(), -1280)
                ),
                new KeyFrame(new Duration(250),
                        new KeyValue(mapUI.layoutXProperty(), 0)
                )
        );
        timeline.play();
    }

    public void onDelete2() {
        this.onDelete();
        this.nikolaususFX.showTable();
    }

    public void setLocalization() {
        propName.setText(nikolaususFX.nikiLocal.getText("propName"));
        propId.setText(nikolaususFX.nikiLocal.getText("propId"));
        propArea.setText(nikolaususFX.nikiLocal.getText("propArea"));
        propCoords.setText(nikolaususFX.nikiLocal.getText("propCoords"));
        propPopulation.setText(nikolaususFX.nikiLocal.getText("propPopulation"));
        propMASL.setText(nikolaususFX.nikiLocal.getText("propMASL"));
        propCarCode.setText(nikolaususFX.nikiLocal.getText("propCarCode"));
        propClimate.setText(nikolaususFX.nikiLocal.getText("propClimate"));
        propStandardOfLiving.setText(nikolaususFX.nikiLocal.getText("propStandardOfLiving"));
        propCreator.setText(nikolaususFX.nikiLocal.getText("propCreator"));
        propCreationDate.setText(nikolaususFX.nikiLocal.getText("propCreationDate"));

        labelCity.setText(nikolaususFX.nikiLocal.getText("labelCity"));
        back_button.setText(nikolaususFX.nikiLocal.getText("buttonBack"));
        delete_button.setText(nikolaususFX.nikiLocal.getText("buttonDelete"));
        save_button.setText(nikolaususFX.nikiLocal.getText("buttonSave"));
        change_button.setText(nikolaususFX.nikiLocal.getText("buttonEdit"));
        mainMenu_button.setText(nikolaususFX.nikiLocal.getText("buttonToMainMenu"));
        create_button.setText(nikolaususFX.nikiLocal.getText("buttonCreate"));
    }
}
