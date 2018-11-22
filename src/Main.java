import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.*;
import com.mongodb.util.JSON;

public class Main {
    public static void main(String... args){
        Reader reader = new Reader();
        reader.readFile(args[0]);
for (String [] line : reader.list){


    JSONObject obj = new JSONObject();
    try {
        obj.put("Nazwa pola ", line[0])
                .put("Wartosc ", line[1].replace("\"", ""))
                .put("Data ", DateFormatter.stringToDate(line[2]));

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("logDB");
    MongoCollection collection = database.getCollection("logs");
    Document log = new Document();
    log.put("Nazwa pola ", line[0]);
    log.put("Wartosc ", line[1].replace("\"", ""));
    log.put("Data ", DateFormatter.stringToDate(line[2]));
    collection.insertOne(log);

    } catch (JSONException e) {
        e.printStackTrace();
    }
System.out.println(obj);

}

    }
}
