package com.atguigu;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;

/**
 * @Author: ljg
 * @Date: 2025/9/10 11:30 AM Wednesday
 * @Description:
 */
public class MongoTest {
    public static void main(String[] args) {
        String url = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(url);
        MongoDatabase db = mongoClient.getDatabase("bookstore");
        MongoCollection<Document> collection = db.getCollection("user");
        Document user = new Document("username", "zhangsan")
                .append("email", "zhangsan@example.com")
                .append("age", 25)
                .append("createdAt", new Date());
        collection.insertOne(user);
        System.out.println("User inserted!");

    }
}
