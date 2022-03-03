package e;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestMap {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 5;
            }
        };

        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        map.get(6);
        System.out.println(map.keySet());

    }

}
