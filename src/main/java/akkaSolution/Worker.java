package akkaSolution;

import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.collection.mutable.ArraySeq;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dmytro on 29.11.15.
 */
public class Worker extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Work) {
            HashMap<Integer, Integer> map = new HashMap<>();
            ArrayList<String> list = ((Work)message).getList();
            int sum = 0;
            System.out.println(list.size());
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

            getSender().tell(new Result(map), getSelf());
        }else
            unhandled(message);

    }

    public static Props createWorker() {
        return Props.create(Worker.class, new ArraySeq<Object>(0));
    }
}
