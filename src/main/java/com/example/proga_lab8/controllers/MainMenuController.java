package com.example.proga_lab8.controllers;

import com.example.proga_lab8.my_program.obj.City;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class MainMenuController extends BaseController {

    public Button toTable_button;
    public Button logout_button;
    public Button update_button;
    public Label menuLabel;
    public Label nickname;
    public Pane risulki;
    public Integer selectedCity;
    public Pane information;
    public Label city_name;
    public Label city_id;
    public Label city_area;
    public Label city_coordinates;
    public Label city_population;
    public Label city_metersAboveSeaLevel;
    public Label city_carCode;
    public Label city_climate;
    public Label city_standardOfLiving;
    public Label city_creatorId;
    public Label city_creationDate;
    public Button edit_button;
    public Button delete_button;
    public Label id;
    public Pane mapUI;
    public Pane editPane;
    public Button back_button;
    public Button delete_button2;
    public TextField inputArea;
    public TextField inputCoordinates;
    public TextField inputPopulation;
    public TextField inputMetersAboveSeaLevel;
    public TextField inputCarCode;
    public ChoiceBox inputClimate;
    public ChoiceBox inputStandardOfLiving;
    public Label outputId;
    public Button save_button;
    public TextField inputName;
    public Label outputCreatorId;
    public Label outputCreationDate;
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
    public Label propName;
    public Label propName2;
    public Label propId2;
    public Label propArea2;
    public Label propCoords2;
    public Label propPopulation2;
    public Label propMASL2;
    public Label propCarCode2;
    public Label propClimate2;
    public Label propStandardOfLiving2;
    public Label propCreator2;
    public Label propCreationDate2;

    public void logout(ActionEvent event) {
        try {
            nikolaususFX.getClient().logout();
            nikolaususFX.showLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void toTable(ActionEvent event) {
        try {
            nikolaususFX.showTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawLines() {
        for (int i = (int)risulki.getWidth() / 20; i < risulki.getWidth(); i += (int)(risulki.getWidth() / 20)) {
            Line line = new Line();
            line.setStartX(i);
            line.setStartY(0);
            line.setEndX(i);
            line.setEndY(risulki.getHeight());
            risulki.getChildren().add(line);
            line = new Line();
            line.setStartX(0);
            line.setStartY(i);
            line.setEndX(risulki.getWidth());
            line.setEndY(i);
            risulki.getChildren().add(line);
        }
    }

    public void drawCities() {
        selectedCity = null;
        Timeline timeline = new Timeline();
        for (Map.Entry<Integer, City> entry : nikolaususFX.getClient().getTable().entrySet()) {
            City city = entry.getValue();
            Circle circle = new Circle();
            circle.setCenterX(Integer.parseInt(city.getCoordinates().split(",")[0].strip()) * 2.57 + 60);
            circle.setCenterY(Integer.parseInt(city.getCoordinates().split(",")[1].strip()) * 2.57 + 60);
            circle.setId("city_" + city.getId());
            circle.setRadius(city.getRadius());
//            circle.setEffect(new BoxBlur(2, 2, 1));

            circle.setOnMousePressed(e ->  {
                this.showCityInformation(city);
                Timeline locTimeLine = new Timeline();
                locTimeLine.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(circle.radiusProperty(), circle.getRadius())
                        ),
                        new KeyFrame(new Duration(50),
                                new KeyValue(circle.radiusProperty(), circle.getRadius() * 1.3)
                        ),
                        new KeyFrame(new Duration(100),
                                new KeyValue(circle.radiusProperty(), circle.getRadius())
                        )
                );
                locTimeLine.play();
            });

            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(circle.radiusProperty(), 0)
                    ),
                    new KeyFrame(new Duration(75),
                            new KeyValue(circle.radiusProperty(), circle.getRadius() / 2)
                    ),
                    new KeyFrame(new Duration(150),
                            new KeyValue(circle.radiusProperty(), circle.getRadius())
                    )
            );

            circle.setFill(city.getColor());
            risulki.getChildren().add(circle);
        }
        timeline.play();
    }


    public void updateMap(ActionEvent event) {
        nikolaususFX.showMainMenu();
    }

    public void onEdit() {
//        System.out.println(selectedCity);
        City city = nikolaususFX.getClient().get_city_by_id(selectedCity);
        if (city != null) {

            inputName.setText(city.getName());
            outputId.setText(city.getId().toString());
            inputArea.setText(nikolaususFX.nbr.format(city.getArea()));
            inputCoordinates.setText(city.getCoordinates());
            inputPopulation.setText(nikolaususFX.nbr.format(city.getPopulation()));
            inputMetersAboveSeaLevel.setText(nikolaususFX.nbr.format(city.getMetersAboveSeaLevel()));
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
        } else {
            callAlert("Ошибка", "Город с таким id был кем-то удалён");
            this.nikolaususFX.showMainMenu();
        }
    }

    public void onDelete() {
        nikolaususFX.getClient().add_message("remove_key " + selectedCity);
        Date dateNow = new Date();
        while (nikolaususFX.result_of_delete == null) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {}

            if (new Date().getTime() - dateNow.getTime() > 1000) {
                nikolaususFX.result_of_delete = "error " + "Сервер умер";
            }
        }

        if (nikolaususFX.result_of_delete.length() > 0 && nikolaususFX.result_of_delete.split(" ")[0].strip().equals("success")) {
            for (Node c : risulki.getChildren().sorted()) {
                if (c == null) {
                    continue;
                }
                if (c.getId().equals("city_" + selectedCity)) {
                    Circle circle = (Circle) c;
                    Timeline timeline = new Timeline();
                    timeline.getKeyFrames().addAll(
                            new KeyFrame(Duration.ZERO,
                                    new KeyValue(circle.radiusProperty(), circle.getRadius())
                            ),
                            new KeyFrame(new Duration(75),
                                    new KeyValue(circle.radiusProperty(), circle.getRadius() / 2)
                            ),
                            new KeyFrame(new Duration(150),
                                    new KeyValue(circle.radiusProperty(), 0)
                            )
                    );
                    timeline.play();
                    break;
                }
            }
        }

        this.callAlert("Ответ", nikolaususFX.result_of_delete.substring(nikolaususFX.result_of_delete.split(" ")[0].length() + 1));
        nikolaususFX.result_of_delete = null;
        nikolaususFX.showMainMenu();
    }

    public void onBack() {
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

    public void onSave() {
//        System.out.println("Провести валидацию данных и сохранить изменения города " + selectedCity);

        City city = nikolaususFX.getClient().get_city_by_id(selectedCity);

        if (city != null) {
            String res = city.setNewData(inputName.getText(), inputCoordinates.getText(), inputArea.getText(), inputPopulation.getText(), inputMetersAboveSeaLevel.getText(), inputCarCode.getText(), inputClimate.getValue().toString(), inputStandardOfLiving.getValue().toString());
            if (res.split(" ")[0].strip().equals("success")) {
                res = "update " + selectedCity + " " + res.substring(res.split(" ")[0].length() + 1);
                nikolaususFX.getClient().add_message(res);
                Date dateNow = new Date();
                while (nikolaususFX.result_of_change == null) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {}

                    if (new Date().getTime() - dateNow.getTime() > 1000) {
                        nikolaususFX.result_of_change = "error " + "Сервер умер";
                    }
                }
                this.callAlert("Результат", nikolaususFX.result_of_change.substring(nikolaususFX.result_of_change.split(" ")[0].length() + 1));
                nikolaususFX.result_of_change = null;
                nikolaususFX.showMainMenu();
            } else {
                callAlert("Ошибка", res.substring(res.split(" ")[0].length() + 1));
            }
        } else {
            callAlert("Ошибка", "Город с таким id был кем-то удалён");
            this.nikolaususFX.showMainMenu();
        }
    }

    public void showCityInformation(City city) {
        if (!information.isVisible()) {
            information.setVisible(true);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(information.layoutXProperty(), 1280)
                    ),
                    new KeyFrame(new Duration(200),
                            new KeyValue(information.layoutXProperty(), 773)
                    )
            );
            timeline.play();
        }

        selectedCity = city.getId();

        city_name.setText(city.getName());
        city_id.setText(city.getId().toString());
        city_area.setText(nikolaususFX.nbr.format(city.getArea()));
        city_coordinates.setText(city.getCoordinates());
        city_population.setText(nikolaususFX.nbr.format(city.getPopulation()));
        city_metersAboveSeaLevel.setText(nikolaususFX.nbr.format(city.getMetersAboveSeaLevel()));
        city_carCode.setText(Integer.toString(city.getCarCode()));
        city_climate.setText(city.getClimate().toString());
        city_standardOfLiving.setText(city.getStandardOfLiving().toString());
        city_creatorId.setText(city.getCreator_id().toString());
        city_creationDate.setText(nikolaususFX.fmt.format(city.getCreationDate()));
        if (Objects.equals(this.nikolaususFX.getClient().userId, city.getCreator_id()) || this.nikolaususFX.getClient().userStatus > 0) {
            delete_button.setVisible(true);
            edit_button.setVisible(true);
        } else {
            delete_button.setVisible(false);
            edit_button.setVisible(false);
        }
//        city
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

        propName2.setText(nikolaususFX.nikiLocal.getText("propName"));
        propId2.setText(nikolaususFX.nikiLocal.getText("propId"));
        propArea2.setText(nikolaususFX.nikiLocal.getText("propArea"));
        propCoords2.setText(nikolaususFX.nikiLocal.getText("propCoords"));
        propPopulation2.setText(nikolaususFX.nikiLocal.getText("propPopulation"));
        propMASL2.setText(nikolaususFX.nikiLocal.getText("propMASL"));
        propCarCode2.setText(nikolaususFX.nikiLocal.getText("propCarCode"));
        propClimate2.setText(nikolaususFX.nikiLocal.getText("propClimate"));
        propStandardOfLiving2.setText(nikolaususFX.nikiLocal.getText("propStandardOfLiving"));
        propCreator2.setText(nikolaususFX.nikiLocal.getText("propCreator"));
        propCreationDate2.setText(nikolaususFX.nikiLocal.getText("propCreationDate"));

        back_button.setText(nikolaususFX.nikiLocal.getText("buttonBack"));
        delete_button.setText(nikolaususFX.nikiLocal.getText("buttonDelete"));
        delete_button2.setText(nikolaususFX.nikiLocal.getText("buttonDelete"));
        save_button.setText(nikolaususFX.nikiLocal.getText("buttonSave"));
        logout_button.setText(nikolaususFX.nikiLocal.getText("buttonLogout"));
        toTable_button.setText(nikolaususFX.nikiLocal.getText("buttonToTable"));
        update_button.setText(nikolaususFX.nikiLocal.getText("buttonUpdate"));
        edit_button.setText(nikolaususFX.nikiLocal.getText("buttonEdit"));

        menuLabel.setText(nikolaususFX.nikiLocal.getText("labelMenu"));
    }
}
