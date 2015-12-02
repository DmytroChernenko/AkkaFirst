package akkaSolution;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akkaSolution.immutable.Finish;
import akkaSolution.immutable.Work;
import akkaSolution.workers.MapWorker;
import akkaSolution.workers.StringWorker;
import scala.collection.mutable.ArraySeq;
import akka.routing.RoundRobinRouter;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Dmytro on 29.11.15.
 */
public class Master extends UntypedActor {

    private String filename;
    private ActorRef workerRouter;
    private ActorRef mapWorker;

    public Master() {
        workerRouter = this.getContext().actorOf(StringWorker.createWorker().withRouter(new RoundRobinRouter(8)), "workerRouter");
        mapWorker = this.getContext().actorOf(MapWorker.createMapWorker(), "MapWorker");
    }

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof String) {
            filename = (String) message;
            processFile(filename);
        } else
            unhandled(message);


    }


    private void processFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        int count = 0;
        while (reader.ready()) {
            ArrayList<String> buff = new ArrayList<>();
            for (int i = 0; i < 5000 && reader.ready(); i++) {
                buff.add(reader.readLine());
            }

            workerRouter.tell(new Work(buff, mapWorker), getSelf());
            count++;
        }
        reader.close();
        mapWorker.tell(new Finish(count), getSelf());
    }

    public static Props createMaster() {
        return Props.create(Master.class, new ArraySeq<Object>(0));
    }


}
