package simple;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro on 29.11.15.
 */
public class ReadFile {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"));
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        long start = System.currentTimeMillis();

        while (reader.ready()) {
            String s = reader.readLine();
            String[] ar = s.split(";");
            int id = Integer.parseInt(ar[0]);
            int amt = Integer.parseInt(ar[1]);
            int prevAmt = 0;
            if (map.containsKey(id))
                prevAmt = map.get(id);
            map.put(id, prevAmt + amt);
        }
        reader.close();

        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            String result = "id " + pair.getKey() + " amount: " + pair.getValue();
            writer.write(result);
            writer.newLine();
        }

        writer.close();

        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
        System.out.println("There are " + map.size() + " ids");
    }
}
