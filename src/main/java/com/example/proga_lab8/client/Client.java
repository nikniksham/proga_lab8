package com.example.proga_lab8.client;

import com.example.proga_lab8.NikolaususFX;
import com.example.proga_lab8.controllers.FakeHandler;
import com.example.proga_lab8.controllers.MainMenuController;
import com.example.proga_lab8.my_programm.CustomFileReader;
import com.example.proga_lab8.my_programm.enums.Climate;
import com.example.proga_lab8.my_programm.enums.StandardOfLiving;
import com.example.proga_lab8.my_programm.obj.City;
import com.example.proga_lab8.my_programm.obj.Human;
import com.example.proga_lab8.server.api.BaseApi;
import com.example.proga_lab8.server.api.UserApi;
import javafx.event.ActionEvent;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class Client {
    private String login = null;
    private String password = null;
    Socket clientSocket;
    private Scanner scanner;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean run = true;
    private ArrayList<String> messages;
    private Hashtable<Integer, City> table = new Hashtable<>();

    private NikolaususFX nikolaususFX;
    private Date lastAsk = new Date();


    public Hashtable<Integer, City> getTable() {
        return table;
    }

    public Client(NikolaususFX nikolaususFX) {
        this.nikolaususFX = nikolaususFX;
        messages = new ArrayList<>();
        scanner = new Scanner(System.in);

//        System.out.println("Укажите логин");
//        this.login = scanner.nextLine();
//        System.out.println("Укажите пароль");
//        this.password = codeToSHA256(scanner.nextLine());
//        System.out.println("Это новый аккаунт? (нажмите enter, если нет)");
//        if (!scanner.nextLine().equals("")) {
//            messages.add("register");
//        } else {
//            System.out.println("Добро пожаловать в систему " + login);
//        }

//        this.login = "nikolausus";
//        this.password = codeToSHA256("123456");

//        for (int i = 0; i < 201; i += 40) {
//            for (int j = 0; j < 201; j += 40) {
//                messages.add("create  {\"lafs\", ["+i+", "+j+"], 124214, 2142, 22, 41, 1, 1}");
//            }
//        }
        messages.add("show");
    }

    public String getLogin() {
        return login;
    }

    public void logout() {
        this.login = null;
        this.password = null;
        this.messages.clear();
    }

    public String tryToLogin(String login, String password) {
        int result = UserApi.login(login, codeToSHA256(password));
        if (result >= 0) {
            this.login = login;
            this.password =codeToSHA256(password);
            return "ok";
        } else {
            return "Wrong login or password";
        }
    }

    public String tryToRegister(String login, String password) {
        String result = UserApi.register(login, codeToSHA256(password));
        if (result.equals("ok")) {
            this.login = login;
            this.password = codeToSHA256(password);
        }
        return result;
    }

    private void connectToTheServer() {
        try {
            writer.close();
            reader.close();
            clientSocket.close();
        } catch (Exception e) {}

        try {
            clientSocket = new Socket("localhost", 4004);
//            clientSocket = new Socket("helios.cs.ifmo.ru", 2222);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (Exception e) {
            System.out.println("Что-то пошло не так");
//            e.printStackTrace();
        }
    }
    public void start() {
        while (run) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {}

            if (login != null && password != null) {

                if (new Date().getTime() - lastAsk.getTime() > 3000) {
                    messages.add("show");
                    lastAsk = new Date();
                }

                if (messages.size() > 0) {
                    this.connectToTheServer();
                    try {
                        String message = messages.get(0);
                        if (message.strip().equals("exit")) {
                            System.out.println("Завершаем работу без вопросов");
                            System.exit(0);
                        } else if (message.strip().contains("execute_script ")) {
                            ArrayList<String> blacklist = new ArrayList<>();
                            blacklist.add(message.split("\s")[1]);
                            get_command(message.strip(), blacklist);
//                    for (String s : this.messages) {System.out.println(s + "?");}
                        } else {
                            writer.write(message + " " + login + " " + password + "\n");
                            writer.flush();

                            CompletableFuture<String> waitMessageFromServer = CompletableFuture.supplyAsync(() -> wait_new_message(reader));

                            Date c_date = new Date();
                            while (!waitMessageFromServer.isDone()) {
                                if (new Date().getTime() - c_date.getTime() > 3000) {
                                    System.out.println("Превышено время ожидания ответа от сервера");
                                }
                            }

                            String answer = waitMessageFromServer.get();
                            if (answer != null) {
                                System.out.print(answer);

                                try {
                                    Object o = new JSONParser().parse(answer);
                                    JSONObject j = (JSONObject) o;
                                    JSONArray ja = (JSONArray) j.get("city");
                                    table.clear();
                                    for (Object obj : ja) {
                                        JSONObject jo = (JSONObject) obj;
//                                    System.out.println(jo);
                                        Human gov = null;
                                        if (jo.get("Governor") != null) {
                                            Date zti = new Date();
                                            gov = new Human(1, new Timestamp(zti.getTime()), "чёрт");
                                        }
                                        table.put(Math.toIntExact((Long) jo.get("id")), new City(Math.toIntExact((Long) jo.get("id")), (String) jo.get("name"), (String) jo.get("coordinates"), (long) jo.get("area"), (Long) jo.get("population"), Math.toIntExact((Long) jo.get("metersAboveSeaLevel")), Math.toIntExact((Long) jo.get("carCode")), Climate.getById(Math.toIntExact((Long) jo.get("Climate"))), StandardOfLiving.getById(Math.toIntExact((Long) jo.get("StandardOfLiving"))), gov, Math.toIntExact((Long) jo.get("creator_id"))));
                                    }
                                    if (nikolaususFX.cur_scene.equals("menu")) {
                                        nikolaususFX.mainMenuController.update_button.fireEvent(new ActionEvent());
//                                        nikolaususFX.showTable();

                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                        messages.remove(0);
                    } catch (Exception e) {
                        System.out.println("Сломалися");
//                    e.printStackTrace();
//                    System.exit(1);
                    }
                }
            }
        }
    }
    private static String wait_new_message(BufferedReader reader) {
        try {
            StringBuilder answer = new StringBuilder();
            for (Iterator<String> it = reader.lines().iterator(); it.hasNext(); ) {
                String s = it.next();
                if (s.equals("end")) {break;}
                answer.append(s).append("\n");
            }
            return answer.toString();
        } catch (Exception e) {
            System.out.println("Потеря соединения с сервером");
            return "error";
        }
//        return null;
    }

    private String codeToSHA256(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            System.out.println("это как?");
        }
        return null;
    }

    private void get_command(String command, ArrayList<String> blacklist) {
        String filename = command.split("\s")[1];
        List<String> arr = CustomFileReader.readFile(filename);
        if (arr == null) {return;}
        for (String s : arr) {
            s = s.strip();
            if (s.contains("execute_script ")) {
                if (!blacklist.contains(s.split("\s")[1])) {
                    blacklist.add(s.split("\s")[1]);
                    get_command(s, blacklist);
                }
            } else {
//                com += s + "\n";
                messages.add(s);
            }
        }
    }
}
