package com.debi.social_media.controllers;

import com.debi.social_media.AuthManager;
import com.debi.social_media.ViewManager;
import com.debi.social_media.exceptions.UserNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void onLogin() {
        if (txtUser.getText().isEmpty() || txtPass.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields");
            alert.showAndWait();
            return;
        }
        try {
            if (AuthManager.getInstance().login(txtUser.getText(), txtPass.getText())){
                ViewManager.getInstance().pushAndPopView("main");
                ViewManager.getInstance().getCurrentStage().setTitle("Social Media App");
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Invalid Credentials").showAndWait();
            }

        } catch (UserNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Credentials").showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Internal Error: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void onRegister() throws IOException {
        ViewManager viewManager = ViewManager.getInstance();
        viewManager.pushAndPopView("register");
        viewManager.getCurrentStage().setTitle("Register");
    }

}
