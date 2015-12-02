package akkaSolution.workers;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akkaSolution.immutable.Finish;
import akkaSolution.immutable.MapWork;
import scala.collection.mutable.ArraySeq;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro on 01.12.15.
 */
public class MapWorker extends UntypedActor {

    private Map<Integer, Integer> generalMap = new HashMap<>();
    private int recievedMesagesMap = 0;
    private int needToProcess;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof MapWork) {
            recievedMesagesMap++;
            Map map = (Map) ((MapWork) message).getMap();
            processMap(map);
            if (needToProcess == recievedMesagesMap) {
                writeResult(generalMap);
                getContext().system().shutdown();
            }
        } else if (message instanceof Finish) {
            needToProcess = ((Finish) message).getCount();
        }
        unhandled(message);

    }

    private void processMap(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            int prev = 0;
            if (generalMap.containsKey(pair.getKey()))
                prev = generalMap.get(pair.getKey());

            generalMap.put(pair.getKey(), prev + pair.getValue());
        }

    }

    private void writeResult(Map<Integer, Integer> map) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("result2.txt"));

        int sum = 0;
        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {

            String result = "id " + pair.getKey() + " amount: " + pair.getValue();
            writer.write(result);
            writer.newLine();
            sum += pair.getValue();
        }

        writer.close();
    }


    public static Props createMapWorker() {
        return Props.create(MapWorker.class, new ArraySeq<Object>(0));
    }
}
