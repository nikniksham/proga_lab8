package com.example.proga_lab8.my_program.resources;

import java.util.ListResourceBundle;

public class resource_es extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return new Object[][] {
                {"enterText", "Entrada"},
                {"registerButton", "Registro"},
                {"loginButton", "Inicio de sesión"},
        };
    }
}
