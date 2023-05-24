module com.corpevents.corpevents {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.corpevents.main to javafx.fxml;
    exports com.corpevents.main;
}