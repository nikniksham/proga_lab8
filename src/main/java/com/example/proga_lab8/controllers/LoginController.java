package com.example.proga_lab8.controllers;

import com.example.proga_lab8.NikolaususFX;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Objects;

public class LoginController extends BaseController{
    public Label loginLabel;
    public PasswordField password;
    public TextField login;
    public Button login_button;
    public Button register_button;
    public ComboBox languageSelector;

    public void setMulti() {

        Image defaultImage = new Image(getClass().getResourceAsStream("/images/ru.png"));
        languageSelector.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ImageListCell(defaultImage);
            }
        });
        languageSelector.setButtonCell(new ImageListCell(defaultImage));
        languageSelector.setItems(FXCollections.observableArrayList("ru", "ee", "uk", "es"));
        languageSelector.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Выбран элемент: " + newValue);
        });
    }

    public void login(ActionEvent event) {
        try {
            String result = nikolaususFX.getClient().tryToLogin(login.getText(), password.getText());
//            System.out.println(login.toString() + " " + password.toString());
            if (result.equals("ok")) {
                nikolaususFX.loadAndThenShow();
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
                nikolaususFX.loadAndThenShow();
            } else {
                this.callAlert(result, "try again");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class ImageListCell extends ListCell<String> {
    private ImageView imageView = new ImageView();
    private Image defaultImage;

    public ImageListCell(Image image) {
        defaultImage = image;
        imageView.setImage(defaultImage);
    }
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            imageView.setImage(defaultImage);
            setGraphic(imageView);
        } else {
            imageView.setImage(getImageForItem(item));
            setGraphic(imageView);
        }
    }

    private Image getImageForItem(String item) {
        if (item.equals("ru")) {
            return new Image(getClass().getResourceAsStream("/images/ru.png"));
        } else if (item.equals("ee")) {
            return new Image(getClass().getResourceAsStream("/images/ee.png"));
        } else if (item.equals("uk")) {
            return new Image(getClass().getResourceAsStream("/images/uk.png"));
        } else if (item.equals("es")) {
            return new Image(getClass().getResourceAsStream("/images/es.png"));
        }

        return null;
    }
}
