package com.example.c195task1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

/**
 * The entry point of the application
 */
public class Main extends Application {
    /**
     * The method that kicks off the application
     * @param stage the JavaFX stage on which the application will be displayed
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log In");
        if (Locale.getDefault().getLanguage().equals("fr")) {
            stage.setTitle("Connectez");
        }
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The called main function that will launch the application
     * @param args command line arguments, if there are any
     */
    public static void main(String[] args) {
        //Locale.setDefault(Locale.FRENCH);
        System.out.println(System.getProperty( "javafx.runtime.version" ));
        launch();
    }
}