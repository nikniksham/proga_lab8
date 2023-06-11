package com.example.proga_lab8.my_program.resources;

import java.util.ListResourceBundle;

public class resource_ee extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return new Object[][] {
                {"textEnter", "Sisend"},
                {"buttonRegister", "Registreerimine"},
                {"buttonLogin", "Login"},
                {"fieldPassword", "Parool"},

                {"propName", "Nimi:"},
                {"propId", "id:"},
                {"propArea", "Ala:"},
                {"propCoords", "Koordinaat:"},
                {"propPopulation", "Elanikkond:"},
                {"propMASL", "Kõrgus:"},
                {"propCarCode", "Auto kood:"},
                {"propClimate", "Kliima:"},
                {"propStandardOfLiving", "Elatustase:"},
                {"propCreator", "Looja:"},
                {"propCreationDate", "Loomise kuupäev:"},
                {"buttonBack", "Tagasi"},
                {"buttonDelete", "Eemaldama"},
                {"buttonSave", "Salvestama"},
                {"buttonLogout", "Muuda kontot"},
                {"buttonToTable", "Tabelisse"},
                {"buttonUpdate", "Uuendage kaarti"},
                {"buttonEdit", "Muutma"},
                {"labelMenu", "Põhimenüü"},

                {"labelCity", "Linn"},
                {"buttonCreate", "Luua"},
                {"buttonToMainMenu", "Peamine menüü"},
        };
    }
}
