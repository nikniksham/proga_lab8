package com.example.proga_lab8.controllers;

import com.example.proga_lab8.NikolaususFX;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

public class BaseController {
    protected NikolaususFX nikolaususFX;

    public Pane base;

    public void setNikolaususFX(NikolaususFX nikolaususFX) {
        this.nikolaususFX = nikolaususFX;
        base.setStyle("-fx-background-color: #9d9c9c");
    }

    public void callAlert(String header, String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, info);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
