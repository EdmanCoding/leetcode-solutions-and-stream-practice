package stream_practice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;


public class Refresher {
    public static void main(String[] args) {

        List<String> products = List.of(
                "Laptop:Electronics",
                "T-shirt:Clothing",
                "Phone:Electronics",
                "Jeans:Clothing",
                "Headphones:Electronics",
                "Socks:Clothing",
                "Tablet:Electronics"
        );
        List<String> category = products.stream()
                .map(s -> s.substring(s.lastIndexOf(':') + 1))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.groupingBy(
                        s -> s,
                        counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(category);


        List<String> posts = List.of(
                "I love #java and #python!",
                "#Java is great for #backend",
                "Learning #python #data #science",
                "No hashtags here",
                "#AI #ML #python"
        );
        Map<String, Long> tags = posts.stream().flatMap(s -> Arrays.stream(s.split("\\s+")))
                .filter(s -> s.charAt(0) == '#')
                .map(s -> s.substring(1))
                .filter(s -> s.length() > 3)
                .map(String::toLowerCase)
                .map(s -> s.replaceAll("[^A-Z0-9a-z]", ""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, counting()));
        System.out.println(tags);


        List<String> records = List.of(
                "Alice:Math:85",
                "Bob:Math:92",
                "Alice:Science:78",
                "Charlie:Math:88",
                "Bob:Science:95",
                "Alice:English:91",
                "Charlie:Science:82"
        );
        Map<String, Double> avg = records.stream()
                .map(s -> {
                    String[] arr = s.split(":", 2);
                    arr[1] = arr[1].replaceAll("[^0-9]", "");
                    return arr;
                })
                .collect(Collectors.groupingBy(
                        arr -> arr[0],
                        LinkedHashMap::new,
                        Collectors.averagingDouble(arr -> Double.parseDouble(arr[1]))
                ));
        Map<String, Double> avg2 = records.stream()
                .map(s -> {
                    String[] parts = s.split(":");
                    if (parts.length < 3) {
                        // skip malformed record (or throw, as appropriate)
                        return null;
                    }
                    parts[2] = parts[2].replaceAll("[^0-9]", "");
                    return parts;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(parts -> parts[0],
                        TreeMap::new,
                        Collectors.averagingDouble(parts -> Double.parseDouble(parts[2]))));
        System.out.println(avg2);


        List<String> words = List.of("apple", "banana", "cherry");
        String word = words.stream().skip(1).reduce(words.getFirst(), (a, b) -> a + ", " + b);
        String word2 = words.stream()
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
        System.out.println(word2);


        List<String> words2 = List.of("cat", "elephant", "dog", "hippopotamus", "ibis");
        String word3 = words2.stream().reduce((a, b) -> a.length() >= b.length() ? a : b)
                .orElse("");
        System.out.println(word3);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Integer product = numbers.stream().filter(n -> n % 2 == 0)
                .reduce(1, (a, b) -> a * b);
        System.out.println(product);

        String fromNumbers = numbers.stream().filter(n -> n % 2 == 1)
                .map(String::valueOf).reduce("", (a, b) -> a + b);
        System.out.println(fromNumbers);


        List<String> words3 = List.of("apple", "banana", "Orange", "cat", "elephant");
        String result = words3.stream().filter(s -> s.matches("(?i)^[aeiou].*"))
                .reduce((a, b) -> b + " " + a).orElse("");
        String result2 = words3.stream()
                .filter(s -> {
                    char first = Character.toLowerCase(s.charAt(0));
                    return (first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u');
                }).reduce((a, b) -> b + " " + a).orElse("");
        System.out.println(result2);


        List<String> sentences = List.of(
                "Hello world hello",
                "World of Java %and~ hello",
                "Java is great and world is great"
        );
        Map<String, Long> map = sentences.stream().flatMap(s -> Arrays.stream(s.split(" ")))
                .map(String::toLowerCase)
                .map(s -> s.replaceAll("[^a-z0-9]", ""))
                .collect(Collectors.groupingBy(Function.identity(), counting()));
        Map<Integer, String> mapResult = map.entrySet().stream()
                .filter(e -> e.getValue() >= 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.groupingBy(String::length))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .reduce((a, b) -> a.compareTo(b) <= 0 ? a : b)
                                .orElseThrow(),
                        (x, y) -> x,
                        TreeMap::new
                ));
        System.out.println(mapResult);


        List<String> sentences2 = List.of(
                "Hello home, hello home",
                "Home ~~hello world happy hope",
                "World of ))happy)) hope",
                "Hope happy hello home happy920"
        );
        Map<Character, String> filter = sentences2.stream()
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .map(s -> s.replaceAll("^[^a-zA-Z]+|[^a-zA-Z]+$", ""))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() >= 2)
                .collect(Collectors.toMap(
                        e -> e.getKey().charAt(0),
                        e -> e,
                        (e1, e2) -> {
                            // now e1 and e2 are recognized as Entry, getValue() works
                            int freqCmp = e1.getValue().compareTo(e2.getValue());
                            if (freqCmp != 0) return freqCmp > 0 ? e1 : e2;
                            return e1.getKey().compareTo(e2.getKey()) <= 0 ? e1 : e2;
                        }
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().getKey(),
                        (a, b) -> a,
                        TreeMap::new
                ));
        System.out.println(filter);
        Map<Character, String> filter2 = sentences2.stream()
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .map(w -> w.replaceAll("^[^a-zA-Z]+|[^a-zA-Z]+$", ""))
                .filter(w -> !w.isEmpty())
                .map(String::toLowerCase)
                .peek(System.out::println)
                .collect(Collectors.groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .peek(System.out::println)
                .filter(e -> e.getValue() >= 2)
                .peek(System.out::println)
                .collect(Collectors.groupingBy(
                        e -> e.getKey().charAt(0),                       // group by first letter
                        TreeMap::new,                                    // sort letters
                        Collectors.collectingAndThen(
                                Collectors.reducing(                         // reduce inside group
                                        (Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) -> {
                                            int cmp = e1.getValue().compareTo(e2.getValue());
                                            if (cmp != 0) return cmp > 0 ? e1 : e2;
                                            return e1.getKey().compareTo(e2.getKey()) <= 0 ? e1 : e2;
                                        }
                                ),
                                opt -> opt.orElseThrow().getKey()         // unwrap Optional, get word
                        )
                ));
        System.out.println(filter2);


        List<String> logs = List.of(
                "ERROR disk full",
                "ERROR disk full",
                "INFO user login",
                "ERROR timeout",
                "INFO user login",
                "INFO server start",
                "WARN memory low",
                "WARN memory low",
                "WARN memory low",
                "INFO user login",
                "ERROR disk full",
                "WARN disk slow",
                "WARN    disk slow",
                "WARN disk slow"
        );
        Map<String, String> levelMessage = logs.stream()
                .map(line -> line.split("\\s+", 2))
                .collect(Collectors.groupingBy(arr -> arr[0],
                        TreeMap::new,
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(arr -> arr[1], Collectors.counting()),
                                map2 -> map2.entrySet().stream()
                                        .reduce((e1, e2) -> {
                                            int cmp = e1.getValue().compareTo(e2.getValue());
                                            if (cmp != 0) return cmp > 0 ? e1 : e2;
                                            return e1.getKey().compareTo(e2.getKey()) < 0 ? e1 : e2;
                                        })
                                        .map(Map.Entry::getKey)
                                        .orElse("")
                        )
                ));
        System.out.println(levelMessage);
        Map<String, String> levelMessage2 = logs.stream()
                .map(line -> line.split("\\s+", 2))
                .collect(Collectors.groupingBy(
                        arr -> arr[0],
                        TreeMap::new,
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(arr -> arr[1], Collectors.counting()),
                                map2 -> map2.entrySet().stream()
                                        .max(Comparator.comparingLong(Map.Entry<String, Long>::getValue)
                                                .thenComparing(Map.Entry.<String, Long>comparingByKey().reversed()))
                                        .map(Map.Entry::getKey)
                                        .orElse("")
                        )
                ));
        System.out.println(levelMessage2);
        Map<String, String> levelMessage3 = logs.stream()
                .map(line -> line.split("\\s+", 2))                 // делим на уровень и сообщение
                .collect(Collectors.groupingBy(
                        arr -> arr[0],                                  // ключ – уровень (ERROR/INFO/WARN)
                        TreeMap::new,                                   // сортировка ключей
                        Collectors.collectingAndThen(                    // внутри каждой группы
                                Collectors.groupingBy(arr -> arr[1], Collectors.counting()),  // частота сообщений
                                map2 -> map2.entrySet().stream()
                                        .max(Comparator
                                                .<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue)
                                                .thenComparing(Map.Entry.<String, Long>comparingByKey().reversed()))
                                        .map(Map.Entry::getKey)
                                        .orElse("")
                        )
                ));
        System.out.println(levelMessage3);



    }
}
