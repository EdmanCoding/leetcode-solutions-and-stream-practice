package stream_practice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Matchers {
    public static void main(String[] args) {

        List<String> words = List.of("racecar", "level", "radar", "hello");
        // Ожидаемый результат: false   (hello не палиндром)
        boolean palindrome = words.stream()
                .map(String::toLowerCase)
                .allMatch(w->w.contentEquals(new StringBuilder(w).reverse()));
        boolean palindrome2 = words.stream()
                .allMatch(w -> w.equalsIgnoreCase(new StringBuilder(w).reverse().toString()));
        System.out.println(palindrome2);


        List<String> list1 = List.of("short", "tiny", "extremelylongword");
        List<String> list2 = List.of("apple", "banana", "cherry123", "date");
        // Ожидаемый результат:
        // list1 содержит слово длиннее 10? -> true
        // list2 не содержит слов с цифрами? -> false (cherry123)
        boolean longEnough = list1.stream().anyMatch(w->w.length()>10);
        boolean nonContainDigits = list2.stream().noneMatch(w-> w.matches(".*\\d.*"));
        System.out.println(longEnough + ", " + nonContainDigits);


        List<String> urls = List.of("/api/1", "/api/2", "/api/3");
        String first200 = urls.stream().filter(u-> fetchStatus(u)==200).findFirst().orElse("No success");
        System.out.println(first200);


        record Sale(String bookTitle, String author, int copiesSold) {}
        List<Sale> sales = List.of(
                new Sale("Book A", "John", 100),
                new Sale("Book B", "Alice", 200),
                new Sale("Book A", "John", 50),   // John, Book A, ещё 50 -> всего 150
                new Sale("Book C", "Alice", 100), // Alice: Book B 200, Book C 100 -> всего 300
                new Sale("Book D", "Bob", 300),   // Bob: одна книга 300
                new Sale("Book E", "John", 50),   // John: Book A 150, Book E 50 -> всего 200
                new Sale("Book F", "Bob", 100),    // Bob: Book D 300, Book F 100 -> всего 400
                new Sale("Book G", "Bulbash", 100),
                new Sale("Book H", "Bulbash", 100)
        );
        // Нужно получить три имени авторов, чьи книги продались наибольшим суммарным тиражом (сумма copiesSold).
        // При равенстве суммарного тиража — выбирай того автора, у которого количество уникальных книг (разных названий) больше.
        // Если и тут равенство — того, чьё имя идёт раньше по алфавиту.
        // 1. Группируем все продажи по автору
        Map<String, List<Sale>> salesByAuthor = sales.stream()
                .collect(Collectors.groupingBy(Sale::author));

        // 2. Для каждого автора считаем суммарный тираж и количество уникальных книг
        record AuthorStats(String author, int totalSales, long uniqueBooks) {}

        List<AuthorStats> stats = salesByAuthor.entrySet().stream()
                .map(entry -> {
                    String author = entry.getKey();
                    List<Sale> authorSales = entry.getValue();
                    int total = authorSales.stream().mapToInt(Sale::copiesSold).sum();
                    long uniqueBooks = authorSales.stream()
                            .map(Sale::bookTitle)
                            .distinct()
                            .count();
                    return new AuthorStats(author, total, uniqueBooks);
                })
                .toList();

        // 3. Сортируем и берём топ-3
        List<String> topAuthors = stats.stream()
                .sorted(Comparator
                        .comparingInt(AuthorStats::totalSales)
                        .thenComparingLong(AuthorStats::uniqueBooks).reversed()
                        .thenComparing(AuthorStats::author))
                .limit(3)
                .map(AuthorStats::author)
                .toList();

        List<String> topAuthors2 = sales.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(Sale::author,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        authorSales -> {
                                            int total = authorSales.stream().mapToInt(Sale::copiesSold).sum();
                                            long unique = authorSales.stream().map(Sale::bookTitle).distinct().count();
                                            return Map.entry(total, unique);
                                        })),
                        map -> map.entrySet().stream()
                                .sorted(Comparator
                                        .comparingInt((Map.Entry<String, Map.Entry<Integer, Long>> e) -> e.getValue().getKey())
                                        .thenComparingLong(e -> e.getValue().getValue()).reversed()
                                        .thenComparing(Map.Entry::getKey))
                                .limit(3)
                                .map(Map.Entry::getKey)
                                .toList()
                ));
        System.out.println(topAuthors2);


        record Order(String item, int quantity, double price) {}
        List<Order> orders = List.of(
                new Order("apple", 2, 1.5),   // 3.0
                new Order("apple", 3, 2.0),   // 6.0 => total 9.0
                new Order("banana", 4, 0.75), // 3.0 => total 3.0
                new Order("banana", 2, 1.25)  // 2.5 => total 5.5
        );
        // Ожидаемый результат: {apple=9.00, banana=5.50}
        Map<String, Double> ordersTotalPrice = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::item,
                        TreeMap::new,
                        Collectors.collectingAndThen(
                                Collectors.reducing(0.0, (Order o) -> o.quantity()*o.price, (tot1, tot2)-> tot1+tot2),
                                total -> BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP).doubleValue()
                        )
                ));
        Map<String, Double> ordersTotalPrice2 = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::item,
                        TreeMap::new,
                        Collectors.collectingAndThen(
                                Collectors.summingDouble(o -> o.quantity() * o.price()),
                                total -> BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP).doubleValue()
                        )
                ));
        System.out.println(ordersTotalPrice2);


        record Student(String name, List<String> courses) {}
        List<Student> students = List.of(
                new Student("Al", List.of("Math", "CS")),        // Al — короткое, исключается
                new Student("Bob", List.of("Math", "Physics")),
                new Student("Charlie", List.of("CS", "Art")),
                new Student("Popiks", List.of("CS", "Physics")),
                new Student("Di", List.of("Art"))                 // Di — короткое
        );
        // Ожидаемый результат: {Art=[Charlie], CS=[Charlie, Popiks], Math=[Bob], Physics=[Bob, Popiks]}
        Map<String, Set<String>> majors = students.stream()
                .flatMap(s-> s.courses().stream().map(course-> Map.entry(course, s.name())))
                .filter(e-> e.getValue().length()>=3)
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        TreeMap::new,
                        Collectors.mapping(Map.Entry::getValue,
                                Collectors.toSet())
                ));
        System.out.println(majors);


        record Order2(String dish, String customer) {}

        List<Order2> orders2 = List.of(
                new Order2("pizza", "Alice"),
                new Order2("pasta", "Alice"),
                new Order2("pizza", "Alice"),   // повтор
                new Order2("salad", "Bob"),
                new Order2("pizza", "Bob"),
                new Order2("steak", "Charlie")
        );
        // Ожидаемый результат: {Alice=2, Bob=2, Charlie=1}
        Map<String, Long> peopleOrders = orders2.stream()
                .collect(Collectors.groupingBy(
                        o -> o.customer(),
                        TreeMap::new,
                        Collectors.collectingAndThen(
                                Collectors.mapping(Order2::dish,
                                        Collectors.toSet()),
                                s-> Long.valueOf(s.size())
                        )
                ));
        Map<String, Long> peopleOrders2 = orders2.stream()
                .map(o -> Map.entry(o.customer(), o.dish()))
                .distinct()
                .collect(Collectors.groupingBy(Map.Entry::getKey, TreeMap::new, Collectors.counting()));
        System.out.println(peopleOrders);


        record OrderItem(String item, double price) {}
        record User(String name, List<OrderItem> orders) {}
        List<User> users = List.of(
                new User("Alice", List.of(
                        new OrderItem("apple", 1.5),
                        new OrderItem("banana", 2.0),
                        new OrderItem("apple", 1.5)
                )),
                new User("Bob", List.of(
                        new OrderItem("apple", 1.5),
                        new OrderItem("orange", 3.0)
                )),
                new User("Charlie", List.of(
                        new OrderItem("banana", 2.0),
                        new OrderItem("orange", 3.0),
                        new OrderItem("apple", 1.5)
                ))
        );
        // Ожидаемый результат:
        // apple  → totalRevenue = 4.5, orderCount = 3
        // banana → totalRevenue = 4.0, orderCount = 2
        // orange → totalRevenue = 6.0, orderCount = 2
        record ItemStats(double totalRevenue, long orderCount) {}
        Map<String, Double> revenue = users.stream()
                .flatMap(u-> u.orders().stream())
                .collect(Collectors.groupingBy(u-> u.item(),
                        Collectors.summingDouble(u->u.price())));
        Map<String, Long> orderCount = users.stream()
                .flatMap(u-> u.orders.stream())
                .collect(Collectors.groupingBy(OrderItem::item,
                        Collectors.counting()));
        Iterator<Map.Entry<String, Double>> iter = revenue.entrySet().iterator();
        Iterator<Map.Entry<String, Long>> iterOrderCount = orderCount.entrySet().iterator();
        Map<String, ItemStats> answer = new HashMap<>();
        while(iter.hasNext()){
            Map.Entry<String, Double> entry = iter.next();
            Map.Entry<String, Long> entryCount = iterOrderCount.next();
            ItemStats statistics = new ItemStats(entry.getValue(), entryCount.getValue());
            answer.put(entry.getKey(), statistics);
        }
        Map<String, ItemStats> itemAndStats = revenue.keySet().stream()
                        .collect(Collectors.toMap(
                                item -> item,
                                item -> new ItemStats(revenue.get(item), orderCount.get(item))
                        ));

        System.out.println(itemAndStats);



    }
    public static int fetchStatus(String url) {
        return switch (url) {
            case "/api/2" -> 200;
            case "/api/3" -> 500;
            default -> 404;
        };
    }
}
