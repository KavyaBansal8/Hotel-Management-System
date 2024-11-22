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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.*;

public class App extends Application {
    
    Stage window;
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        window= primaryStage;
        window.setTitle("LOGIN");

        GridPane grid= new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Name Label
        Label Username= new Label("Username");
        GridPane.setConstraints(Username,0,0);

        //Name Input
        TextField nameInput= new TextField();
        nameInput.setPromptText("Username");
        GridPane.setConstraints(nameInput,1,0);

        //Password Label
        Label Password= new Label("Password");
        GridPane.setConstraints(Password,0,1);

        //Name Input
        TextField passInput= new TextField();
        passInput.setPromptText("Password");
        GridPane.setConstraints(passInput,1,1);

        //Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e->
        {if (checkUserAndPass(nameInput.getText(),passInput.getText())){
            System.out.println("Alright");
            Welcome.welcome();
            //AddCustomer1.addCustomer1();
            window.close();
        }else{
            AlertBox.display("Invalid username or password","Enter valid username and password");
        }});

        GridPane.setConstraints(loginButton,1,2);
        grid.getChildren().addAll(Username,nameInput,Password,passInput,loginButton);
        grid.setAlignment(Pos.CENTER);
        grid.styleProperty().bind(Bindings.concat("-fx-font-size: ", 
                window.widthProperty().divide(23).asString(), ";"));

        Scene scene= new Scene(grid,300,200);
        //scene.getStylesheets().add("/App.css");
        scene.getStylesheets().add(getClass().getResource("App.css").toExternalForm());

        window.setScene(scene);
        window.show();
    }
    public static boolean checkUserAndPass(String username, String password){

        String USERNAME = "CoderHotel";
        String PASSWORD= "coder1234";

        if (Objects.equals(username,USERNAME) && Objects.equals(password, PASSWORD)){
            return true;
        }
        else{
            return false;
        }
    }
}


// To run the code copy and paste the following line of code.

// & 'C:\Program Files\Eclipse Adoptium\jdk-17.0.8.7-hotspot\bin\java.exe' '@C:\Users\KAVYAB~1\AppData\Local\Temp\cp_c07f3vfbwliu3r6pp4k6ngjll.argfile' '-m' 'com.example/com.example.App' 
