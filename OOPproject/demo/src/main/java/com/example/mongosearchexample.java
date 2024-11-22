package com.example;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class mongosearchexample {

    public static void main(String[] args) {
        // Connect to MongoDB Atlas
        MongoClient mongoClient = MongoClients.create("mongodb+srv://Admin:Admin@cluster0.rm3amqo.mongodb.net/?retryWrites=true&w=majority");

        // Access the specified database and collection
        MongoDatabase database = mongoClient.getDatabase("OOP_Project");
        MongoCollection<Document> collection = database.getCollection("Customer");

        // Get user input for the name to be queried
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name to search: ");
        String searchName = scanner.nextLine();

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
        }

        // Close the MongoDB connection
        mongoClient.close();
    }
}


