package problems1_10.ValidParentheses_Easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
    String input1 = "()";
    String input2 = "()[]{}";
    String input3 = "(]";
    String input4 = "({})";
    String input5 = "([)]";
        System.out.println(isValid(input1));
        System.out.println(isValid(input2));
        System.out.println(isValid(input3));
        System.out.println(isValid(input4));
        System.out.println(isValid(input5));
    }
    public static boolean isValid(String s){
        if(s.length() % 2 != 0) return false;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (c == ')' && top != '(') {
                    return false;
                }
                if (c == '}' && top != '{') {
                    return false;
                }
                if (c == ']' && top != '[') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    // solution with hints from LeetCode, the second time
    public boolean isValid2(String s) {
        Deque<Character> stack = new ArrayDeque<Character>();
        for(int i =0; i<s.length(); i++){
            if(s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{')
                stack.push(s.charAt(i));
            else if(stack.isEmpty())
                return false;
            else if(s.charAt(i)==')'&&stack.peek()!='(' ||
                    s.charAt(i)==']'&&stack.peek()!='[' ||
                    s.charAt(i)=='}'&&stack.peek()!='{' )
                return false;
            else
                stack.pop();
        }
        return stack.isEmpty();
    }
    // most elegant solution from LeerCode. O(n) time and space
    public static boolean isValid3(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}
