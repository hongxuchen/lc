
package u.t.s2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

class FileSystem {

    static class FileInfo {
        int fileId = 0;
        int time = 0;
        Set<Integer> pages = new HashSet<>();
        int num = 0;

        public FileInfo(int fileId, int time, int num) {
            this.fileId = fileId;
            this.time = time;
            this.num = num;
        }
    }

    private Deque<FileInfo> fileInfoStack = new ArrayDeque<FileInfo>();

    int saved;
    int timeout;

    public FileSystem(int timeout) {
        this.timeout = timeout;
        this.saved = 0;
    }

    public boolean request(int time, int fileId, int number) {
        for (FileInfo fileInfo : fileInfoStack) {
            if (fileInfo.fileId == fileId) {
                return false;
            }
        }
        FileInfo fileInfo = new FileInfo(fileId, time, number);
        fileInfoStack.push(fileInfo);
        return true;
    }

    public int receive(int time, int fileId, int partId) {
        for (FileInfo fileInfo : fileInfoStack) {
            if (fileInfo.fileId == fileId) {
                if (time - fileInfo.time > timeout) {
                    return -1;
                }
                if (fileInfo.pages.contains(partId)) {
                    return -1;
                }
                fileInfo.pages.add(partId);
                if (fileInfo.pages.size() == fileInfo.num) {
                    saved++;
                    fileInfoStack.remove(fileInfo);
                }
            }
        }
        return -1;
    }

    public int query(int time) {
        int sum = 0;
        for (FileInfo fileInfo : fileInfoStack) {
            if (time - fileInfo.time > timeout)
                continue;
            sum++;
        }
        return sum;
    }
}
