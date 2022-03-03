
package e;

import java.util.HashMap;
import java.util.Map;

class LogSystem {
    private Map<Integer, Integer> map = new HashMap<>();

    public LogSystem() {
        System.out.println("good");
        System.err.println(map.get("good"));

    }

    public void add(int id, int timeStamp) {
        map.put(id, timeStamp);
    }

    public int delete(int id) {
        if (map.containsKey(id)) {
            map.remove(id);
            return 0;
        } else {
            return -1;
        }
    }

    public int query(int startTime, int endTime) {
        int count = 0;
        for (int time : map.values()) {
            if (time >= startTime && time <= endTime) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LogSystem logSystem = new LogSystem();
        logSystem.add(1, 5);
        logSystem.add(2, 5);
        logSystem.add(3, 6);
        System.out.println(logSystem.query(5, 6));
        System.out.println(logSystem.delete(2));
        System.out.println(logSystem.delete(4));
        System.out.println(logSystem.query(5, 6));
    }

}
