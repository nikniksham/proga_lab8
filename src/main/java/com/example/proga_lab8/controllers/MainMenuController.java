package com.example.proga_lab8.controllers;

import com.example.proga_lab8.NikolaususFX;
import com.example.proga_lab8.controllers.localObject.CityAndGovernor;
import com.example.proga_lab8.my_programm.enums.Climate;
import com.example.proga_lab8.my_programm.enums.StandardOfLiving;
import com.example.proga_lab8.my_programm.obj.City;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static java.lang.Math.random;

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
            circle.setRadius(city.getRadius());
//            circle.setEffect(new BoxBlur(2, 2, 1));
            circle.setOnMousePressed(e ->  {
                this.showCityInformation(city);
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

    }

    public void onDelete() {
        nikolaususFX.getClient().add_message("remove_key " + selectedCity);
        Date dateNow = new Date();
        while (nikolaususFX.result_of_delete == null) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {}

            if (new Date().getTime() - dateNow.getTime() > 1000) {
                nikolaususFX.result_of_delete = "Сервер умер";
            }
        }
        this.callAlert("Ответ", nikolaususFX.result_of_delete);
        nikolaususFX.result_of_delete = null;
        nikolaususFX.showMainMenu();
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
                            new KeyValue(information.layoutXProperty(), 797)
                    )
            );
            timeline.play();
        }

        selectedCity = city.getId();

        city_name.setText(city.getName());
        city_id.setText(city.getId().toString());
        city_area.setText(Long.toString(city.getArea()));
        city_coordinates.setText(city.getCoordinates());
        city_population.setText(city.getPopulation().toString());
        city_metersAboveSeaLevel.setText(city.getMetersAboveSeaLevel().toString());
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
}
