package p.s0460;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class LFUCache {

    int globalTimeStamp = 0;
    private final int capacity;
    TreeSet<CacheCell> cells = new TreeSet<>();
    Map<Integer, CacheCell> cacheCellMap = new HashMap<>();

    static class CacheCell implements Comparable<CacheCell> {
        int key;
        int count;
        int last;
        int value;

        @Override
        public String toString() {
            return "CacheCell{" +
                    "key=" + key +
                    ", count=" + count +
                    ", last=" + last +
                    ", value=" + value +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheCell cacheCell = (CacheCell) o;
            return key == cacheCell.key;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(key);
        }

        public CacheCell(int key, int value, int last) {
            this.key = key;
            this.value = value;
            this.last = last;
            this.count = 1;
        }

        public void updateInfo(int timeStamp) {
            count++;
            last = timeStamp;
        }

        public void update(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(LFUCache.CacheCell other) {
            return this.count - other.count != 0 ? this.count - other.count : this.last - other.last;
        }
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        CacheCell cacheCell = cacheCellMap.get(key);
        if (cacheCell == null) {
            return -1;
        } else {
            cells.remove(cacheCell);
            cacheCell.updateInfo(++globalTimeStamp);
            cells.add(cacheCell);
            return cacheCell.value;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        CacheCell cacheCell = cacheCellMap.get(key);
        if (cacheCell == null) {
            cacheCell = new CacheCell(key, value, ++globalTimeStamp);
            cacheCellMap.put(key, cacheCell);
            if (cells.size() >= capacity) {
                CacheCell removed = cells.pollFirst();
                cacheCellMap.remove(removed.key);
            }
            cells.add(cacheCell);
        } else {
            cells.remove(cacheCell);
            cacheCell.update(value);
            cacheCell.updateInfo(++globalTimeStamp);
            cells.add(cacheCell);
        }
    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.get(1);      // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        lfu.get(2);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        lfu.get(1);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        lfu.get(4);      // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3
    }

}
