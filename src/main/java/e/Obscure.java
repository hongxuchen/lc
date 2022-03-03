package e;

public class Obscure {

    public static int test() {
        try {
            throw new Exception("error");
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            return 0;
        }
    }


    public static void main(String[] args) {
        int i = test();
        System.out.println(i);
    }

}
