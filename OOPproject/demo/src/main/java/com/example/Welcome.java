package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import java.util.*;

public class Welcome {
    public static void welcome(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("WELCOME");
        window.setMinWidth(450);
        window.setMinHeight(380);

        Button welcome= new Button("EXPERIENCE YOUR DREAM PLACE");
        welcome.setOnAction(e->{
            Options.options();
            window.close();
        });
        welcome.styleProperty().bind(Bindings.concat("-fx-font-size: ", 
                window.widthProperty().divide(20).asString(), ";"));

        Scene scene = new Scene(welcome);
        Options instance = new Options();
        scene.getStylesheets().add(instance.getClass().getResource("Welcome.css").toExternalForm());
        //scene.getStylesheets().add("Welcome.css");
        window.setScene(scene);
        window.showAndWait();
    }
}
