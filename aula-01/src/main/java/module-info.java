module com.example.aula01javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aula01javafx to javafx.fxml;
    exports com.example.aula01javafx;
}