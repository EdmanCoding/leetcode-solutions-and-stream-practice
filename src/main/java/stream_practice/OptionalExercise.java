package stream_practice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OptionalExercise {

    public static Optional<String> fetchData(String url) {
        // для теста: url2 возвращает данные, остальные пустые
        if (url.equals("url2")) return Optional.of("Important data");
        return Optional.empty();
    }

    public static void main(String[] args) {
        List<String> names = List.of("Bob", "Alice", "John", "Anna", "Alex");
        String firstA = names.stream().filter(s->s.startsWith("A"))
                .findFirst().map(String::toUpperCase).orElse("No A-names");
        System.out.println(firstA);


        List<String> users = List.of("Bob:bob@mail.com", "Alice:", "John:john@mail.com", "Anna:anna@mail.com");
        String email = users.stream()
                .filter(user -> user.startsWith("A"))
                .map(user -> user.split(":", 2))
                .filter(parts -> parts.length > 1 && !parts[1].isEmpty())
                .map(parts -> parts[1])                          // extract email directly
                .findFirst()
                .orElse("No email");
        String email2 = users.stream()
                .filter(user -> user.startsWith("A"))
                .map(user -> user.split(":", 2))
                .filter(parts -> parts.length > 1 && !parts[1].isEmpty())
                .map(parts -> Optional.of(parts[1]))            // оборачиваем в Optional
                .filter(Optional::isPresent)                 // лямбда, не Method Reference
                .map(Optional::get)                          // лямбда
                .findFirst()
                .orElse("No email");
        System.out.println(email2);


        List<String> urls = List.of("url1", "url2", "url3");
        String url = urls.stream().map(s->fetchData(s))
                .filter(Optional::isPresent)
                .map(opt-> opt.get())
                .findFirst()
                .orElse("No data");
        String url2 = urls.stream()
                .map(s -> fetchData(s))
                .flatMap(opt -> opt.stream())   // Java 9+
                .findFirst()
                .orElse("No data");
        System.out.println(url2);


        List<Optional<String>> maybeWords = List.of(
                Optional.of("Java"),
                Optional.empty(),
                Optional.of("Stream"),
                Optional.empty(),
                Optional.of("Optional")
        );
        List<String> words = maybeWords.stream().flatMap(opt-> opt.stream()).toList();
        List<String> wordsOrds = maybeWords.stream().filter(opt -> opt.isPresent())
                        .map(opt -> opt.get()).toList();
        System.out.println(wordsOrds);

    }
}
