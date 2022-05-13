package p.s0211s;

public class WordDictionary {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private final TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    public boolean dfs(String word, int wordIndex, TrieNode node) {
        if (wordIndex == word.length()) {
            return node.isEnd;
        }
        char ch = word.charAt(wordIndex);
        if (ch == '.') {
            for (int i = 0; i < 26; i++) {
                TrieNode child = node.children[i];
                if (child != null && dfs(word, wordIndex + 1, child)) {
                    return true;
                }
            }
        } else {
            int nodeIndex = word.charAt(wordIndex) - 'a';
            TrieNode child = node.children[nodeIndex];
            return child != null && dfs(word, wordIndex + 1, child);
        }
        return false;
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

}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
