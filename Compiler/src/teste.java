import parser.ParseException;
import parser.linguagem2019;

import java.io.*;

public class teste {
    public static void main(String[] args) {
        try {
            FileInputStream fileInput = new FileInputStream("/home/fabio/eclipse-workspace/Univali/Compiler/src/test.txt");
            InputStream aaaaa = new StringBufferInputStream("this program 9834");
            linguagem2019 a = new linguagem2019(fileInput);
            // Create a stream to hold the output
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            // IMPORTANT: Save the old System.out!
            PrintStream old = System.out;
            // Tell Java to use your special stream
            System.setOut(ps);
            // Print some output: goes to your special stream
            a.Start();
            // Put things back
            System.out.flush();
            System.setOut(old);
            // Show what happened
            System.out.println("Here: " + baos.toString());


        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }
}
