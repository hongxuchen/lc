package p.s0288;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidWordAbbr {

    Map<String, Set<String>> map = new HashMap<>();

    String getAbbr(String str) {
        if (str.length() <= 2) {
            return str;
        } else {
            return str.substring(0, 1) + (str.length() - 2) + str.charAt(str.length() - 1);
        }
    }

    public ValidWordAbbr(String[] dictionary) {
        for (String str : dictionary) {
            String abbr = getAbbr(str);
            if (map.containsKey(abbr)) {
                Set<String> set = map.get(abbr);
                set.add(str);
            } else {
                Set<String> set = new HashSet<>();
                set.add(str);
                map.put(abbr, set);
            }
        }
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        if (map.containsKey(abbr)) {
            Set<String> set = map.get(abbr);
            return set.contains(word) && set.size() == 1;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        String[] dictionary = {"deer", "door", "cake", "card"};
        ValidWordAbbr validWordAbbr = new ValidWordAbbr(dictionary);
        System.out.println(validWordAbbr.getAbbr("internationalization"));
        System.out.println(validWordAbbr.isUnique("dear"));
        System.out.println(validWordAbbr.isUnique("cart"));
        System.out.println(validWordAbbr.isUnique("cane"));
        System.out.println(validWordAbbr.isUnique("make"));
        System.out.println(validWordAbbr.isUnique("cake"));
    }

}
