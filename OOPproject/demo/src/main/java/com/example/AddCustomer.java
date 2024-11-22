package com.example;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class AddCustomer {
    public static void addCustomer() {
        
        ArrayList<Integer> AGES = new ArrayList<Integer>();   
        ArrayList<String> GENDER = new ArrayList<String>();
        ArrayList<String> EMAIL = new ArrayList<String>();
        ArrayList<String> ADDRESS = new ArrayList<String>();
        ArrayList<Integer> KAMRE = new ArrayList<Integer>();
    
        ArrayList<String> ARRIVAL = new ArrayList<String>();
        ArrayList<String> DEPARTURE = new ArrayList<String>();
        
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

        // Room Label
        Label Room = new Label("Room");
        GridPane.setConstraints(Room, 0, 5);

        // Room list
        ListView<Integer> list = new ListView<Integer>();
        ArrayList<Integer> Kamre;
        //public static final ArrayList<Integer> ROOM = new ArrayList<>(Arrays.asList(101, 102, 103));
        if (Options.ROOM != null) {
            Kamre = new ArrayList<>(Options.ROOM);
        } else {
            // Handle the case where Options.ROOM is null
            System.err.println("Error: Options.ROOM is null. Initializing with default values.");
            Kamre = new ArrayList<>(Arrays.asList(101, 102, 103,104,105,106,107,108,109,110));  // You can provide default values here
        }
        
        list.getItems().setAll(Kamre);
      
    
        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        GridPane.setConstraints(list, 1, 5);

        // Arrival Label
        Label Arrival = new Label("Arrival");
        GridPane.setConstraints(Arrival, 0, 6);

        // Arrival Input
        DatePicker arrivalDate = new DatePicker();
        GridPane.setConstraints(arrivalDate, 1, 6);

        // Departure Label
        Label Departure = new Label("Departure");
        GridPane.setConstraints(Departure, 0, 7);

        // Arrival Input
        DatePicker departureDate = new DatePicker();
        GridPane.setConstraints(departureDate, 1, 7);

        // Return Button
        Button returnButton = new Button("Return");
        GridPane.setConstraints(returnButton, 0, 8);
        returnButton.setOnAction(e -> {
            window.close();
        });


        // Submit button
        Button button = new Button("Submit");
        button.setOnAction(e -> {
            // Your existing data collection code...

            // MongoDB Atlas connection details
            String clusterUri = "mongodb+srv://Admin:Admin@cluster0.rm3amqo.mongodb.net/?retryWrites=true&w=majority";
            String databaseName = "OOP_Project";
            String collectionName = "Customer";
            
            try (MongoClient mongoClient = MongoClients.create(clusterUri)) {
                // Access the specified database and collection
                MongoDatabase database = mongoClient.getDatabase(databaseName);
                MongoCollection<Document> collection = database.getCollection(collectionName);
                
                // Create a new document with the specified fields
                Document newDocument = new Document("name", nameInput.getText())
                        .append("age", Integer.parseInt(ageInput.getText()))
                        .append("gender", radioButton1.isSelected() ? "Male" : radioButton2.isSelected() ? "Female" : "Others")
                        .append("email", emailInput.getText())
                        .append("address", addressInput.getText())
                       .append("room", list.getSelectionModel().getSelectedItem())
                        .append("arrival", arrivalDate.getValue().toString())
                        .append("departure", departureDate.getValue().toString());
                     
                // Insert the new document into the collection
                collection.insertOne(newDocument);
                Options.ROOM.remove(list.getSelectionModel().getSelectedItem());
                System.out.println("Document added successfully to MongoDB!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            window.close();
        });
        GridPane.setConstraints(button, 1, 8);
        grid.getChildren().addAll(Name, nameInput, Age, ageInput, Gender, radioButton1, radioButton2, radioButton3,
                Email, emailInput, Address, addressInput, Room, list, Arrival, arrivalDate, Departure, departureDate,
                returnButton, button);
        grid.styleProperty().bind(Bindings.concat("-fx-font-size: ", 
                window.widthProperty().divide(40).asString(), ";"));
        Scene scene = new Scene(grid);
        grid.setAlignment(Pos.CENTER);
     
        AddCustomer1 instance = new AddCustomer1();
        scene.getStylesheets().add(instance.getClass().getResource("AddCustomer.css").toExternalForm());
     

        window.setScene(scene);
        window.showAndWait();
    }
}



// package com.example;

// import javafx.application.Application;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.DatePicker;
// import javafx.scene.control.Label;
// import javafx.scene.control.ListView;
// import javafx.scene.control.RadioButton;
// import javafx.scene.control.SelectionMode;
// import javafx.scene.control.TextField;
// import javafx.scene.control.ToggleGroup;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.StackPane;
// import javafx.stage.Modality;
// import javafx.stage.Stage;
// import java.util.*;

// public class AddCustomer {
//         // public ArrayList<String> NAMES;
//         // public ArrayList<Integer> AGES;
//         // public ArrayList<String> GENDER;
//         // public ArrayList<String> EMAIL;
//         // public ArrayList<String> ADDRESS;
//         // public ArrayList<Integer> KAMRE;
//         // public ArrayList<String> ARRIVAL;
//         // public ArrayList<String> DEPARTURE;
//         // public void someMethod() {
//         //     // Calling getClass() in a non-static context
//         //     Class<? extends AddCustomer> c = getClass();
//         // } 



//     public static void addCustomer() {
//         ArrayList<String> NAMES = new ArrayList<String>();
//         ArrayList<Integer> AGES = new ArrayList<Integer>();   
//         ArrayList<String> GENDER = new ArrayList<String>();
//         ArrayList<String> EMAIL = new ArrayList<String>();
//         ArrayList<String> ADDRESS = new ArrayList<String>();
//         ArrayList<Integer> KAMRE = new ArrayList<Integer>();
//         ArrayList<String> ARRIVAL = new ArrayList<String>();
//         ArrayList<String> DEPARTURE = new ArrayList<String>();
        
//         Stage window = new Stage();
//         window.initModality(Modality.APPLICATION_MODAL);
//         window.setTitle("NEW CUSTOMER");
//         window.setMinWidth(650);
//         window.setMinHeight(750);

//         GridPane grid = new GridPane();
//         grid.setPadding(new Insets(10, 10, 10, 10));
//         grid.setVgap(18);
//         grid.setHgap(20);

//         // Name Label
//         Label Name = new Label("Name");
//         GridPane.setConstraints(Name, 0, 0);

//         // Name Input
//         TextField nameInput = new TextField();
//         nameInput.setPromptText("Full name");
//         GridPane.setConstraints(nameInput, 1, 0);

//         // Age Label
//         Label Age = new Label("Age");
//         GridPane.setConstraints(Age, 0, 1);

//         // Age Input
//         TextField ageInput = new TextField();
//         ageInput.setPromptText("Age");
//         GridPane.setConstraints(ageInput, 1, 1);

//         // Gender Label
//         Label Gender = new Label("Gender");
//         GridPane.setConstraints(Gender, 0, 2);

//         // Gender RadioButton
//         RadioButton radioButton1 = new RadioButton("Male");
//         RadioButton radioButton2 = new RadioButton("Female");
//         RadioButton radioButton3 = new RadioButton("Others");

//         // Create a ToggleGroup to group the radio buttons
//         ToggleGroup toggleGroup = new ToggleGroup();
//         radioButton1.setToggleGroup(toggleGroup);
//         radioButton2.setToggleGroup(toggleGroup);
//         radioButton3.setToggleGroup(toggleGroup);
//         GridPane.setConstraints(radioButton1, 1, 2);
//         GridPane.setConstraints(radioButton2, 2, 2);
//         GridPane.setConstraints(radioButton3, 3, 2);

//         // Email Label
//         Label Email = new Label("EmailId");
//         GridPane.setConstraints(Email, 0, 3);

//         // Email Input
//         TextField emailInput = new TextField();
//         emailInput.setPromptText("Password");
//         GridPane.setConstraints(emailInput, 1, 3);

//         // Address Label
//         Label Address = new Label("Address");
//         GridPane.setConstraints(Address, 0, 4);

//         // Address Input
//         TextField addressInput = new TextField();
//         addressInput.setPromptText("Address");
//         GridPane.setConstraints(addressInput, 1, 4);

//         // Room Label
//         Label Room = new Label("Room");
//         GridPane.setConstraints(Room, 0, 5);

//         // Room list
//         ListView<Integer> list = new ListView<Integer>();
//         ArrayList<Integer> Kamre = Options.ROOM;
//         list.getItems().setAll(Kamre);
//         list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//         GridPane.setConstraints(list, 1, 5);

//         // Arrival Label
//         Label Arrival = new Label("Arrival");
//         GridPane.setConstraints(Arrival, 0, 6);

//         // Arrival Input
//         DatePicker arrivalDate = new DatePicker();
//         GridPane.setConstraints(arrivalDate, 1, 6);

//         // Departure Label
//         Label Departure = new Label("Departure");
//         GridPane.setConstraints(Departure, 0, 7);

//         // Arrival Input
//         DatePicker departureDate = new DatePicker();
//         GridPane.setConstraints(departureDate, 1, 7);

//         // Return Button
//         Button returnButton = new Button("Return");
//         GridPane.setConstraints(returnButton, 0, 8);
//         returnButton.setOnAction(e -> {
//             window.close();
//         });

//         // Submit button
//         Button button = new Button("Submit");
//         button.setOnAction(e -> {
//             NAMES.add(nameInput.getText());
//             AGES.add(Integer.parseInt(ageInput.getText()));
//             if (radioButton1.isSelected()) {
//                 GENDER.add("Male");
//             } else if (radioButton2.isSelected()) {
//                 GENDER.add("Female");
//             } else {
//                 GENDER.add("Others");
//             }

//             EMAIL.add(emailInput.getText());
//             ADDRESS.add(addressInput.getText());
//             KAMRE.add(list.getSelectionModel().getSelectedItem());
//             Options.ROOM.remove(list.getSelectionModel().getSelectedItem());
//             ARRIVAL.add(arrivalDate.getValue().toString());
//             DEPARTURE.add(departureDate.getValue().toString());
            
//             for (int i = 0; i < 1; i++) {
//                 System.out.println(NAMES.get(i));
//                 System.out.println(AGES.get(i));
//                 System.out.println(EMAIL.get(i));
//                 System.out.println(GENDER.get(i));
//                 System.out.println(ADDRESS.get(i));
//                 System.out.println(KAMRE.get(i));
//                 System.out.println(ARRIVAL.get(i));
//                 System.out.println(DEPARTURE.get(i));
//             }
//             window.close();
//         });
//         GridPane.setConstraints(button, 1, 8);

//         grid.getChildren().addAll(Name, nameInput, Age, ageInput, Gender, radioButton1, radioButton2, radioButton3,
//                 Email, emailInput, Address, addressInput, Room, list, Arrival, arrivalDate, Departure, departureDate,
//                 returnButton, button);

//         Scene scene = new Scene(grid);
//         grid.setAlignment(Pos.CENTER);
//         //scene.getStylesheets().add("AddCustomer.css");
//         AddCustomer instance = new AddCustomer();

//         // Calling getClass() using an instance in a static context
//         //Class<? extends AddCustomer> c = instance.getClass();
//         scene.getStylesheets().add(instance.getClass().getResource("AddCustomer.css").toExternalForm());
//        //scene.getStylesheets().add("/" + AddCustomer.class.getPackage().getName().replace('.', '/') + "/AddCustomer.css");

//         window.setScene(scene);
//         window.showAndWait();
//     }
// }

