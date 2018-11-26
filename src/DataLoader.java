import com.mongodb.MongoException;
import org.bson.Document;

import java.io.ByteArrayOutputStream;
import java.util.Date;

public class DataLoader {

    public void loadData(Reader reader, Connection connection, ByteArrayOutputStream file){
        for (String[] line : reader.list) {
            Date date;
            date = DateFormatter.stringToDate(line[2]);
            if (date == null) {
                connection.mongoClient.close();
                System.out.println("Rozlaczono z baza z powodu bledu daty");
                return;
            }
            Document log = new Document();
            log.append("Nazwa pola ", line[0]);
            log.append("Wartosc ", line[1].replace("\"", ""));
            log.append("Data ", date);

            try {
                connection.collection.insertOne(log);
                System.out.println("Zamieszczono w bazie " + log);
            } catch (MongoException e) {
                String texto = new String(file.toByteArray());
                if (texto.contains("'Authentication failed.'")) {
                    System.out.println("Niepoprawny user, haslo lub baza danych");
                } else if (texto.contains("'port is not valid on remote machine'")) {
                    System.out.println("Niepoprawny port");
                } else if (texto.contains("'UnknownHostException:'")) {
                    System.out.println("Niepoprawny host");
                }
                System.out.println("Blad polaczenia z baza");
                connection.mongoClient.close();
            }
        }
    }

}
