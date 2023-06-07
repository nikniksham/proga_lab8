module com.example.proga_lab8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires json.simple;
    requires java.sql;

    opens com.example.proga_lab8 to javafx.fxml;
    exports com.example.proga_lab8;
    exports com.example.proga_lab8.controllers;
    opens com.example.proga_lab8.controllers to javafx.fxml;
//    opens com.example.proga_lab8.controllers.localObject to javafx.fxml;
    exports com.example.proga_lab8.controllers.localObject;

}