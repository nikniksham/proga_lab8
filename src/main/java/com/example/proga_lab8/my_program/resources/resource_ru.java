package com.example.proga_lab8.my_program.resources;

import java.util.ListResourceBundle;

public class resource_ru extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return new Object[][] {
                {"enterText", "Вход"},
                {"registerButton", "Регистрация"},
                {"loginButton", "Логин"},
        };
    }
}