import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.junit.Assert;

import java.util.LinkedList;

public class ConnectionTest {

    @org.junit.Test
    public void testCreateConnection() {
        String testUser = "testUser";
        String testDB = "testDB";
        String testColl = "testColl";
        char[] pass = "pass".toCharArray();

        LinkedList<MongoCredential> mc = new LinkedList<>();
        MongoCredential testCredential = MongoCredential.createCredential(testUser, testDB, pass);
        mc.add(testCredential);
        MongoClient testClient = new MongoClient(new ServerAddress("localhost", 27017), mc);

        Connection connection = new Connection();
        connection.createConnection(testUser, testDB, testColl, pass);
        Assert.assertEquals(testClient.toString(), connection.mongoClient.toString());
    }
}