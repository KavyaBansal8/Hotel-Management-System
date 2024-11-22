package com.example;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
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
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ShowCustomerDetails {
    public static void detailsShow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("CUSTOMER DETAILS");
        window.setMinWidth(650);
        window.setMinHeight(750);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(18);
        grid.setHgap(20);

        // search label
        Label Search = new Label("Search Customer");
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

        // Room label
        Label Room = new Label("Room:   ");
        GridPane.setConstraints(Room, 0, 7);

        // Arrival label
        Label Arrival = new Label("Arrival Date:   ");
        GridPane.setConstraints(Arrival, 0, 8);

        // Departure label
        Label Departure = new Label("Departure Date:   ");
        GridPane.setConstraints(Departure, 0, 9);

        // Access the specified database and collection
        MongoDatabase database = mongoClient.getDatabase("OOP_Project");
        MongoCollection<Document> collection = database.getCollection("Customer");

        SearchButton.setOnAction(e -> {
            String searchName = SearchField.getText();
            System.out.println("Finding ");
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
                System.out.println("Room: " + document.getInteger("room"));
                System.out.println("Arrival: " + document.getString("arrival"));
                System.out.println("Departure: " + document.getString("departure"));
                System.out.println(); // Separate documents with an empty line

                // CName label
                Label CName = new Label(document.getString("name"));
                GridPane.setConstraints(CName, 1, 2);

                // CAge label
                Label CAge = new Label((document.getInteger("age")).toString());
                GridPane.setConstraints(CAge, 1, 3);

                // CGender label
                Label CGender = new Label(document.getString("gender"));
                GridPane.setConstraints(CGender, 1, 4);

                // Email label
                Label CEmail = new Label(document.getString("email"));
                GridPane.setConstraints(CEmail, 1, 5);

                // Address label
                Label CAddress = new Label(document.getString("address"));
                GridPane.setConstraints(CAddress, 1, 6);

                // Room label
                Label CRoom = new Label(document.getInteger("room").toString());
                GridPane.setConstraints(CRoom, 1, 7);

                // Arrival label
                Label CArrival = new Label(document.getString("arrival"));
                GridPane.setConstraints(CArrival, 1, 8);

                // Departure label
                Label CDeparture = new Label(document.getString("departure"));
                GridPane.setConstraints(CDeparture, 1, 9);

                grid1.getChildren().addAll(Search, SearchField, SearchButton, Name, CName, Age, CAge, Gender, Email,
                        Address, Room, Arrival, Departure, CGender, CEmail, CAddress, CRoom, CArrival, CDeparture);
                grid1.setAlignment(Pos.CENTER);
                grid1.styleProperty().bind(Bindings.concat("-fx-font-size: ",
                        window.widthProperty().divide(23).asString(), ";"));
                Scene scene2 = new Scene(grid1, 600, 700);
                ShowCustomerDetails instance = new ShowCustomerDetails();
                scene2.getStylesheets().add(instance.getClass().getResource("ShowCustomerDetails.css").toExternalForm());
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
        ShowCustomerDetails instance = new ShowCustomerDetails();
        scene.getStylesheets().add(instance.getClass().getResource("ShowCustomerDetails.css").toExternalForm());
        window.setScene(scene);
        window.showAndWait();
    }
}
