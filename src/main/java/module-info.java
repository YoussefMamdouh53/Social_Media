module com.debi.social_media {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;

    opens com.debi.social_media to javafx.fxml;
    exports com.debi.social_media;
    opens com.debi.social_media.controllers to javafx.fxml;
    exports com.debi.social_media.controllers;
    exports com.debi.social_media.models;
    exports com.debi.social_media.enums;
    exports com.debi.social_media.utils;
    exports com.debi.social_media.exceptions;
}