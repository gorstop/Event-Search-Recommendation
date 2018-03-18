package db.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

public class MongoDBTableCreation {
	  // Run as Java application to create MongoDB collections with index.
	  public static void main(String[] args) {

	    // Step 1: Connection to MongoDB
		MongoClient mongoclient = new MongoClient();
		MongoDatabase db = mongoclient.getDatabase(MongoDBUtil.DB_NAME);

	    // Step 2: remove old collections.
		db.getCollection("users").drop();
		db.getCollection("items").drop();

	    // Step 3: create new collections.
		IndexOptions indexOptions = new IndexOptions().unique(true);
		db.getCollection("users").createIndex(new Document("user_id", 3), indexOptions);	
		db.getCollection("items").createIndex(new Document("item_id", 3), indexOptions);	

	    // Step 4: insert fake user data
		db.getCollection("users").insertOne(new Document().append("first_name", "John").append("last_name", "Smith")
				.append("user_id", "1111").append("password", "3229c1097c00d497a0fd282d586be050"));
		db.getCollection("users").insertOne(new Document().append("first_name", "Tianfang").append("last_name", "Gui")
				.append("user_id", "0428").append("password", "37ecf7b9e2f0f74fd676e69ac3b085d7"));
		db.getCollection("users").insertOne(new Document().append("first_name", "Yunrui").append("last_name", "Yang")
				.append("user_id", "1116").append("password", "e556bd413bb98a6a6f93572970a3815e"));

		mongoclient.close();
		
	    System.out.println("Import is done successfully.");
	  }
}
