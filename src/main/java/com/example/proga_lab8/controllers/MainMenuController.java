package com.example.proga_lab8.controllers;

import com.example.proga_lab8.NikolaususFX;
import com.example.proga_lab8.controllers.localObject.CityAndGovernor;
import com.example.proga_lab8.my_programm.enums.Climate;
import com.example.proga_lab8.my_programm.enums.StandardOfLiving;
import com.example.proga_lab8.my_programm.obj.City;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.time.ZoneId;
import java.util.Map;

public class MainMenuController extends BaseController {

    public Button toTable_button;
    public Button logout_button;
    public Button update_button;
    public Label menuLabel;
    public Label nickname;
    public Pane risulki;

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

    public void drawCities() {
        for (Map.Entry<Integer, City> entry : nikolaususFX.getClient().getTable().entrySet()) {
            City city = entry.getValue();
            Circle circle = new Circle();
            circle.setCenterX(Integer.parseInt(city.getCoordinates().split(",")[0].strip()) * 2 + 100);
            circle.setCenterY(Integer.parseInt(city.getCoordinates().split(",")[1].strip())* 2 + 100);
            circle.setRadius(10.0f);
            circle.setOnMousePressed(e ->  {
                System.out.println(city.getName());
            });
//            System.out.println(circle.getCenterX() + " " + circle.getCenterY());
            circle.setFill(Color.BLUE);
            risulki.getChildren().add(circle);
        }
    }


    public void updateMap(ActionEvent event) {
        nikolaususFX.showMainMenu();
    }
}
