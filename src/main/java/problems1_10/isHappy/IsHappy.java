package problems1_10.isHappy;

import java.util.HashSet;
import java.util.Set;

public class IsHappy {
    // my initial solution with Set (Visited-state detection).
    // O(log n) time complexity (and all solutions below)
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while(true){
            int sum = 0;
            int reminder= 0;
            while(n>0){
                reminder = n%10;
                n/=10;
                sum += reminder*reminder;
            }
            if (sum == 1)
                return true;
            else if (!set.add(sum))
                return false;
            else n=sum;
        }
    }
    // from LeetCode solutions without Set (Floyd’s Tortoise and Hare)
    public static boolean isHappy2(int n) {
        int slow = getNextNumber(n);
        int fast = getNextNumber(getNextNumber(n));

        while (slow != fast) {
            if (fast == 1) return true;
            slow = getNextNumber(slow);
            fast = getNextNumber(getNextNumber(fast));
        }
        return slow == 1;
    }
    private static int getNextNumber(int n) {
        int output = 0;
        while (n > 0) {
            int digit = n % 10;
            output += digit * digit;
            n = n / 10;
        }
        return output;
    }
    // Fastest solution from LeetCode.
    // Fact (well-known and proven for base-10):
    // - happy numbers → eventually reach 1;
    // - unhappy numbers → eventually reach 4.
    // So it skips general cycle detection and checks only the known sink.
    // In graph theory and computer science, a sink refers to:
    // A node with no outgoing edges (out-degree = 0).
    // It's a terminal point where a path ends; you can go into it but not out of it.
    static boolean isHappy3(int n) {
        while(n != 1 && n != 4) {
            int sum = 0;
            while(n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }
            n = sum;
        }
        return n == 1;
    }
    
    // second attempt by myself
    public static boolean isHappy4(int n) {
        HashSet<Integer> set = new HashSet<>();
        boolean loop = true;
        while (loop) {
            String number = String.valueOf(n);
            char[] charArray = number.toCharArray();
            n = 0;
            for (char charN : charArray) {
                int num = charN - '0';
                n += num * num;
            }
            if (n == 1)
                return true;
            loop = set.add(n);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isHappy4(19));
    }
}
