package Others.FileHandling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandling {
    public static void main(String[] args) {

        // 1) Creating a file
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

        // 2) Writing to a file
        try {
            File f = new File("f.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("Hello world, My first file in Java");
            bw.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
