package akkaSolution;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import scala.concurrent.duration.Duration;

/**
 * Created by Dmytro on 29.11.15.
 */
public class Main {
    public static void main(String[] args) {
        new AkkaSolution().run(args[0]);
    }
}
