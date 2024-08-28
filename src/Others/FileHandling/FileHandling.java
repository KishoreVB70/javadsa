package Others.FileHandling;

import java.io.*;

public class FileHandling {
    public static void main(String[] args) {

        // 1) Creating a file
        try {
            File f = new File("file.txt");
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
            File f = new File("file.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("Hello world, My first file in Java");
            bw.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // 3) Read from a file
        try {
            File f = new File("file.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }
}
