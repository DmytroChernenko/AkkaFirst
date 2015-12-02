package akkaSolution.workers;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akkaSolution.immutable.MapWork;
import akkaSolution.immutable.Work;
import scala.collection.mutable.ArraySeq;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dmytro on 29.11.15.
 */
public class StringWorker extends UntypedActor {
    private ActorRef mapWorker;

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Work) {

            mapWorker = ((Work) message).getMapWorker();

            HashMap<Integer, Integer> map = new HashMap<>();
            ArrayList<String> list = ((Work)message).getList();
            int sum = 0;
            for (String string : list) {
                String[] params = string.split(";");
                int id = Integer.parseInt(params[0]);
                int amt = Integer.parseInt(params[1]);
                int prevAmt = 0;
                if(map.containsKey(id))
                    prevAmt = map.get(id);
                map.put(id, prevAmt + amt);
                sum += amt;
            }

            mapWorker.tell(new MapWork(map), getSelf());
        }else
            unhandled(message);

    }

    public static Props createWorker() {
        return Props.create(StringWorker.class, new ArraySeq<Object>(0));
    }
}
