package com.debi.social_media;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

public class ViewManager {
    private static final ViewManager _instance = new ViewManager();
    private final HashMap<String, String> _views;
    private final Stack<Stage> stageStack;

    private ViewManager() {
        _views = new HashMap<>();
        stageStack = new Stack<>();
    }
    public void addView(String viewName, String viewPath) {
        if (_views.containsKey(viewName) || viewPath == null || viewName.isEmpty()) {
            return;
        }
        _views.put(viewName, viewPath);
    }
    private Scene loadView(String view){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(_views.get(view))));
            return new Scene(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void pushView(String viewName) {
        Stage stage = new Stage();
        stage.setScene(loadView(viewName));
        stage.setResizable(false);
        stage.show();
        stageStack.push(stage);

        stage.setOnCloseRequest(event -> {
            if (stage == stageStack.peek()) {
                popView();
            }
            else {
                event.consume();
            }
        });
    }
    public void pushAndPopView(String viewName) {
        popView();
        pushView(viewName);
    }

    public void popView() {
        if (stageStack.isEmpty()) {
            return;
        }
        Stage stage = stageStack.pop();
        stage.close();
    }

    public Stage getCurrentStage() {
        return stageStack.peek();
    }

    public static ViewManager getInstance() {
        return _instance;
    }
}
