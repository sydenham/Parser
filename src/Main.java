
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class Main {
    public static void main(String... args) {
        ByteArrayOutputStream file = new ByteArrayOutputStream();
        if (args.length != 5) {
            System.out.println("Niepoprawna ilosc argumentow");
            return;
        }
        Reader reader = new Reader();
        reader.readFile(args[0]);
        if(reader.list.isEmpty()){
            System.out.println("Nie ma danych do umieszczenia w bazie");
            return;
        }
        System.setErr(new PrintStream(file));
        Connection connection = new Connection();
        connection.createConnection(args[1], args[2], args[3], args[4].toCharArray());

        DataLoader dataLoader = new DataLoader();
        dataLoader.loadData(reader, connection, file);


        connection.mongoClient.close();
        System.out.println("Rozlaczono z baza");
    }
}
