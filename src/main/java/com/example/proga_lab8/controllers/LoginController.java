package com.example.proga_lab8.controllers;

import com.example.proga_lab8.NikolaususFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginController {
    public Label enterLabel;
    public PasswordField password;
    public TextField login;
    public Button enter_button;
    private NikolaususFX nikolaususFX;

    public void setNikolaususFX(NikolaususFX nikolaususFX) {
        this.nikolaususFX = nikolaususFX;
    }

    public void login(ActionEvent event) {
        try {
//            Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
//            Scene tableViewScene = new Scene(tableViewParent);
//
//            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//
//            window.setScene(tableViewScene);
//            window.show();
//            nikolaususFX.
            nikolaususFX.showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
