module com.example.componentebutton {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    exports controle;
    opens controle to javafx.fxml;

    opens com.example.componentebutton to javafx.fxml;
    exports com.example.componentebutton;
}