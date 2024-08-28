package Others.FileHandling;

import java.io.File;
import java.io.IOException;

public class FileHandling {
    public static void main(String[] args) {
        try {
            File f = new File("f.txt");
            boolean result = f.createNewFile();
            if(result) {
                System.out.println("File created");
            } else {
                System.out.println("File already exists");
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
