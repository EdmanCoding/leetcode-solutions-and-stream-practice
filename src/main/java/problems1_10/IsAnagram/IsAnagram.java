package problems1_10.IsAnagram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IsAnagram {
    // my first the slowest assumption
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Character[] word = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            word[i] = s.charAt(i);
        }
        Character[] word2 = new Character[t.length()];
        for (int i = 0; i < t.length(); i++) {
            word2[i] = t.charAt(i);
        }
        Arrays.sort(word);
        Arrays.sort(word2);
        for (int i = 0; i < word.length; i++) {
            if (word[i] != word2[i])
                return false;
        }
        return true;
    }
    // algorithm with HashMap (slow too)
    public static boolean isAnagram2(String s, String t) {
        if(s.length()!=t.length()) return false;
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            if(map.containsKey(s.charAt(i)))
                map.put(s.charAt(i),(map.get(s.charAt(i))+1));
            else map.put(s.charAt(i),1);
        }
        for(int i = 0; i < t.length(); i++){
            map.put(t.charAt(i),(map.getOrDefault(t.charAt(i),0)-1));
            if(map.get(t.charAt(i))<0) return false;
        }
        return true;
    }
    // Fastest O(n) time, O(1) space algorithm
    public static boolean isAnagram3(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }
        for (int count : counts) {
            if (count != 0)
                return false;
        }
        return true;
    }
    // Resolved solution
    public static boolean isAnagram4(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>(s.length());
        for(int i = 0; i< s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                Integer accum = map.get(s.charAt(i));
                map.put(s.charAt(i), accum+1);
            }
            else
                map.put(s.charAt(i), 1);
        }
        HashMap<Character, Integer> map2 = new HashMap<>(s.length());
        for(int i = 0; i< s.length(); i++){
            if(map2.containsKey(t.charAt(i))){
                Integer accum = map2.get(t.charAt(i));
                map2.put(t.charAt(i), accum+1);
            }
            else
                map2.put(t.charAt(i), 1);
        }
        return map.equals(map2);
    }
    //correct HashMap solution ChatGPT
    public static boolean isAnagram5(String s, String t) {
        if (s == null || t == null|| s.isEmpty() || t.isEmpty())
            throw new IllegalArgumentException();
        if (s.length() != t.length())
            return false;
        HashMap<Character, Integer> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            map.put(c1, map.getOrDefault(c1, 0) + 1);
            map.put(c2, map.getOrDefault(c2, 0) - 1);
        }
        for (int count : map.values()) {
            if (count != 0)
                return false;
        }
        return true;
    }
    
    // my HashMap solution from 4th loop + little GPT correction
    public boolean isAnagram6(String s, String t) {
        if (s.length() != t.length())
            return false;
        char[] sch = s.toCharArray();
        char[] tch = t.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : sch)
            map.put(c, map.getOrDefault(c, 0) + 1);
        for (char c : tch) {
            int count = map.getOrDefault(c, 0) - 1;
            if (count < 0)
                return false;
            map.put(c, count);
        }
        return true;
    }

    // ============ Streams ============

    // Плюсы: коротко, декларативно.
    // Минусы: нет досрочного выхода, мапы строятся полностью. Для коротких строк ок, для длинных — оверхед.
    public boolean isAnagram7(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Integer, Long> freqS = s.chars().boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Integer, Long> freqT = t.chars().boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return freqS.equals(freqT);
    }
    // s.chars() возвращает IntStream кодовых точек символов.


    // Тот же allMatch с мутацией, как в Contains Duplicate
    // Минусы: громоздко, побочные эффекты, нечитаемо. Для продакшена я бы не рекомендовал.
    public boolean isAnagram8(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        s.chars().forEach(c -> map.merge((char) c, 1, Integer::sum));
        return t.chars().allMatch(c -> {
            int count = map.getOrDefault((char) c, 0);
            if (count == 0) return false;
            map.put((char) c, count - 1);
            return true;
        });
    }

    public static void main(String[] args) {
        System.out.println(isAnagram3("a", "ab"));
    }
}
