package com.example;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.*;

public class Options {
    static ArrayList<Integer> ROOM = new ArrayList<Integer>();
    public static void options() {
        
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("CODER HOTELS");
        window.setMinWidth(450);
        window.setMinHeight(380);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(20);
        grid.setHgap(50);

        // Receptionist Label
        Label Receptionist = new Label("Receptionist");
        GridPane.setConstraints(Receptionist, 0, 0);
        Receptionist.setUnderline(true);

        // receptionist buttons
        Button RecepButton = new Button("ADD CUSTOMER");
        GridPane.setConstraints(RecepButton, 0, 1);
        RecepButton.setOnAction(e->AddCustomer.addCustomer());

        Button ShowDetails = new Button("Show Customer Details");
        GridPane.setConstraints(ShowDetails, 0, 2);
        ShowDetails.setOnAction(e->ShowCustomerDetails.detailsShow());

        //room details
        for (int i=1;i<100;i++){
            ROOM.add(i);
        }

        // Manager Label
        Label Manager = new Label("Manager");
        GridPane.setConstraints(Manager, 1, 0);
        Manager.setUnderline(true);

        // Manager buttons
        Button ManagerButton = new Button("ADD EMPLOYEE");
        GridPane.setConstraints(ManagerButton, 1, 1);
        ManagerButton.setOnAction(e->AddEmployee.addEmployee());

        Button ShowEmpDetails= new Button("Show Emp Details");
        GridPane.setConstraints(ShowEmpDetails, 1, 2);
        ShowEmpDetails.setOnAction(e->ShowEmployeeDetails.detailsEmpShow());

        Button ADDROOM= new Button("ADD ROOM");
        GridPane.setConstraints(ADDROOM, 1, 3);
        ADDROOM.setOnAction(e->AddRoom.addRoom());

        grid.getChildren().addAll(Receptionist,RecepButton,ShowDetails, Manager, ManagerButton,ShowEmpDetails, ADDROOM);
        grid.setAlignment(Pos.CENTER);

        grid.styleProperty().bind(Bindings.concat("-fx-font-size: ", 
                window.widthProperty().divide(30).asString(), ";"));

        Scene scene = new Scene(grid);
        Options instance = new Options();
        scene.getStylesheets().add(instance.getClass().getResource("Options.css").toExternalForm());
        //scene.getStylesheets().add("Welcome.css");
        window.setScene(scene);
        window.showAndWait();
    }
}
