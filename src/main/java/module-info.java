module com.quanlythuphi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires mysql.connector.java;

    opens com.quanlythuphi to javafx.fxml;
    exports com.quanlythuphi;

    opens com.quanlythuphi.views to javafx.fxml;
    exports com.quanlythuphi.views;

    opens com.quanlythuphi.models to javafx.fxml;
    exports com.quanlythuphi.models;
}