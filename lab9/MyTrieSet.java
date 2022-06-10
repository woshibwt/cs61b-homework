import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    private static class Node {
        boolean isKey;
        Map<Character, Node> map;

        Node(boolean isKey) {
            this.isKey = isKey;
            this.map = new HashMap<>();
        }
    }

    public MyTrieSet() {
        root = new Node(true);
    }

    @Override
    public void clear() {
        root = new Node(true);
    }

    @Override
    public boolean contains(String key) {
        if (key == null) {
            throw  new IllegalArgumentException("Input is null");
        }
        Node endNode = endOf(key);
        if (endNode == null) {
            return false;
        }
        return endNode.isKey;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Input is null");
        }

        Node startNode = endOf(prefix);
        List<String> keys = new ArrayList<>();

        if (startNode == null) {
            return keys;
        }

        collectAllKeys(prefix, startNode, keys);
        return keys;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    private Node endOf(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i ++ ) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return null;
            }
            curr = curr.map.get(c);
        }
        return curr;
    }

    private void collectAllKeys(String s, Node node, List<String> keys) {
        if (node.isKey) {
            keys.add(s);
        }

        for (char c : node.map.keySet()) {
            collectAllKeys(s + c, node.map.get(c), keys);
        }
    }
}
