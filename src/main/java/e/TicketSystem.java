package e;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// http://3ms.huawei.com/km/groups/3803117/blogs/details/10964225

public class TicketSystem {

    static class SeatInfo {
        public SeatInfo(int start, int length, int id) {
            this.start = start;
            this.length = length;
            this.id = id;
        }

        int start;
        int length;
        int id;
    }

    static class BookInfo {
        int id;
        int num;

        public BookInfo(int id, int num) {
            this.id = id;
            this.num = num;
        }
    }

    private int VACON_ID = 0;

    private Map<Integer, List<SeatInfo>> cabinMap = new HashMap<>();
    private Map<Integer, Queue<BookInfo>> queueMap = new HashMap<>();
    private Map<Integer, Integer> book2Cabin = new HashMap<>();

    public TicketSystem(int[] cabins) {
        for (int i = 0; i < cabins.length; i++) {
            SeatInfo seatInfo = new SeatInfo(0, cabins[i], VACON_ID);
            cabinMap.put(i, new ArrayList<>(List.of(seatInfo)));
        }
    }

    public boolean findAndUpdate(int id, int num, List<SeatInfo> seatInfos) {
        boolean continuous = false;
        int voconNum = 0;
        for (int i = 0; i < seatInfos.size(); i++) {
            SeatInfo seatInfo = seatInfos.get(i);
            if (seatInfo.id == VACON_ID && seatInfo.length >= num) {
                seatInfo.length -= num;
                seatInfo.id = id;
                if (seatInfo.length > num) {
                    SeatInfo newSeatInfo = new SeatInfo(seatInfo.start + seatInfo.length, seatInfo.length - num, VACON_ID);
                    seatInfos.add(i + 1, seatInfo);
                }
                return true;
            }
            voconNum += seatInfo.length;
        }
        if (voconNum >= num) {
            int needed = num;
            for (int i = 0; i < seatInfos.size(); i++) {
                SeatInfo seatInfo = seatInfos.get(i);
                if (seatInfo.id == VACON_ID) {
                    if (seatInfo.length >= needed) {
                        if (seatInfo.length > needed) {
                            SeatInfo newSeatInfo = new SeatInfo(seatInfo.start + needed, seatInfo.length - needed, VACON_ID);
                            seatInfos.add(i + 1, newSeatInfo);
                        }
                        seatInfo.id = id;
                        seatInfo.length = needed;
                        return true;
                    } else {
                        // seatInfo.length < needed
                        seatInfo.id = id;
                        needed -= seatInfo.length;
                    }
                }
            }
            return true;
        } else {
            BookInfo bookInfo = new BookInfo(id, num);
            Queue<BookInfo> queue = queueMap.get(id);
            if (queue == null) {
                queue = new ArrayDeque<>();
            }
            queue.offer(bookInfo);
            return false;
        }
    }

    public boolean book(int id, int cabinId, int num) {
        book2Cabin.put(id, cabinId);
        List<SeatInfo> seatInfos = cabinMap.get(cabinId);
        return findAndUpdate(id, num, seatInfos);
    }

    public boolean cancel(int id) {
        int cabinId = book2Cabin.get(id);
        List<SeatInfo> seatInfos = cabinMap.get(cabinId);
        boolean updated = false;
        for (int i = 0; i < seatInfos.size(); i++) {
            SeatInfo seatInfo = seatInfos.get(i);
            if (seatInfo.id == id) {
                if (i < seatInfos.size() - 1 && seatInfos.get(i + 1).id == VACON_ID) {
                    SeatInfo nextSeatInfo = seatInfos.get(i + 1);
                    seatInfo.id = VACON_ID;
                    seatInfo.length = seatInfo.length + nextSeatInfo.length;
                    seatInfos.remove(i + 1);
                } else {
                    seatInfo.id = VACON_ID;
                }
            }
            updated = true;
        }
        if (!updated) {
            return false;
        } else {
            Queue<BookInfo> bookInfos = queueMap.get(id);
            while (!bookInfos.isEmpty()) {
                BookInfo bookInfo = bookInfos.poll();
                boolean changed = findAndUpdate(bookInfo.id, bookInfo.num, seatInfos);
                if (!changed) {
                    break;
                }
            }
            return true;
        }
    }

    public int query(int id) {
        int cabinId = book2Cabin.get(id);
        List<SeatInfo> seatInfos = cabinMap.get(cabinId);
        return seatInfos.stream().filter(s -> s.id == id).findFirst().stream().mapToInt(s -> s.start).findFirst().getAsInt();
    }

}
