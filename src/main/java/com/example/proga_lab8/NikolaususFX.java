package com.example.proga_lab8;

import com.example.proga_lab8.client.Client;
import com.example.proga_lab8.controllers.LoginController;
import com.example.proga_lab8.controllers.MainMenuController;
import com.example.proga_lab8.controllers.TableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NikolaususFX extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    private String login;
    private String password;
    Socket clientSocket;
    private Scanner scanner;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean run = true;
    private ArrayList<String> messages;
    private ThreadPoolExecutor clients = new ThreadPoolExecutor(0, 20, 120L,TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    private Parent loginView;
    private Parent mainMenuView;
    private Parent tableView;
    private Stage primaryStage;


    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        System.out.println(stage);
//        Parent tableView = FXMLLoader.load(getClass().getResource("login.fxml"));
//        tableView = FXMLLoader.load(getClass().getResource("login.fxml"));


        primaryStage.setTitle("Клиент?");

        InputStream iconStream = getClass().getResourceAsStream("/images/zolotie_ruki.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);

//        Scene scene = new Scene(tableView);
//        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
//        primaryStage.show();
        this.showLogin();
//        TestController testController = fxmlLoader.getController();
//        testController.cities.setVgap(1);
//        testController.cities.setPadding(new Insets(10, 10, 10, 10));
////        testController.cities.setGridLinesVisible(true);
//        for (int i = 0; i < 300; ++i) {
//            Button btn = new Button("Город номер " + (i + 1));
//            btn.setOnAction(e -> {
//                testController.callCity(((Button)e.getSource()).getText());
//
//            });
////            btn.setStyle("-fx-arc-width: 200");
////            btn.setMinWidth(250);
////            btn.setMaxWidth(250);
//            testController.cities.add(btn, 0, i);
//        }

//        testController.cities.add(new Button("1"), 0, 0);
//        testController.cities.add(new Button("2"), 0, 1);
//        testController.cities.add(new Button("3"), 0, 2);

        clients.submit(new localClient());
    }

    public void showLogin() {
        try {
//                System.out.println("?????????????????????????????????????");
//                loginView = FXMLLoader.load(getClass().getResource("login.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            loginView = loader.load();
            LoginController loginController = loader.getController();
            loginController.setNikolaususFX(this);
            Scene scene = new Scene(loginView);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            mainMenuView = loader.load();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.setNikolaususFX(this);
            Scene scene = new Scene(mainMenuView);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showTable() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("table.fxml"));
            tableView = loader.load();
            TableController tableController = loader.getController();
            tableController.setNikolaususFX(this);
            Scene scene = new Scene(tableView);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class localClient implements Runnable {
    public localClient() {

    }

    @Override
    public void run() {
        Client client = new Client();
        client.start(); // javascript:document.getElementsByClassName("video-stream html5-main-video")[0].playbackRate = 3;
    }
}