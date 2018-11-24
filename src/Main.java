
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class Main {
    public static void main(String... args){
        Reader reader = new Reader();
        reader.readFile(args[0]);
for (String [] line : reader.list){

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase database = mongoClient.getDatabase("logDB");
       MongoCollection<Document> collection = database.getCollection("logs");
        Document log = new Document();
        log.append("Nazwa pola ", line[0]);
        log.append("Wartosc ", line[1].replace("\"", ""));
        log.append("Data ", DateFormatter.stringToDate(line[2]));
        collection.insertOne(log);

        mongoClient.close();

}

    }
}
