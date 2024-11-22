package com.example;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.Scanner;

public class MongoDBUpdateExample {

    public static void main(String[] args) {
        // Set up your MongoDB Atlas connection details
        String clusterUri = "mongodb+srv://Admin:Admin@cluster0.rm3amqo.mongodb.net/?retryWrites=true&w=majority";
        String databaseName = "OOP_Project";
        String collectionName = "Customer";

        // Connect to MongoDB Atlas
        try (MongoClient mongoClient = MongoClients.create(clusterUri)) {
            // Access the specified database and collection
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Get user input for key, value, and data
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the key to update: ");
            String keyToUpdate = scanner.nextLine();

            System.out.print("Enter the new value: ");
            String valueToUpdate = scanner.nextLine();

            System.out.print("Enter the existing data value for the specified key: ");
            String existingData = scanner.nextLine();

            // Specify the filter for the document you want to update
            Document filter = new Document(keyToUpdate, existingData);

            // Specify the update operation
            Document update = new Document("$set", new Document(keyToUpdate, valueToUpdate));

            // Update the document in the collection
            collection.updateOne(filter, update);

            System.out.println("Document updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}