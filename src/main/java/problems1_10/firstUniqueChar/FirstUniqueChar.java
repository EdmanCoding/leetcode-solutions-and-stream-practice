package problems1_10.firstUniqueChar;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import java.util.stream.IntStream;

public class FirstUniqueChar {
    // up to O(n²) operations, my initial solution
    public static int firstUniqChar(String s) {
        int[] array = new int[26];
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++){
            int count = 1;
            array[str[i] - 'a']++;
            if (array[str[i] - 'a'] > 1) continue;
            for (int j = i+1; j < str.length; j++){
                if (str[i]==str[j])
                    count++;
            }
            if (count == 1)
                return i;
        }
        return -1;
    }
    // O(n + n) = O(n), have solved with hint
    public static int firstUniqChar2(String s) {
        if (s == null || s.isEmpty()) return -1;
        int[] array = new int[26];
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++){
            array[str[i]-'a']++;
        }
        for(int i = 0; i < str.length; i++){
            if(array[str[i]-'a']==1)
                return i;
        }
        return -1;
    }
    // my solution with HashMap after failed resolving attempt
    public static int firstUniqChar3(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] str = s.toCharArray();
        for(int i =0; i<str.length; i++){
            map.put(str[i], map.getOrDefault(str[i],0)+1);
        }
        for(int i = 0; i<str.length; i++){
            if(map.get(str[i])==1)
                return i;
        }
        return -1;
    }
    // impressive tricky solution by chatGPT with unusual pattern (read md for invariant)
    public int firstUniqChar4(String s) {
        int[] freq = new int[26];
        Queue<Integer> queue = new LinkedList<>();
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            int idx = charArray[i] - 'a';
            freq[idx]++;

            queue.offer(i);

            while (!queue.isEmpty() && freq[charArray[queue.peek()] - 'a'] > 1) {
                queue.poll();
            }
        }
        return queue.isEmpty() ? -1 : queue.peek();
    }

    // stream solution
    public static int firstUniqChar5(String s) {
        Map<Character, Long> freq = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        return freq.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> s.indexOf(e.getKey()))
                .findFirst()
                .orElse(-1);
    }

    // stream solution
    public int firstUniqChar6(String s) {
        int[] freq = new int[26];
        s.chars().forEach(c -> freq[c - 'a']++);
        return IntStream.range(0, s.length())
                .filter(i -> freq[s.charAt(i) - 'a'] == 1)
                .findFirst()
                .orElse(-1);
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar5("leetcode"));
        System.out.println(firstUniqChar2("loveleetcode"));
        System.out.println(firstUniqChar2("aabb"));
    }
}
