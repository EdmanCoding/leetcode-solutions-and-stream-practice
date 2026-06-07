package problems21_30.LRUcache;

import java.util.HashMap;

// LeetCode solution + Qwen optimization
// Time: get() → O(1), put() → O(1)
// Space: O(capacity)
class LRUCache {
    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    int cap;
    HashMap<Integer, Node> m = new HashMap<>();

    public LRUCache(int capacity) {
        cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void addNode(Node newnode) {
        Node temp = head.next;

        newnode.next = temp;
        newnode.prev = head;

        head.next = newnode;
        temp.prev = newnode;
    }

    private void deleteNode(Node delnode) {
        Node prevv = delnode.prev;
        Node nextt = delnode.next;

        prevv.next = nextt;
        nextt.prev = prevv;
    }

    public int get(int key) {
        if (m.containsKey(key)) {
            Node resNode = m.get(key);
            int ans = resNode.val;
            // Just move the node in the list — NO HashMap ops needed!
            deleteNode(resNode);
            addNode(resNode);
            return ans;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (m.containsKey(key)) {
            // Update existing node's value and move to front
            Node curr = m.get(key);
            curr.val = value;  // ← Critical: update the value!

            deleteNode(curr);
            addNode(curr);
            // NO HashMap remove/put — same node object, reference still valid
            return;
        }
        // New key — check capacity before adding
        if (m.size() == cap) {
            m.remove(tail.prev.key);  // ← Must remove when evicting!
            deleteNode(tail.prev);
        }
        // Add new node
        addNode(new Node(key, value));
        m.put(key, head.next);  // ← Only put for genuinely new keys
    }
}

// optimized second version after failed attempt to resolve.
class LRUCache2 {
    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    int cap;
    HashMap<Integer, Node> m = new HashMap<>();

    public LRUCache2(int capacity) {
        cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void addNode(Node newnode) {
        newnode.next = head.next;
        newnode.prev = head;
        head.next.prev = newnode;
        head.next = newnode;
    }

    private void deleteNode(Node delnode) {
        delnode.prev.next = delnode.next;
        delnode.next.prev = delnode.prev;
    }

    public int get(int key) {
        Node resNode = m.get(key);
        if (resNode == null)
            return -1;
        int ans = resNode.val;
        // Just move the node in the list — NO HashMap ops needed!
        deleteNode(resNode);
        addNode(resNode);
        return ans;
    }

    public void put(int key, int value) {
        Node curr = m.get(key);
        if (curr == null) {
            // New key — check capacity before adding
            if (m.size() == cap) {
                m.remove(tail.prev.key);  // ← Must remove when evicting!
                deleteNode(tail.prev);
            }
            // Add new node
            addNode(new Node(key, value));
            m.put(key, head.next);  // ← Only put for genuinely new keys
            return;
        }
        // Update existing node's value and move to front
        curr.val = value;  // ← Critical: update the value!
        deleteNode(curr);
        addNode(curr);
        // NO HashMap remove/put — same node object, reference still valid
    }
}

// my solution from the 3rd attempt
class LRUCache3 {
    int cap;

    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    private HashMap<Integer, Node> map = new HashMap<>();

    void removeNode(Node remNode) {
        remNode.prev.next = remNode.next;
        remNode.next.prev = remNode.prev;
    }

    void addNode(Node addN) {
        head.next.prev = addN;
        addN.next = head.next;
        addN.prev = head;
        head.next = addN;
    }

    public LRUCache3(int capacity) {
        this.cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            removeNode(map.get(key));
            addNode(map.get(key));
            return map.get(key).val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            removeNode(map.get(key));
            addNode(map.get(key));
            map.get(key).val = value;
        } else if (map.size() == cap) {
            map.put(key, new Node(key, value));
            addNode(map.get(key));
            map.remove(tail.prev.key);
            removeNode(tail.prev);
        } else {
            map.put(key, new Node(key, value));
            addNode(map.get(key));
        }
    }
}
// 4th attempt, with debugger
class LRUCache4 {
    class Node {
        private int key;
        private int value;
        private Node next;
        private Node previous;

        Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    private int cap;
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);
    private HashMap<Integer, Node> map = new HashMap<>();

    void addNode(Node add) {
        add.next = head.next;
        head.next.previous = add;
        head.next = add;
        add.previous = head;
    }

    void removeNode(Node remove) {
        remove.previous.next = remove.next;
        remove.next.previous = remove.previous;
    }

    void toHead(Node toHeadNode) {
        removeNode(toHeadNode);
        addNode(toHeadNode);
    }

    public LRUCache4(int capacity) {
        this.cap = capacity;
        head.next = tail;
        tail.previous = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            toHead(node);
            return node.value;
        } else
            return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)){
            Node update = map.get(key);
            update.value = value;
            toHead(update);
        }
        else if (map.size() == cap) {
            map.remove(tail.previous.key);
            removeNode(tail.previous);
            Node nodeToAdd = new Node(key, value);
            map.put(key, nodeToAdd);
            addNode(nodeToAdd);
        } else {
            Node nodeToAdd = new Node(key, value);
            map.put(key, nodeToAdd);
            addNode(nodeToAdd);
        }
    }

    public static void main(String[] args) {
        LRUCache4 cache = new LRUCache4(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        System.out.println(cache.get(2));
    }
}

