package com.debi.social_media.controllers;

import com.debi.social_media.AuthManager;
import com.debi.social_media.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import javax.swing.text.View;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML private Text userHeader;
    @FXML private TextField txtSearch;
    @FXML private TextField txtPost;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AuthManager.getInstance().getCurrentUser().nameProperty().addListener((observable, oldValue, newValue) -> {
            userHeader.setText(newValue);
        });
    }
    @FXML
    private void onLogout() {
        AuthManager.getInstance().logout();
        ViewManager.getInstance().pushAndPopView("login");
        ViewManager.getInstance().getCurrentStage().setTitle("Login");

    }
    @FXML
    private void search() {

    }
    @FXML
    private void post() {

    }
    @FXML
    private void showProfile() {

    }

}
