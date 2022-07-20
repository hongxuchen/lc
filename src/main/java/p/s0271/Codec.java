package p.s0271;

import java.util.ArrayList;
import java.util.List;

public class Codec {

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            int size = str.length();
            String sizeStr = String.format("%04d", size);
            sb.append(sizeStr);
            sb.append(str);
        }
        return sb.toString();
    }

    public List<String> decode(String s) {
        List<String> strs = new ArrayList<>();

        if (s.isEmpty()) {
            return strs;
        }

        for (int j = 0; j < s.length(); ) {
            String sizeStr = s.substring(j, j + 4);
            int size = Integer.parseInt(sizeStr);
            j += 4;
            String str = s.substring(j, j + size);
            strs.add(str);
            j += size;
        }

        return strs;

    }

    public static void main(String[] args) {
        List<String> strs = List.of("hello~", "world!");
        Codec codec = new Codec();
        String str = codec.encode(strs);
        System.out.println(str);
        List<String> newStrs = codec.decode(str);
        System.out.println(newStrs);
    }

}
