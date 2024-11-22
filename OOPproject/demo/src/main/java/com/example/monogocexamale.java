package com.example;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

public class monogocexamale {

    public static void main(String[] args) {
        // Connect to MongoDB Atlas
        MongoClient mongoClient = MongoClients.create("mongodb+srv://Admin:Admin@cluster0.rm3amqo.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("OOP_Project");
        MongoCollection<Document> collection = database.getCollection("Customer");

        // Find all documents in the collection
        FindIterable<Document> documents = collection.find();

        // Iterate over the documents and print them
        for (Document document : documents) {
            System.out.println("Document:");
            System.out.println("_id: " + document.getObjectId("_id"));
            System.out.println("Name: " + document.getString("name"));
            System.out.println("Age: " + document.getInteger("age"));
            // Add other fields...

            System.out.println(); // Separate documents with an empty line
        }

        // Close the MongoDB client
        mongoClient.close();
    }
}


