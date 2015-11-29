package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Dmytro on 29.11.15.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader("result.txt"));
        BufferedReader reader2 = new BufferedReader(new FileReader("result2.txt"));

        String s1;
        String s2;



        while (reader1.ready() && reader2.ready()) {
            s1 = reader1.readLine();
            s2 = reader2.readLine();
            if(!s1.equals(s2)) {
                System.out.println("Fuck");
                System.out.println("s1 " + s1 + " s2 " + s2);
            }
        }

        System.out.println("Good");
    }
}
