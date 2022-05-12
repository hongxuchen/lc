package p.s0211;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDictionary {

  Map<Integer, List<String>> words = new HashMap<>();

  public WordDictionary() {

  }

  public static void main(String[] args) {
    WordDictionary wordDictionary = new WordDictionary();
    wordDictionary.addWord("bad");
    wordDictionary.addWord("dad");
    wordDictionary.addWord("mad");
    System.out.println(wordDictionary.search("pad")); // 返回 False
    System.out.println(wordDictionary.search("bad")); // 返回 True
    System.out.println(wordDictionary.search(".ad")); // 返回 True
    System.out.println(wordDictionary.search("b..")); // 返回 True

  }

  public void addWord(String word) {
    int length = word.length();
    if (!words.containsKey(length)) {
      List<String> ss = new ArrayList<>();
      ss.add(word);
      words.put(length, ss);
    } else {
      List<String> ss = words.get(length);
      ss.add(word);
    }
  }

  public boolean search(String word) {
    int length = word.length();
    if (!words.containsKey(length)) {
      return false;
    }
    int i;
    List<String> w = words.get(length);
    for (String cur : w) {
      for (i = 0; i < length; i++) {
        if (word.charAt(i) != cur.charAt(i) && word.charAt(i) != '.') {
          break;
        }
      }
      if (i == length) {
        return true;
      }
    }
    return false;
  }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */