package com.example.proga_lab8.my_program.resources;

import java.util.ListResourceBundle;

public class resource_es extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return new Object[][] {
                {"textEnter", "Entrada"},
                {"buttonRegister", "Registro"},
                {"buttonLogin", "Inicio de sesión"},
                {"fieldPassword", "Contraseña"},

                {"propName", "Nombre:"},
                {"propId", "id:"},
                {"propArea", "Plaza:"},
                {"propCoords", "Coordenadas:"},
                {"propPopulation", "Población:"},
                {"propMASL", "Altura:"},
                {"propCarCode", "Código auto:"},
                {"propClimate", "Clima:"},
                {"propStandardOfLiving", "Nivel de vida:"},
                {"propCreator", "Creador:"},
                {"propCreationDate", "Fecha de creación:"},
                {"buttonBack", "Atrás"},
                {"buttonDelete", "Eliminar"},
                {"buttonSave", "Guardar"},
                {"buttonLogout", "Cambiar cuenta"},
                {"buttonToTable", "En la tabla"},
                {"buttonUpdate", "Actualizar mapa"},
                {"buttonEdit", "Cambiar"},
                {"labelMenu", "Menú principal"},

                {"labelCity", "Ciudades"},
                {"buttonCreate", "Crear"},
                {"buttonToMainMenu", "En el menú principal"},
        };
    }
}
