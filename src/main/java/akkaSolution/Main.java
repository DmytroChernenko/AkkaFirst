package akkaSolution;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Created by Dmytro on 29.11.15.
 */
public class Main {
    public static void main(String[] args) {
        new AkkaWay().run(args[0]);
    }



}
