package e;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListRemove {

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        for (String str: list) {
            if (str.equals("c")) {
                list.remove(str);
                System.out.println(str);
            }
        }
    }

}
