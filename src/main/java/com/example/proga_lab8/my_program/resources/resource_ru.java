package com.example.proga_lab8.my_program.resources;

import java.util.ListResourceBundle;

public class resource_ru extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return new Object[][] {
                {"textEnter", "Вход"},
                {"buttonRegister", "Регистрация"},
                {"buttonLogin", "Логин"},
                {"fieldPassword", "Пароль"},

                {"propName", "Название:"},
                {"propId", "id:"},
                {"propArea", "Площадь:"},
                {"propCoords", "Координаты:"},
                {"propPopulation", "Население:"},
                {"propMASL", "Высота:"},
                {"propCarCode", "Код авто:"},
                {"propClimate", "Климат:"},
                {"propStandardOfLiving", "Уровень жизни:"},
                {"propCreator", "Создатель:"},
                {"propCreationDate", "Дата создания:"},

                {"buttonBack", "Назад"},
                {"buttonDelete", "Удалить"},
                {"buttonSave", "Сохранить"},
                {"buttonLogout", "Сменить аккаунт"},
                {"buttonToTable", "В таблицу"},
                {"buttonUpdate", "Обновить карту"},
                {"buttonEdit", "Изменить"},
                {"labelMenu", "Главное меню"},

                {"labelCity", "Города"},
                {"buttonCreate", "Создать"},
                {"buttonToMainMenu", "В главное меню"},
        };
    }
}
