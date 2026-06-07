package stream_practice;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingWithDownstream {
    public static void main(String[] args) {
        List<String> words = List.of("apple", "ant", "banana", "bat", "cat", "carrot", "dog");
        List<String> words2 = List.of("apple", "actor", "black", "beast", "custom", "carrot", "dog");
        Map<Character, String> result = words.stream()
                .collect(Collectors.groupingBy(
                        (str-> str.charAt(0)),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(String::length)),
                                optional -> optional.orElse("")
                        )));
        Map<Character, String> result2 = words.stream()
                .collect(Collectors.groupingBy(
                        (str-> str.charAt(0)),
                        Collectors.collectingAndThen(
                                Collectors.minBy((s1, s2) -> s1.length() - s2.length()),
                                optional -> optional.orElse("")
                        )));
        Map<Character, String> result3 = words.stream()
                .collect(Collectors.toMap(
                        s -> s.charAt(0),   // keyMapper
                        s -> s,             // valueMapper
                        (s1, s2) -> s1.length() >= s2.length() ? s1 : s2   // mergeFunction
                ));
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
    }
}
