import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public  class FilesWriter {
    ArrayList<String> list = new ArrayList<>();
    //Function to create a file and save  data inside it
    public static void write(String filename, ArrayList<String> list ) throws IOException {
        FileWriter filewriter = new FileWriter(filename);
        for (int i = 0; i < list.size(); i++) {
            filewriter.write(list.get(i) + "\n" );

        }
        filewriter.close();
    }
}