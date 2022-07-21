package p.s0705;

import java.util.ArrayList;
import java.util.List;

public class MyHashSet {

    int SIZE = 1000001;
    List<List<Integer>> buckets;

    public MyHashSet() {
        buckets = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int getBucket(int key) {
        if (key < 0) {
            return (key + Integer.MAX_VALUE / 2 + 1) % SIZE;
        } else {
            return key % SIZE;
        }
    }

    public void add(int key) {
        int i = getBucket(key);
        List<Integer> list = buckets.get(i);
        boolean found = list.contains(key);
        if (!found) {
            list.add(key);
        }
    }

    public void remove(int key) {
        int i = getBucket(key);
        List<Integer> list = buckets.get(i);
        list.removeIf(ele -> ele == key);
    }

    public boolean contains(int key) {
        int i = getBucket(key);
        List<Integer> list = buckets.get(i);
        return list.contains(key);
    }

    public static void main(String[] args) {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        System.out.println(hashSet.contains(1));
        System.out.println(hashSet.contains(3));
        hashSet.add(2);
        System.out.println(hashSet.contains(2));
        hashSet.remove(2);
        System.out.println(hashSet.contains(2));
    }

}
