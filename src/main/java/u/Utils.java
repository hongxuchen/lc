package u;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String[] asStrArray(String arrString) {
        String trimmed = arrString.trim();
        String nobrackets = trimmed.substring(1, trimmed.length() - 1);
        return nobrackets.split(",\\s+");
    }

    public static int[] asIntArray(String arrString) {
        String[] ss = asStrArray(arrString);
        return Arrays.stream(ss).mapToInt(Integer::valueOf).toArray();
    }

    public static List<String> asStringList(String arrString) {
        String[] ss = asStrArray(arrString);
        return Arrays.stream(ss).collect(Collectors.toList());
    }

    public static List<Integer> asIntegerList(String arrString) {
        String[] ss = asStrArray(arrString);
        return Arrays.stream(ss).map(Integer::valueOf).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String ss = "[1, 2,  3] ";
        {
            int[] ssInts = asIntArray(ss);
            System.out.println(Arrays.toString(ssInts));
            List<Integer> ssIntList = asIntegerList(ss);
            System.out.println(ssIntList);
        }
        {
            String[] ssStrs = asStrArray(ss);
            System.out.println(Arrays.toString(ssStrs));
            List<String> ssStrList = asStringList(ss);
            System.out.println(ssStrList);
        }
    }

}
