package com.example.proga_lab8.my_program;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationHelper {
    public ResourceBundle bundleRU;
    public ResourceBundle bundleUK;
    public ResourceBundle bundleES;
    public ResourceBundle bundleEE;

    public String locale = "ru";

    public LocalizationHelper() {
        bundleRU = ResourceBundle.getBundle("com.example.proga_lab8.my_program.resources.resource", new Locale("ru"));
        bundleUK = ResourceBundle.getBundle("com.example.proga_lab8.my_program.resources.resource", new Locale("uk"));
        bundleES = ResourceBundle.getBundle("com.example.proga_lab8.my_program.resources.resource", new Locale("es"));
        bundleEE = ResourceBundle.getBundle("com.example.proga_lab8.my_program.resources.resource", new Locale("ee"));

    }

    public String getText(String textFor) {
        switch (locale) {
            case "uk": return bundleUK.getString(textFor);
            case "es": return bundleES.getString(textFor);
            case "ee": return bundleEE.getString(textFor);
            default: return bundleRU.getString(textFor);
        }
    }
}
