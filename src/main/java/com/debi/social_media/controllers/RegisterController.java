package com.debi.social_media.controllers;

import com.debi.social_media.ViewManager;
import com.debi.social_media.dao.UserDataAccessor;
import com.debi.social_media.enums.Gender;
import com.debi.social_media.models.User;
import com.debi.social_media.utils.CountryLoader;
import com.debi.social_media.utils.PasswordHasher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RegisterController implements Initializable {
    @FXML private TextField txtUser;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPass;
    @FXML private PasswordField txtPassConfirm;
    @FXML private ComboBox<String> txtGender;
    @FXML private ComboBox<String> txtCountry;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtGender.getItems().addAll("Male", "Female");
        txtCountry.getItems().addAll(CountryLoader.getCountries());
    }

    @FXML
    private void Register() {
        if (txtUser.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPass.getText().isEmpty() || txtPassConfirm.getText().isEmpty() || txtCountry.getValue().isEmpty() | txtGender.getValue().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all fields", ButtonType.OK).show();
            return;
        }
        if (!txtPass.getText().equals(txtPassConfirm.getText())) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match", ButtonType.OK).show();
            return;
        }
        User newUser = new User();
        newUser.setName(txtUser.getText());
        newUser.setEmail(txtEmail.getText());
        newUser.setPassword(txtPass.getText());
        newUser.setGender(txtGender.getValue().equals("Male") ? Gender.MALE : Gender.FEMALE);
        newUser.setCountry(txtCountry.getValue());
        try {
            UserDataAccessor userDao = new UserDataAccessor();
            userDao.CreateUser(newUser);
            new Alert(Alert.AlertType.INFORMATION, "Registration Successful", ButtonType.OK).show();
            userDao.Shutdown();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Registration Failed", ButtonType.OK).show();
        }
    }

    @FXML
    private void Back() {
        ViewManager viewManager = ViewManager.getInstance();
        viewManager.pushAndPopView("login");
        viewManager.getCurrentStage().setTitle("Login");

    }
}
