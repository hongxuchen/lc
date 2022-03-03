package e;

import java.util.ArrayList;
import java.util.List;

public class TestWC {

    static class Fruit {
    }

    static class Apple extends Fruit {
    }

    public static void main(String[] args) {

        List<? super Fruit> list = new ArrayList<>();

        list.add(new Apple());

    }

}
