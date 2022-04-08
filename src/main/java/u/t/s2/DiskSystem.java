package u.t.s2;

import java.util.Map;
import java.util.TreeMap;

class DiskSystem {
    // 这里使用一个数组作为磁盘容器，并使用一个int值记录剩下的磁盘容量
    private int[] disk;
    private int lastSize;

    // 初始化，按照给定的大小建立数组，并初始化剩余容量
    public DiskSystem(int capacity) {
        this.disk = new int[capacity];
        this.lastSize = capacity;
    }

    public int add(int fileId, int fileSize) {
        // 看剩余容量是否满足增加文件，若不满足，返回-1。建立剩余容量的好处就在于可以少遍历几次数组
        int lastfile = fileSize;
        if (this.lastSize < fileSize) {
            return -1;
        }
        for (int i = 0; i < this.disk.length; i++) {
            // 遍历数组，为0的空间放置文件，同时减小剩余空间、文件大小，当文件大小为0，返回当前索引。
            if (this.disk[i] == 0) {
                this.disk[i] = fileId;
                lastfile -= 1;
                this.lastSize -= 1;
            }
            if (lastfile == 0) {
                return i;
            }
        }
        return -1;
    }

    public int remove(int fileId) {
        // 先记录当前剩余位置，遍历一边数组，符合给fileId的置0，磁盘剩余容量+1。判断遍历后的磁盘剩余容量是否相等。
        // 若相同，则不存在fileId，返回-1;若不同，返回当前磁盘剩余容量。
        int beforeRemoveSize = this.lastSize;
        for (int i = 0; i < this.disk.length; i++) {
            if (this.disk[i] == fileId) {
                this.disk[i] = 0;
                this.lastSize += 1;
            }
        }
        if (this.lastSize > beforeRemoveSize) {
            return this.lastSize;
        } else {
            return -1;
        }
    }

    public int defrag() {
        // 整理空间，遍历数组，使用treeMap，记录文件个数，treeMap保证了文件按大小顺序排列，数组为0时跳过。
        Map<Integer, Integer> treemap = getFiles();
        int index = 0;
        int files = 0;
        for (Map.Entry<Integer, Integer> treeEntry : treemap.entrySet()) {
            // 遍历treeMap，将值填到数组中。
            files += 1;
            for (int i = 0; i < treeEntry.getValue(); i++) {
                this.disk[index] = treeEntry.getKey();
                index += 1;
            }
        }
        // 剩余数组，置0，有很多测试用例通过但是提交报错的可能就少这一步。
        for (int i = index; i < this.disk.length; i++) {
            this.disk[i] = 0;
        }
        return files;
    }

    public Map<Integer, Integer> getFiles() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : this.disk) {
            if (i == 0) {
                continue;
            }
            if (map.containsKey(i)) {
                int curNum = map.get(i);
                map.put(i, curNum + 1);
            } else {
                map.put(i, 1);
            }
        }
        return map;
    }
}