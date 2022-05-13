package d;

import java.util.Map;
import java.util.TreeMap;

public class MapTrie {

    static class TrieNode {
        Map<Character, TrieNode> childMap = new TreeMap<>();
        boolean isEnd = false;
    }

    TrieNode root;

    public MapTrie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.childMap.containsKey(ch)) {
                node.childMap.put(ch, new TrieNode());
            }
            node = node.childMap.get(ch);
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!node.childMap.containsKey(ch)) {
                return false;
            }
            node = node.childMap.get(ch);
        }
        return node.isEnd;
    }

    public boolean startsWith(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length();i ++) {
            char ch = word.charAt(i);
            if(!node.childMap.containsKey(ch)) {
                return false;
            }
            node = node.childMap.get(ch);
        }
        return true;
    }

}
