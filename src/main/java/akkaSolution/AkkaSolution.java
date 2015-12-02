package akkaSolution;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Created by Dmytro on 29.11.15.
 */
public class AkkaSolution {
    public void run(String filename) {
        ActorSystem system = ActorSystem.create("FileReader");
        ActorRef master = system.actorOf(Master.createMaster(), "master");
        master.tell(filename, ActorRef.noSender());
    }
}
