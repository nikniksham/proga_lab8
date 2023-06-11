package com.example.proga_lab8.my_program.resources;

import java.util.ListResourceBundle;

public class resource_ee extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return new Object[][] {
                {"enterText", "Sisend"},
                {"registerButton", "Registreerimine"},
                {"loginButton", "Login"},
        };
    }
}
