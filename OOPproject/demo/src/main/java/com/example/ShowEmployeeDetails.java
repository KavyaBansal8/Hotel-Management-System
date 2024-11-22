package com.example;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.*;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ShowEmployeeDetails {
    public static void detailsEmpShow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("EMPLOYEE DETAILS");
        window.setMinWidth(650);
        window.setMinHeight(750);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(18);
        grid.setHgap(20);

        // search label
        Label Search = new Label("Search EMPLOYEE");
        GridPane.setConstraints(Search, 0, 0);

        // search field
        TextField SearchField = new TextField();
        SearchField.setPromptText("Search here");
        GridPane.setConstraints(SearchField, 1, 0);

        // Search Button
        Button SearchButton = new Button("Search");
        GridPane.setConstraints(SearchButton, 1, 1);

        MongoClient mongoClient = MongoClients
                .create("mongodb+srv://Admin:Admin@cluster0.rm3amqo.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("OOP_Project");
        MongoCollection<Document> collection = database.getCollection("Employee");

        GridPane grid1 = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(18);
        grid.setHgap(20);

        // Name label
        Label Name = new Label("Name:   ");
        GridPane.setConstraints(Name, 0, 2);

        // Age label
        Label Age = new Label("Age:   ");
        GridPane.setConstraints(Age, 0, 3);

        // Gender label
        Label Gender = new Label("Gender:   ");
        GridPane.setConstraints(Gender, 0, 4);

        // Email label
        Label Email = new Label("Email:   ");
        GridPane.setConstraints(Email, 0, 5);

        // Address label
        Label Address = new Label("Address:   ");
        GridPane.setConstraints(Address, 0, 6);

        // Profession label
        Label Profession = new Label("Profession:   ");
        GridPane.setConstraints(Profession, 0, 7);

        SearchButton.setOnAction(e -> {
            String searchName = SearchField.getText();
            System.out.println("Finding");
            System.out.println(searchName);

            // Create a query to find documents with the specified name
            Document query = new Document("name", searchName);

            // Find all documents matching the query
            FindIterable<Document> documents = collection.find(query);

            // Iterate over the documents and print them
            for (Document document : documents) {
                System.out.println("Document:");
                System.out.println("_id: " + document.getObjectId("_id"));
                System.out.println("Name: " + document.getString("name"));
                System.out.println("Age: " + document.getInteger("age"));
                System.out.println("Gender: " + document.getString("gender"));
                System.out.println("Email: " + document.getString("email"));
                System.out.println("Address: " + document.getString("address"));
                System.out.println("Profession: " + document.getString("Profession"));
                System.out.println(); // Separate documents with an empty line

                // CName label
                Label EName = new Label(document.getString("name"));
                GridPane.setConstraints(EName, 1, 2);

                // CAge label
                Label EAge = new Label((document.getInteger("age")).toString());
                GridPane.setConstraints(EAge, 1, 3);

                // CGender label
                Label EGender = new Label(document.getString("gender"));
                GridPane.setConstraints(EGender, 1, 4);

                // Email label
                Label EEmail = new Label(document.getString("email"));
                GridPane.setConstraints(EEmail, 1, 5);

                // Address label
                Label EAddress = new Label(document.getString("address"));
                GridPane.setConstraints(EAddress, 1, 6);

                // Profession label
                Label EProfession = new Label(document.getString("Profession"));
                GridPane.setConstraints(EProfession, 1, 7);

                grid1.getChildren().addAll(Search, SearchField, SearchButton, Name, EName, Age, EAge, Gender, Email,
                        Address,Profession, EGender, EAddress, EEmail, EProfession);
                grid1.setAlignment(Pos.CENTER);
                grid1.styleProperty().bind(Bindings.concat("-fx-font-size: ",
                        window.widthProperty().divide(23).asString(), ";"));
                Scene scene2 = new Scene(grid1, 600, 700);
                ShowEmployeeDetails instance = new ShowEmployeeDetails();
                scene2.getStylesheets().add(instance.getClass().getResource("ShowEmployeeDetails.css").toExternalForm());
                window.setScene(scene2);
            }
            // Close the MongoDB connection
            mongoClient.close();
        });

        grid.getChildren().addAll(Search, SearchField, SearchButton);
        grid.styleProperty().bind(Bindings.concat("-fx-font-size: ",
                window.widthProperty().divide(23).asString(), ";"));
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid);
        ShowEmployeeDetails instance = new ShowEmployeeDetails();
        scene.getStylesheets().add(instance.getClass().getResource("ShowEmployeeDetails.css").toExternalForm());
        window.setScene(scene);
        window.showAndWait();

    }
}
