module main.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens model to javafx.fxml;
    opens controller to javafx.fxml;
    exports model;
    exports controller;

    opens main.app to javafx.fxml;
    exports main.app;
}