package stream_practice;

import java.util.List;
import java.util.*;

public class ComparatorExercise {
    public static void main(String[] args) {

        List<String> words = List.of("apple", "banana", "cat", "abc", "banan", "car", "appla");
        List<String> sorted = words.stream().sorted(
                Comparator.comparingInt(String::length).thenComparing(Comparator.reverseOrder())
        ).toList();
        System.out.println(sorted);

        List<String> words2 = List.of("banana", "apple", "orange", "kiwi", "grape");
        String longest = words2.stream().max(Comparator.comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder())).get();
        System.out.println(longest);

        Map<String, Long> map = Map.of("apple", 3L, "banana", 5L, "kiwi", 3L, "orange", 5L);
        Map.Entry<String, Long> maximum = map.entrySet().stream().max(Comparator
                        .comparing(Map.Entry<String, Long>::getValue)
                        .thenComparing(Map.Entry.<String, Long>comparingByKey().reversed())).get(); // reverse key order).get();
        System.out.println(maximum);


        List<String> words3 = List.of("banana", "apple", "kiwi", "grape", "fig", "cherry");
        List<String> sorted2 = words3.stream().sorted(
                Comparator.comparingInt(String::length).thenComparing(Comparator.reverseOrder())
        ).toList();
        System.out.println(sorted2);

        List<String> words4 = List.of("banana", "cherry", "apple", "orange", "kiwi");
        String maximum2 = words4.stream().max(
                Comparator.comparingInt(String::length).thenComparing(Comparator.reverseOrder())
        ).orElse("");
        System.out.println(maximum2);

        List<String> words5 = List.of("kiwi", "fig", "apple", "cat", "dog", "ant");
        String minimum = words5.stream().min(
                Comparator.comparingInt(String::length).thenComparing(Comparator.reverseOrder())
        ).orElse("");
        System.out.println(minimum);
    }
}
