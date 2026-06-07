package stream_practice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FlatMap {
    public static void main(String[] args) {
        List<String> lines = List.of("a,b,c", "d,e", "f");
        List<String> result = lines.stream().flatMap(s -> Arrays.stream(s.split(",")))
                .toList();

        List<String> words = List.of("apple", "banana", "cat");
        List<Character> chars = words.stream().flatMap(s -> Arrays.stream(s.split("")))
                .map(s -> s.charAt(0)).toList();
        List<Character> chars2 = words.stream().flatMapToInt(String::chars)
                .mapToObj(c -> (char) c).toList();
        System.out.println(chars2);

        List<List<Integer>> listOfLists = List.of(List.of(1, 2), List.of(3, 4, 5));
        List<Integer> list = listOfLists.stream().flatMap(l -> l.stream()).toList();
        System.out.println(list);

        List<String> names = List.of("Alice", "Bob", "Charlie", "Anna", "Alex");
        Map<Character, Long> map = names.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0),
                        Collectors.counting()));
        Map<Character, Long> map2 = names.stream().collect(Collectors.toMap(
                s -> s.charAt(0),
                s -> 1L,
                Long::sum
        ));
        System.out.println(map2);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<String, Long> map3 = numbers.stream()
                .collect(Collectors.groupingBy(
                        n -> n % 2 == 0 ? "Even" : "Odd",
                        Collectors.counting()
                ));
        Map<String, Long> map4 = numbers.stream()
                .collect(Collectors.toMap(
                        n -> n % 2 == 0 ? "Even" : "Odd",
                        n -> 1L,
                        Long::sum
                ));
        System.out.println(map4);

        List<String> words2 = List.of("Java", "stream", "Silo", "API", "is", "powerful");
        Map<Integer, Long> map5 = words2.stream()
                .collect(Collectors.groupingBy(
                        s -> s.length(),
                        Collectors.counting()
                ));
        Map<Integer, Long> map6 = words2.stream()
                .collect(Collectors.toMap(
                        String::length,
                        s -> 1L,
                        Long::sum
                ));
        System.out.println(map6);

        Map<Character, Long> nameCounts = Map.of('A', 1L, 'B', 3L, 'C', 2L);
        List<Character> counts = nameCounts.entrySet().stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(counts);

        Map<String, Integer> sales = Map.of("Laptop", 150, "Mouse", 500, "Keyboard", 300);
        List<String> salesList = sales.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue())
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(salesList);

        Map<String, Double> gpas = Map.of("Alice", 3.8, "Bob", 2.9, "Charlie", 3.6, "Diana", 4.0);
        List<String> names2 = gpas.entrySet().stream()
                .filter(m -> m.getValue() >= 3.5)
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(names2);

        List<String> sentences = List.of(
                "Hello world and hello Java",
                "The world of Java is vast",
                "Hello to the world of streams"
        );
        List<String> result2 = sentences.stream().flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(s -> s.length() > 3)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(result2);

        List<String> emails = List.of(
                "alice@gmail.com",
                "bob@yahoo.com",
                "charlie@gmail.com",
                "diana@outlook.com",
                "eve@yahoo.com",
                "frank@aol.com",
                "grace@gmail.com",
                "henry@ms.com",      // domain "ms" length 2 → excluded
                "ivy@yahoo.com"
        );
        List<String> result3 = emails.stream()
                .map(s -> s.substring(s.indexOf('@') + 1))
                .map(String::toLowerCase)
                .filter(domain -> domain.contains("."))
                .filter(s -> s.substring(0, s.indexOf('.')).length() >= 3)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(result3);

        List<String> fileNames = List.of(
                "report.pdf",
                "image.jpg",
                "notes.txt",
                "archive.tar.gz",
                "script",
                ".gitignore"
        );
        List<String> result4 = fileNames.stream()
                .map(s -> {
                    String[] parts = s.split("\\.");
                    int index = parts.length - 1;
                    return index > 0 ? parts[index].toLowerCase() : "";
                })
                .filter(s -> !s.isEmpty())
                .toList();
        List<String> result5 = fileNames.stream()
                .map(s -> {
                    int index = s.lastIndexOf('.');
                    return index >= 0 ? s.substring(index+1) : "";
                })
                .filter(s -> !s.isEmpty())
                .toList();
        List<String> result6 = fileNames.stream()
                        .map(s-> Optional.of(s.lastIndexOf('.'))
                                .filter(i->i>=0)
                                .map(i->s.substring(i+1))
                                .orElse("")
                        )
                                .filter(s->!s.isEmpty())
                                        .toList();
        System.out.println(result6);

        List<String> configs = List.of(
                "host=localhost",
                "port=8080",
                "debug=true",
                "timeout=30s",
                "invalid_entry",
                "no_value="
        );
        Map<String, String> result7 = configs.stream()
                .map(s-> s.split("=",2))
                .filter(array-> array.length>=2)
                .filter(array-> !array[1].isEmpty())
                .collect(Collectors.toMap(
                    array->array[0],
                        array->array[1],
                        (v1, v2) -> v2,
                        LinkedHashMap::new
                ));
        System.out.println(result7);

        List<String> tweets = List.of(
                "Loving the new ###Java features! #streams",
                "Just finished a 5k run. #fitness #health",
                "No hashtags here",
                "Multiple #tags in #one #tweet"
        );
        List<String> tags = tweets.stream().flatMap(s->Arrays.stream(s.split(" ")))
                .filter(s->s.contains("#"))
                .map(s->s.substring(1))
                .map(String::toLowerCase)
                .distinct()
                .sorted(Comparator.comparingInt(String::length))
                .toList();
        List<String> tags2 = tweets.stream()
                .flatMap(s -> Arrays.stream(s.split("\\s+"))) // split on whitespace
                .filter(word -> word.startsWith("#") && word.length() > 1)
                .map(word -> word.substring(1).toLowerCase())
                .map(word -> word.replaceAll("[^a-z0-9]", "")) // optional: remove trailing punctuation
                .filter(word -> !word.isEmpty())
                .distinct()
                .toList();
        System.out.println(tags2);


    }
}
