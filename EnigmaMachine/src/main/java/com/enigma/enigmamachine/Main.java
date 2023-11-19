package com.enigma.enigmamachine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EnigmaBasic.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Enigma Machine");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}