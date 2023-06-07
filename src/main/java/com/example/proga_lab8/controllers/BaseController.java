package com.example.proga_lab8.controllers;

import com.example.proga_lab8.NikolaususFX;
import javafx.scene.control.Alert;

public class BaseController {
    protected NikolaususFX nikolaususFX;

    public void setNikolaususFX(NikolaususFX nikolaususFX) {
        this.nikolaususFX = nikolaususFX;
    }

    public void callAlert(String header, String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, info);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
