package e;

import java.util.HashMap;
import java.util.Map;

class Key {

    private int fst;
    private int scn;

    public Key(int fst, int scn) {
        this.fst = fst;
        this.scn = scn;
    }

    public void setFst(int fst) {
        this.fst = fst;
    }

    public void setScn(int scn) {
        this.scn = scn;
    }

    @Override
    public String toString() {
        return "Key{" +
                "fst=" + fst +
                ", scn=" + scn +
                '}';
    }
}

public class TestStr<T> {


    public static void test() {
        Map<Key, String> aa = new HashMap<Key, String>();

        Key c = new Key(30, 10);

        System.out.println(c);

        aa.put(c, "Robin");

        System.out.println(aa.get(c));

        c.setFst(30);

        System.out.println(aa.get(c));

        c.setFst(10);

        System.out.println(aa.get(c));

        for (Map.Entry<Key, String> entry: aa.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }


    public static void main(String[] args) {
        test();
    }

}
