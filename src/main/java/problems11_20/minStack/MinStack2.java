package problems11_20.minStack;

import java.util.Stack;

// by myself, second attempt
class MinStack {
    class Node{
        private int value;
        private int min;
        private Node next;
        private Node previous;
        public Node(int value, int min){
            this.value = value;
            this.min = min;
        }
        public Node(){}
    }
    private Node node;

    public MinStack() {
        node = new Node();
        node.previous = null;
        node.next = null;
    }
    public void push(int val) {
        if(node.previous==null){
            Node temp = node;
            node = new Node(val, val);
            node.previous = temp;
            temp.next = node;
        }
        else if(node.min > val){
            Node temp = node;
            node = new Node(val, val);
            node.previous = temp;
            temp.next = node;
        }
        else{
            Node temp = node;
            node = new Node(val, temp.min);
            node.previous = temp;
            temp.next = node;
        }
    }
    public void pop() {
        Node temp = node;
        node = temp.previous;
        temp.previous = null;
        node.next = null;
    }
    public int top() {
        return node.value;
    }
    public int getMin() {
        return node.min;
    }
}
// optimized solution by GPT using usual LinkedList instead of double
class MinStack2 {
    class Node{
        private int value;
        private int min;
        private Node previous;
        public Node(int value, int min){
            this.value = value;
            this.min = min;
        }
        public Node(){}
    }
    private Node node;

    public MinStack2() {
    }
    public void push(int val) {
        if(node==null)
            node = new Node(val, val);
        else{
            Node temp = node;
            node = new Node(val, Math.min(val, temp.min));
            node.previous = temp;
        }
    }
    public void pop() {
        node = node.previous;
    }
    public int top() {
        return node.value;
    }
    public int getMin() {
        return node.min;
    }
}
// Two stacks solution by chatGPT
class MinStack3 {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }
    public void pop() {
        int removed = stack.pop();
        if (removed == minStack.peek()) {
            minStack.pop();
        }
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return minStack.peek();
    }
}
