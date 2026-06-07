package problems11_20.implementQueueUsingStack;

import java.util.Stack;
// my stupid try
class MyQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    int n=0;
    public MyQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    public void push(int x) {
        if(stack1.isEmpty() && stack2.isEmpty()){
            stack1.push(x);
            n=1;
        }
        else if(stack2.isEmpty()){
            stack2.push(x);
            n=2;
        }
        else if(n==2){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            stack1.push(x);
            n=1;
        }
        else{
            while(!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
            stack2.push(x);
            n=2;
        }
    }
    public int pop() {
        return stack1.pop();
    }
    public int peek() {
        return stack1.peek();
    }
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
// solution from the answers
class MyQueue2{
    private Stack<Integer>s1;
    private Stack<Integer>s2;

    public MyQueue2(){
        s1=new Stack<>();
        s2=new Stack<>();
    }
    public void push(int x){
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        s1.push(x);
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }
    public int pop(){
        return s1.pop();
    }
    public int peek(){
        return s1.peek();
    }
    public boolean empty(){
        return s1.isEmpty();
    }
}
// another solution from the answers
class MyQueue3 {

    Stack<Integer> input = new Stack();
    Stack<Integer> output = new Stack();

    public void push(int x) {
        input.push(x);
    }

    public void pop() {
        peek();
        output.pop();
    }

    public int peek() {
        if (output.empty())
            while (!input.empty())
                output.push(input.pop());
        return output.peek();
    }

    public boolean empty() {
        return input.empty() && output.empty();
    }
}
// my solution, second try (optimal by the way)
class MyQueue4 {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> reverse = new Stack<>();

    public void push(int x) {
        stack.push(x);
    }
    public int pop() {
        if(reverse.empty()){
            while(!stack.empty())
                reverse.push(stack.pop());
        }
        return reverse.pop();
    }
    public int peek() {
        if(reverse.empty()){
            while(!stack.empty())
                reverse.push(stack.pop());
        }
        return reverse.peek();
    }
    public boolean empty() {
        return stack.empty()&&reverse.empty();
    }
}
// my solution, second try (refactored by me)
class MyQueue5 {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> reverse = new Stack<>();

    public void push(int x) {
        stack.push(x);
    }
    public int pop() {
        makeFIFO();
        return reverse.pop();
    }
    public int peek() {
        makeFIFO();
        return reverse.peek();
    }
    private void makeFIFO(){
        if(reverse.empty())
            while(!stack.empty())
                reverse.push(stack.pop());
    }
    public boolean empty() {
        return stack.empty()&&reverse.empty();
    }
}

class Queue{
    public static void main(String[] args) {
        MyQueue2 myQueue = new MyQueue2();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(5);
        myQueue.push(6);
        myQueue.push(7);
    }
}