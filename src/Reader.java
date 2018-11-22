import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

    ArrayList<String []> list = new ArrayList<>();
    public void readFile(String path) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(path));
            String dataRow = file.readLine();
            while (dataRow != null) {
                // regex dzielacy z formatu oddzielonego znakiem "="; tu przyklad:  srcIp="192.168.10.1"=data_w_roznych_formatach
                String[] values = dataRow.split("=");
                list.add(values);
                dataRow = file.readLine();
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

