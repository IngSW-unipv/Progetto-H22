module com.nosuchmethod.aerospin {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.persistence;

    opens model to javafx.fxml;
    exports model;
    exports controller;
    opens controller to javafx.fxml;
    exports view;
    opens view to javafx.fxml;
}