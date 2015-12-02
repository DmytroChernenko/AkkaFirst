package akkaSolution.immutable;


import java.util.Map;

/**
 * Created by Dmytro on 01.12.15.
 */
public class MapWork {
    private final Map<Integer, Integer> map;

    public MapWork(Map<Integer, Integer> map) {
        this.map = map;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }
}
