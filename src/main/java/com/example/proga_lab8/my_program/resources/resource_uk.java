package com.example.proga_lab8.my_program.resources;

import java.util.ListResourceBundle;

public class resource_uk extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return new Object[][] {
                {"enterText", "Входу"},
                {"registerButton", "Реєстрація"},
                {"loginButton", "Логін"},
        };
    }
}
