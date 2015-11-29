package simple;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Dmytro on 29.11.15.
 */
public class ReadResult {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
        BufferedReader reader2 = new BufferedReader(new FileReader("result.txt"));

        int sum1 = 0;
        int sum2 = 0;

        while(reader.ready()){
            String s = reader.readLine();
            String[] ar = s.split(";");
            sum1 += Integer.parseInt(ar[1]);
        }
        reader.close();

        while(reader2.ready()){
            String s = reader2.readLine();
            String[] ar = s.split(" ");
            sum2 += Integer.parseInt(ar[3]);
        }
        reader2.close();

        System.out.println(sum1);

    }
}
