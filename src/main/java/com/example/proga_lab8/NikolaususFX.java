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
    public MainMenuController mainMenuController;
    private Stage primaryStage;
    private Client client;
    public String cur_scene = "login";
    public boolean need_to_update = true;


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
            Scene scene = new Scene(loginView);
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
            Scene scene = new Scene(mainMenuView);
            primaryStage.setScene(scene);
            mainMenuController.drawCities();
            need_to_update = false;
            cur_scene = "menu";
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
            tableController.loadTable();
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