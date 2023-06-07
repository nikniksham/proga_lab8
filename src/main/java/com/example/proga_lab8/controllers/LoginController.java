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

public class LoginController extends BaseController{
    public Label loginLabel;
    public PasswordField password;
    public TextField login;
    public Button login_button;
    public Button register_button;

    public void login(ActionEvent event) {
        try {
            String result = nikolaususFX.getClient().tryToLogin(login.getText(), password.getText());
//            System.out.println(login.toString() + " " + password.toString());
            if (result.equals("ok")) {
                nikolaususFX.showMainMenu();
            } else {
                this.callAlert(result, "try again");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(ActionEvent event) { // доделать
        try {
            String result = nikolaususFX.getClient().tryToRegister(login.getText(), password.getText());
//            System.out.println(login.toString() + " " + password.toString());
            if (result.equals("ok")) {
                nikolaususFX.showMainMenu();
            } else {
                this.callAlert(result, "try again");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
