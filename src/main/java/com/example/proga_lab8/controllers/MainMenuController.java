package com.example.proga_lab8.controllers;

import com.example.proga_lab8.NikolaususFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainMenuController extends BaseController {

    public Button toTable_button;
    public Button logout_button;
    public Label menuLabel;
    public Label nickname;

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
}
