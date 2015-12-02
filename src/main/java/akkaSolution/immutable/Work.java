package akkaSolution.immutable;

import akka.actor.ActorRef;

import java.util.ArrayList;

/**
 * Created by Dmytro on 29.11.15.
 */
public class Work {
    private final ArrayList<String> list;
    private final ActorRef mapWorker;

    public Work(ArrayList<String> list, ActorRef mapWorker) {
        this.list = list;
        this.mapWorker = mapWorker;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public ActorRef getMapWorker() {
        return mapWorker;
    }
}
