package com.example.proga_lab8.my_program.resources;

import java.util.ListResourceBundle;

public class resource_uk extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return new Object[][] {
                {"textEnter", "Входу"},
                {"buttonRegister", "Реєстрація"},
                {"buttonLogin", "Логін"},
                {"fieldPassword", "Пароль"},

                {"propName", "Назва:"},
                {"propId", "id:"},
                {"propArea", "Площа:"},
                {"propCoords", "Координата:"},
                {"propPopulation", "Населення:"},
                {"propMASL", "Висота:"},
                {"propCarCode", "Код авто:"},
                {"propClimate", "Клімат:"},
                {"propStandardOfLiving", "Рівень життя:"},
                {"propCreator", "Боже:"},
                {"propCreationDate", "Дата створення:"},
                {"buttonBack", "Назад"},
                {"buttonDelete", "Видалити"},
                {"buttonSave", "Зберігши"},
                {"buttonLogout", "Змінити аккаунт"},
                {"buttonToTable", "В таблицю"},
                {"buttonUpdate", "Оновити карту"},
                {"buttonEdit", "Змінивши"},
                {"labelMenu", "Головне меню"},

                {"labelCity", "Місто"},
                {"buttonCreate", "Утворити"},
                {"buttonToMainMenu", "В Головне меню"},
        };
    }
}
