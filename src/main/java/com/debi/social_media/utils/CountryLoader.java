package com.debi.social_media.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountryLoader
{
    public static List<String> getCountries() {
        List<String> countries = new ArrayList<>();
        File countriesFile = new File("countries.txt");
        try {
            Scanner sc = new Scanner(countriesFile);
            while (sc.hasNextLine()) {
                countries.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Cannot load countries.txt").showAndWait();
        }
        return countries;
    }
}
