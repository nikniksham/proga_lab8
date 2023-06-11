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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NikolaususFX extends Application {
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
    public MainMenuController mainMenuController;
    private Stage primaryStage;
    private Client client;
    public String cur_scene = "login";
    public Scene scene;
    public boolean need_to_update = true;
    public String result_of_delete;
    public String result_of_change;
    public String result_of_create;
    public DateFormat fmt = DateFormat.getDateTimeInstance (DateFormat.SHORT, DateFormat.MEDIUM, Locale.FRANCE);

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;

        primaryStage.setTitle("Клиент?");

        InputStream iconStream = getClass().getResourceAsStream("/images/zolotie_ruki.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        primaryStage.setResizable(false);

        this.showLogin();

        clients.submit(new localClient(this));
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void showLogin() {
        try {
//                System.out.println("?????????????????????????????????????");
//                loginView = FXMLLoader.load(getClass().getResource("login.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            loginView = loader.load();
            LoginController loginController = loader.getController();
            loginController.setNikolaususFX(this);
            scene = new Scene(loginView);
            primaryStage.setScene(scene);
            cur_scene = "login";
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            mainMenuView = loader.load();
            mainMenuController = loader.getController();
            mainMenuController.setNikolaususFX(this);
            mainMenuController.nickname.setText(client.getLogin());
            mainMenuController.id.setText(client.getId());
            scene = new Scene(mainMenuView);
            primaryStage.setScene(scene);
            mainMenuController.drawLines();
            need_to_update = false;
            cur_scene = "menu";
            mainMenuController.drawCities();
//            mainMenuController.editPane.setVisible(false);
            mainMenuController.inputClimate.getItems().addAll("TROPICAL_SAVANNA", "HUMIDSUBTROPICAL", "STEPPE", "SUBARCTIC", "DESERT");
            mainMenuController.inputStandardOfLiving.getItems().addAll("VERY_HIGH", "HIGH", "VERY_LOW", "ULTRA_LOW", "NIGHTMARE");
            mainMenuController.editPane.setLayoutX(1315);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAndThenShow() {
        try {
            Date dateNow = new Date();
            while (new Date().getTime() - dateNow.getTime() < 1000) {
                if (client.isActual) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.showMainMenu();
    }

    public void showTable() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("table.fxml"));
            tableView = loader.load();
            TableController tableController = loader.getController();
            tableController.setNikolaususFX(this);
            scene = new Scene(tableView);
            primaryStage.setScene(scene);
            tableController.inputClimate.getItems().addAll("TROPICAL_SAVANNA", "HUMIDSUBTROPICAL", "STEPPE", "SUBARCTIC", "DESERT");
            tableController.inputStandardOfLiving.getItems().addAll("VERY_HIGH", "HIGH", "VERY_LOW", "ULTRA_LOW", "NIGHTMARE");
            tableController.loadTable();
            tableController.editPane.setLayoutX(1315);
            cur_scene = "table";
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Client getClient() {
        return client;
    }
}

class localClient implements Runnable {
    private NikolaususFX nikolaususFX;
    public localClient(NikolaususFX nikolaususFX) {
        this.nikolaususFX = nikolaususFX;
    }

    @Override
    public void run() {
        Client client = new Client(nikolaususFX);
        nikolaususFX.setClient(client);
        client.start(); // javascript:document.getElementsByClassName("video-stream html5-main-video")[0].playbackRate = 3;
    }
}