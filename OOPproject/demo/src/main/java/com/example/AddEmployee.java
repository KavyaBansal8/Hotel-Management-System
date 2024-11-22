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

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AddEmployee {

    public static void addEmployee() {
        ArrayList<String> NAMES = new ArrayList<String>();
        ArrayList<Integer> AGES = new ArrayList<Integer>();
        ArrayList<String> GENDER = new ArrayList<String>();
        ArrayList<String> EMAIL = new ArrayList<String>();
        ArrayList<String> ADDRESS = new ArrayList<String>();
        ArrayList<String> PROFESSION = new ArrayList<String>();

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("NEW CUSTOMER");
        window.setMinWidth(650);
        window.setMinHeight(750);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(18);
        grid.setHgap(20);

        // Name Label
        Label Name = new Label("Name");
        GridPane.setConstraints(Name, 0, 0);

        // Name Input
        TextField nameInput = new TextField();
        nameInput.setPromptText("Full name");
        GridPane.setConstraints(nameInput, 1, 0);

        // Age Label
        Label Age = new Label("Age");
        GridPane.setConstraints(Age, 0, 1);

        // Age Input
        TextField ageInput = new TextField();
        ageInput.setPromptText("Age");
        GridPane.setConstraints(ageInput, 1, 1);

        // Gender Label
        Label Gender = new Label("Gender");
        GridPane.setConstraints(Gender, 0, 2);

        // Gender RadioButton
        RadioButton radioButton1 = new RadioButton("Male");
        RadioButton radioButton2 = new RadioButton("Female");
        RadioButton radioButton3 = new RadioButton("Others");

        // Create a ToggleGroup to group the radio buttons
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);
        GridPane.setConstraints(radioButton1, 1, 2);
        GridPane.setConstraints(radioButton2, 2, 2);
        GridPane.setConstraints(radioButton3, 3, 2);

        // Email Label
        Label Email = new Label("EmailId");
        GridPane.setConstraints(Email, 0, 3);

        // Email Input
        TextField emailInput = new TextField();
        emailInput.setPromptText("Password");
        GridPane.setConstraints(emailInput, 1, 3);

        // Address Label
        Label Address = new Label("Address");
        GridPane.setConstraints(Address, 0, 4);

        // Address Input
        TextField addressInput = new TextField();
        addressInput.setPromptText("Address");
        GridPane.setConstraints(addressInput, 1, 4);

        // Profession Label
        Label Profession = new Label("Profession");
        GridPane.setConstraints(Profession, 0, 5);

        // Profession box
        ComboBox<String> comboBox = new ComboBox<>();

        // Add items to the ComboBox
        comboBox.getItems().addAll("Receptionist", "Driver", "Room clearner", "Chef", "Gardener");

        // Set a default value (optional)
        comboBox.setValue("Driver");
        GridPane.setConstraints(comboBox, 1, 5);

        // Return Button
        Button returnButton = new Button("Return");
        GridPane.setConstraints(returnButton, 0, 7);
        returnButton.setOnAction(e -> {
            window.close();
        });

        // Submit button
        Button button = new Button("Submit");
        button.setOnAction(e -> {
            NAMES.add(nameInput.getText());
            AGES.add(Integer.parseInt(ageInput.getText()));
            if (radioButton1.isSelected()) {
                GENDER.add("Male");
            } else if (radioButton2.isSelected()) {
                GENDER.add("Female");
            } else {
                GENDER.add("Others");
            }
            EMAIL.add(emailInput.getText());
            ADDRESS.add(addressInput.getText());
            PROFESSION.add(comboBox.getValue().toString());

            String clusterUri = "mongodb+srv://Admin:Admin@cluster0.rm3amqo.mongodb.net/?retryWrites=true&w=majority";
            String databaseName = "OOP_Project";
            String collectionName = "Employee";

            try (MongoClient mongoClient = MongoClients.create(clusterUri)) {
                // Access the specified database and collection
                MongoDatabase database = mongoClient.getDatabase(databaseName);
                MongoCollection<Document> collection = database.getCollection(collectionName);

                // Create a new document with the specified fields
                Document newDocument = new Document("name", nameInput.getText())
                        .append("age", Integer.parseInt(ageInput.getText()))
                        .append("gender",
                                radioButton1.isSelected() ? "Male" : radioButton2.isSelected() ? "Female" : "Others")
                        .append("email", emailInput.getText())
                        .append("address", addressInput.getText())
                        .append("Profession", comboBox.getValue().toString());

                // Insert the new document into the collection
                collection.insertOne(newDocument);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            for (int i = 0; i < 1; i++) {
                System.out.println(NAMES.get(i));
                System.out.println(AGES.get(i));
                System.out.println(EMAIL.get(i));
                System.out.println(GENDER.get(i));
                System.out.println(ADDRESS.get(i));
                System.out.println(PROFESSION.get(i));
            }
            window.close();
        });
        GridPane.setConstraints(button, 1, 7);

        grid.getChildren().addAll(Name, nameInput, Age, ageInput, Gender, radioButton1, radioButton2, radioButton3,
                Email, emailInput, Address, addressInput, Profession, comboBox, returnButton, button);

        Scene scene = new Scene(grid);
        grid.setAlignment(Pos.CENTER);
        AddEmployee instance = new AddEmployee();
        scene.getStylesheets().add(instance.getClass().getResource("AddEmployee.css").toExternalForm());
        // scene.getStylesheets().add("AddEmployee.css");
        window.setScene(scene);
        window.showAndWait();
    }
}
