import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.LinkedList;

public class Connection {

    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    LinkedList<MongoCredential> mc = new LinkedList<>();

    public void createConnection(String user, String databaseName, String collectionName, char[] password){
        MongoCredential credential = MongoCredential.createCredential(user, databaseName, password);
        mc.add(credential);
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017), mc);
        database = mongoClient.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
    }

}


