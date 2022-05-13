package e;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class FileSystem {

    static class FileInfo {
        int fileId;
        int time;
        TreeSet<Integer> remainingParts;

        public FileInfo(int fileId, int time, int num) {
            this.fileId = fileId;
            this.time = time;
            this.remainingParts = new TreeSet<>();
            for (int i = 0; i < num; i++) {
                this.remainingParts.add(i);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FileInfo fileInfo = (FileInfo) o;
            return fileId == fileInfo.fileId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(fileId);
        }
    }

    static class MergeInfo {
        int time;
        int merged;

        public MergeInfo(int time, int merged) {
            this.time = time;
            this.merged = merged;
        }
    }

    int timeout;
    Queue<FileInfo> queue = new ArrayDeque<>();
    Set<Integer> inQueueIds = new HashSet<>();
    List<MergeInfo> merged = new ArrayList<>();

    public FileSystem(int timeout) {
        this.timeout = timeout;
        merged.add(new MergeInfo(-1, 0));
    }

    public boolean request(int time, int fileId, int number) {
        if (inQueueIds.contains(fileId)) {
            return false;
        } else {
            FileInfo fileInfo = new FileInfo(fileId, time, number);
            queue.offer(fileInfo);
            return true;
        }
    }

    public int receive(int time, int fileId, int partId) {
        if (!inQueueIds.contains(fileId)) {
            return -1;
        }
        boolean updated = false;
        int remaining = -1;
        for (FileInfo fileInfo : queue) {
            if (fileInfo.fileId == fileId) {
                if (time > this.timeout + fileInfo.time) {
                    return -1;
                }
                if (!fileInfo.remainingParts.contains(partId)) {
                    return -1;
                }
                fileInfo.remainingParts.remove(partId);
                remaining = fileInfo.remainingParts.size();
            }
        }
        int currentMerged = 0;
        while (!queue.isEmpty()) {
            FileInfo fileInfo = queue.peek();
            if (fileInfo.remainingParts.isEmpty()) {
                queue.poll();
                currentMerged++;
            }
        }
        MergeInfo mergeInfo = new MergeInfo(time, merged.get(merged.size() - 1).merged + currentMerged);
        merged.add(mergeInfo);
        return remaining;
    }

    public int query(int time) {
        for (MergeInfo mergeInfo : merged) {
            if (mergeInfo.time <= time) {
                return mergeInfo.merged;
            }
        }
        return -1;
    }
}
