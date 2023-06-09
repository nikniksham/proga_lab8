package com.example.proga_lab8.my_programm.enums;

public enum Climate {
    TROPICAL_SAVANNA,
    HUMIDSUBTROPICAL,
    STEPPE,
    SUBARCTIC,
    DESERT;
    /*
    insert into climate values
    (1, 'TROPICAL_SAVANNA'),
    (2, 'HUMIDSUBTROPICAL'),
    (3, 'STEPPE'),
    (4, 'SUBARCTIC'),
    (5, 'DESERT');
    */
    public static Climate getById(int id) {
        switch (id) {
            case 1: return TROPICAL_SAVANNA;
            case 2: return HUMIDSUBTROPICAL;
            case 3: return STEPPE;
            case 4: return SUBARCTIC;
            case 5: return DESERT;
            default: throw new RuntimeException("Тип климата может быть от 1 до 5");
        }
    }

    public static int getIdByName(Climate climate) {
        switch (climate) {
            case TROPICAL_SAVANNA: return 1;
            case HUMIDSUBTROPICAL: return 2;
            case STEPPE: return 3;
            case SUBARCTIC: return 4;
            case DESERT: return 5;
            default: return 0;
        }
    }

    public static Climate getClimateByString(String name) {
        switch (name) {
            case "TROPICAL_SAVANNA": return TROPICAL_SAVANNA;
            case "HUMIDSUBTROPICAL": return HUMIDSUBTROPICAL;
            case "STEPPE": return STEPPE;
            case "SUBARCTIC": return SUBARCTIC;
            case "DESERT": return DESERT;
            default: throw new RuntimeException("Такого климата нет");
        }
    }
}