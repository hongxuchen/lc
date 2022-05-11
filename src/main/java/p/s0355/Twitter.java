package p.s0355;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Twitter {

    int COUNT = 10;

    static class Message {
        public Message(int userId, int tweetId) {
            this.userId = userId;
            this.tweetId = tweetId;
        }

        int userId;
        int tweetId;
    }

    private final Map<Integer, Set<Integer>> followInfo = new HashMap<>();
    private final List<Message> messages = new ArrayList<>();

    public Twitter() {
    }

    public void postTweet(int userId, int tweetId) {
        messages.add(new Message(userId, tweetId));
        follow(userId, userId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feeds = new ArrayList<>();
        int count = 0;
        int i = messages.size() - 1;
        Set<Integer> following = followInfo.get(userId);
        if (following == null || following.isEmpty()) {
            return feeds;
        }
        while (count < COUNT && i >= 0) {
            Message cur = messages.get(i);
            if (following.contains(cur.userId)) {
                feeds.add(cur.tweetId);
                count++;
            }
            i--;
        }
        return feeds;
    }

    public void follow(int followerId, int followeeId) {
        if (followInfo.containsKey(followerId)) {
            Set<Integer> following = followInfo.get(followerId);
            following.add(followeeId);
        } else {
            Set<Integer> following = new HashSet<>();
            following.add(followeeId);
            followInfo.put(followerId, following);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if (followInfo.containsKey(followerId)) {
            Set<Integer> following = followInfo.get(followerId);
            following.remove(followeeId);
        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // 用户 1 发送了一条新推文 (用户 id = 1, 推文 id = 5)
        System.out.println(twitter.getNewsFeed(1));  // 用户 1 的获取推文应当返回一个列表，其中包含一个 id 为 5 的推文
        twitter.follow(1, 2);    // 用户 1 关注了用户 2
        twitter.postTweet(2, 6); // 用户 2 发送了一个新推文 (推文 id = 6)
        System.out.println(twitter.getNewsFeed(1));  // 用户 1 的获取推文应当返回一个列表，其中包含两个推文，id 分别为 -> [6, 5] 。推文 id 6 应当在推文 id 5 之前，因为它是在 5 之后发送的
        twitter.unfollow(1, 2);  // 用户 1 取消关注了用户 2
        System.out.println(twitter.getNewsFeed(1));  // 用户 1 获取推文应当返回一个列表，其中包含一个 id 为 5 的推文。因为用户 1 已经不再关注用户 2

    }

}