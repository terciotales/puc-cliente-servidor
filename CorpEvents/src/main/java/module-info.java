module com.corpevents.corpevents {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;


    opens com.corpevents.main to javafx.fxml;
    exports com.corpevents.main;
    opens com.corpevents.main.controller to javafx.fxml;
    exports com.corpevents.main.controller;
    opens com.corpevents.main.util to javafx.fxml;
    exports com.corpevents.main.util;
}