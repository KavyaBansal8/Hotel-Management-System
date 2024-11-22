package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.*;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class AddRoom {
    public static void addRoom(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("CUSTOMER DETAILS");
        window.setMinWidth(650);
        window.setMinHeight(750);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(18);
        grid.setHgap(20);

        // add room heading label
        Label addroom = new Label("ADD ROOM");
        GridPane.setConstraints(addroom, 1, 0);

        // add room heading label
        Label room = new Label("ROOM NUMBER: ");
        GridPane.setConstraints(room, 0, 1);

        // room input field
        TextField SearchField = new TextField();
        SearchField.setPromptText("Add room");
        GridPane.setConstraints(SearchField, 1, 1);

        // Search Button
        Button roombutton = new Button("Add Room");
        GridPane.setConstraints(roombutton, 1, 2);
        roombutton.setOnAction(e->{
            Options.ROOM.add(Integer.parseInt(SearchField.getText()));
            window.close();
        });
        
        grid.getChildren().addAll(addroom,room,SearchField,roombutton);

        Scene scene = new Scene(grid);
        grid.setAlignment(Pos.CENTER);
        AddEmployee instance = new AddEmployee();
        scene.getStylesheets().add(instance.getClass().getResource("AddRoom.css").toExternalForm());
        // scene.getStylesheets().add("AddEmployee.css");
        window.setScene(scene);
        window.showAndWait();
    }   
}

