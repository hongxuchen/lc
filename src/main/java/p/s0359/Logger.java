package p.s0359;

import java.util.HashMap;
import java.util.Map;

public class Logger {

    int TIME = 10;

    private Map<String, Integer> lastUpdated = new HashMap<>();

    public Logger() {

    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (lastUpdated.containsKey(message)) {
            int last = lastUpdated.get(message);
            boolean shouldUpdate = timestamp >= last + TIME;
            if (shouldUpdate) {
                lastUpdated.put(message, timestamp);
            }
            return shouldUpdate;
        } else {
            lastUpdated.put(message, timestamp);
            return true;
        }
    }

    public static void main(String[] args) {
        Logger logger = new Logger();

        System.out.println(logger.shouldPrintMessage(1, "foo"));  // 返回 true ，下一次 "foo" 可以打印的时间戳是 1 + 10 = 11
        System.out.println(logger.shouldPrintMessage(2, "bar"));  // 返回 true ，下一次 "bar" 可以打印的时间戳是 2 + 10 = 12
        System.out.println(logger.shouldPrintMessage(3, "foo"));  // 3 < 11 ，返回 false
        System.out.println(logger.shouldPrintMessage(8, "bar"));  // 8 < 12 ，返回 false
        System.out.println(logger.shouldPrintMessage(10, "foo")); // 10 < 11 ，返回 false
        System.out.println(logger.shouldPrintMessage(11, "foo")); // 11 >= 11 ，返回 true ，下一次 "foo" 可以打印的时间戳是 11 + 10 = 21

    }

}
