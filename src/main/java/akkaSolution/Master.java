package akkaSolution;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.collection.mutable.ArraySeq;
import akka.routing.RoundRobinRouter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro on 29.11.15.
 */
public class Master extends UntypedActor {

    private String filename;
    private int processFileCalls = 0;
    private int processMapCalls = 0;
    private ActorRef workerRouter;
    private HashMap<Integer, Integer> generalMap = new HashMap();

    public Master() {
        workerRouter = this.getContext().actorOf(Worker.createWorker().withRouter(new RoundRobinRouter(8)), "workerRouter");
    }

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof String) {
            filename = (String) message;
            processFile(filename);
        } else if (message instanceof Result) {
            processMap(message);
        } else
            unhandled(message);


    }


    private void processFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        while (reader.ready()) {
            ArrayList<String> buff = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                buff.add(reader.readLine());
            }

            workerRouter.tell(new Work(buff), getSelf());

            processFileCalls++;
        }
        reader.close();
    }

    private void processMap(Object message) {
        if (message instanceof Result) {
            Map<Integer, Integer> map = ((Result) message).getMap();

            for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
                int id = pair.getKey();
                int amt = pair.getValue();
                int prevAmt = 0;

                if (generalMap.containsKey(id))
                    prevAmt = generalMap.get(id);

                generalMap.put(id, prevAmt + amt);
            }
            processMapCalls++;

            if (processFileCalls == processMapCalls) {
                System.out.println("Good");
                try {
                    writeResult(generalMap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getContext().system().shutdown();
            }
        }else
            unhandled(message);


    }

    private void writeResult(HashMap<Integer, Integer> map) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("result2.txt"));

        int sum = 0;
        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {

            String result = "id " + pair.getKey() + " amount: " + pair.getValue();
            writer.write(result);
            writer.newLine();
            sum += pair.getValue();
        }

        writer.close();
        System.out.println(sum);
        System.out.println("Done");
    }


    public static Props createMaster() {
        return Props.create(Master.class, new ArraySeq<Object>(0));
    }


}
