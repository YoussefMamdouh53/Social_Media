package com.debi.social_media;

//import com.debi.social_media.views.LoginRegisterView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        ViewManager viewManager = ViewManager.getInstance();
        viewManager.addView("login", "views/LoginView.fxml");
        viewManager.addView("register", "views/RegisterView.fxml");
        viewManager.addView("main", "views/MainView.fxml");
        viewManager.pushView("login");
        viewManager.getCurrentStage().setTitle("Login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}