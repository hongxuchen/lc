package last.s1;

import java.util.*;

class UserInfo implements Comparable<UserInfo> {
    int userId;
    int duration;
    int time;

    public UserInfo(int userId, int duration, int time) {
        this.userId = userId;
        this.duration = duration;
        this.time = time;
    }

    @Override
    public int compareTo(UserInfo o) {
        if (this.duration == o.duration) {
            return this.time - o.time;
        } else {
            return this.duration - o.duration;
        }
    }
}

public class SkiRankingSystem {

    private int time = 0;

    PriorityQueue<UserInfo> topUsers = new PriorityQueue<>();
    Map<Integer, PriorityQueue<UserInfo>> topInfoMap = new HashMap<>();

    public SkiRankingSystem() {
    }

    public void populateTopUsers(UserInfo userInfo) {
        topUsers.add(userInfo);
    }

    public void addRecord(int userId, int duration) {
        UserInfo userInfo = new UserInfo(userId, duration, time++);
        populateTopUsers(userInfo);

        PriorityQueue<UserInfo> priorityQueue = topInfoMap.get(userId);
        if (priorityQueue == null) {
            priorityQueue = new PriorityQueue<>();
            topInfoMap.put(userId, priorityQueue);
        }
        priorityQueue.offer(userInfo);

    }

    public int[] getTopAthletes(int num) {
        Set<Integer> res = new LinkedHashSet<>();
        for (UserInfo userInfo : topUsers) {
            res.add(userInfo.userId);
            if (res.size() >= num) {
                break;
            }
        }
        return res.stream().mapToInt(id -> id).toArray();
    }


    public int[] queryTop3Record(int userId) {
        PriorityQueue<UserInfo> curRecord = topInfoMap.get(userId);
        if (curRecord == null) {
            return new int[0];
        } else {
            return curRecord.stream().limit(3).mapToInt(u -> u.duration).toArray();
        }
    }

    public static void main(String[] args) {
        SkiRankingSystem skiRankingSystem = new SkiRankingSystem();
        skiRankingSystem.addRecord(20, 8);
        skiRankingSystem.addRecord(22, 6);
        skiRankingSystem.addRecord(20, 6);
        System.out.println(Arrays.toString(skiRankingSystem.getTopAthletes(4)));
        skiRankingSystem.addRecord(33, 5);
        skiRankingSystem.addRecord(22, 9);
        skiRankingSystem.addRecord(31, 4);
        System.out.println(Arrays.toString(skiRankingSystem.getTopAthletes(4)));
        skiRankingSystem.addRecord(20, 8);
        System.out.println(Arrays.toString(skiRankingSystem.queryTop3Record(20)));
        skiRankingSystem.addRecord(20, 6);
        System.out.println(Arrays.toString(skiRankingSystem.queryTop3Record(20)));
    }
}
