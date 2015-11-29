package simple;

import java.io.*;

/**
 * Created by Dmytro on 29.11.15.
 */
public class CreateStringsInFile {
    public static void main(String[] args) throws IOException, InterruptedException {

        File file = new File("data.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        int n = 0;

//        for (int i = 0; i < 10; i++) {
//            writer.write("Hello");
//            writer.newLine();
//        }


        for (int j = 1; j <= 100_000; j++) {

            int id = (int) (Math.random() * 1000);
            int amt = (int) (Math.random() * 1000);

            String result = id + ";" + amt;
            writer.write(result);
            writer.newLine();

            n++;

        }

        writer.close();

    }
}
