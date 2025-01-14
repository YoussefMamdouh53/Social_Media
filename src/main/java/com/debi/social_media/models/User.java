package com.debi.social_media.models;

import com.debi.social_media.enums.Gender;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty email;
    private StringProperty password;
    private Gender gender;
    private StringProperty country;
    private StringProperty biography;

    public User() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.gender = Gender.MALE;
        this.country = new SimpleStringProperty();
        this.biography = new SimpleStringProperty();
    }
    public User(int id, String name, String email, String password, Gender gender, String country, String biography) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.gender = gender;
        this.country = new SimpleStringProperty(country);
        this.biography = new SimpleStringProperty(biography);
    }
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public Gender getGender() {
        return gender;
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public String getBiography() {
        return biography.get();
    }

    public StringProperty biographyProperty() {
        return biography;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public void setBiography(String biography) {
        this.biography.set(biography);
    }

    public void setId(int id) {
        this.id.set(id);
    }
}
