package e;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAutomic {

    public static void main(String[] args) {
        AtomicInteger auto = new AtomicInteger(1);

        System.out.println(auto.getAndIncrement());// 1

        System.out.println(auto.get());// 2

        System.out.println(auto.incrementAndGet());// 3
    }

}
