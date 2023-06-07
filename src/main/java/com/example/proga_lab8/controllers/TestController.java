package com.example.proga_lab8.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TestController {
    @FXML
    public GridPane cities;
    @FXML
    public Pane info;
    @FXML
    private Button create_button;
    @FXML
    private Button edit_button;
    @FXML
    private Button delete_button;
    @FXML
    private Label welcomeText;

    public void onDeleteClick(ActionEvent actionEvent) {

    }

    public void onCreateClick(ActionEvent actionEvent) {
    }

    public void onEditClick(ActionEvent actionEvent) {
    }

    public void callCity(String mes) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mes);
        alert.setHeaderText(mes);
        alert.showAndWait();
    }
}