package stream_practice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class PartitionGroupingBy {
    public static void main(String[] args) {
        List<String> items = List.of("apple", "banana", "apricot", "blueberry", "cherry", "avocado");
        Map<Boolean, List<String>> itemsA = items.stream().collect(Collectors.partitioningBy(
                s-> s.charAt(0) == 'a'
        ));
        System.out.println(itemsA.get(true));
        System.out.println(itemsA.get(false));

        Map<Character, List<String>> itemsLettersMoreThan5 = items.stream()
                .collect(groupingBy(s -> s.charAt(0),
                        Collectors.filtering(s -> s.length()>5, Collectors.toList())));
        System.out.println(itemsLettersMoreThan5);


        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Map<Boolean, List<Integer>> even = numbers.stream().collect(Collectors.partitioningBy(
                n -> n%2 == 0
        ));
        List<Integer> evenNumbers = even.get(true);
        String[] evenArray = evenNumbers.stream().map(i-> "Number: " + i.toString()).toArray(String[]::new);
        for(String s : evenArray)
            System.out.println(s);


        List<String> sentences = List.of(
                "Hello, world",
                "Hello again",
                "World. Again."
        );
        List<String> distinct = sentences.stream().flatMap(s->Arrays.stream(s.split("[^a-zA-Z]+")))
                .map(s->s.toLowerCase()).sorted().distinct().toList();
        System.out.println(distinct);
        System.out.println();


        record Transaction(String category, int amount) {}
        List<Transaction> transactions = List.of(
                new Transaction("food", 500),
                new Transaction("tech", 1200),
                new Transaction("food", 700),
                new Transaction("books", 300),
                new Transaction("tech", 800)
        );
        Map<String, Integer> amounts = transactions.stream().collect(groupingBy(
                t->t.category(),
                TreeMap::new,
                Collectors.summingInt((t)->t.amount())
        ));
        System.out.println(amounts);


        record Person(String group, List<String> phones) {}
        List<Person> people = List.of(
                new Person("A", List.of("111", "222")),
                new Person("A", List.of("222", "333")),
                new Person("B", List.of("444"))
        );
        Map<String, Set<String>> phonesByGroup = people.stream()
                .collect(groupingBy(Person::group,
                        flatMapping(p -> p.phones().stream(),
                                toSet())));
        System.out.println(phonesByGroup);


        record Order(String customer, String item) {}
        List<Order> orders = List.of(
                new Order("Alice", "apple"),
                new Order("Alice", "banana"),
                new Order("Alice", "apple"),
                new Order("Bob", "banana"),
                new Order("Bob", "orange")
        );
        // Требуется: Map<String, Integer> — количество уникальных товаров для каждого заказчика.
        // Ожидаемый результат: {Alice=2, Bob=2}
        Map<String, Integer> uniqueItems = orders.stream().collect(Collectors.groupingBy(
                p->p.customer(),
                Collectors.collectingAndThen(
                        mapping(p->p.item(),toSet()),
                        set->set.size()
                )
        ));
        System.out.println(uniqueItems);


        record Product(String category, double price) {}
        List<Product> products = List.of(
                new Product("fruit", 1.2),
                new Product("fruit", 0.8),
                new Product("vegetable", 2.5),
                new Product("vegetable", 3.0),
                new Product("fruit", 2.0)
        );
        // Ожидаемый результат: {fruit=1.33, vegetable=2.75}
        Map<String, Double> averagePrice = products.stream()
                .collect(Collectors.groupingBy(
                        p-> p.category(),
                        TreeMap::new,
                        Collectors.collectingAndThen(
                                averagingDouble(p-> p.price()),
                                avg-> Math.round(avg * 100.0) / 100.0
                        )
                ));
        System.out.println(averagePrice);


        record Student(String name, List<String> courses) {}
        List<Student> students = List.of(
                new Student("Alice", List.of("Math", "Physics")),
                new Student("Bob", List.of("Math", "CS")),
                new Student("Charlie", List.of("Physics", "CS", "Art")),
                new Student("Diana", List.of("Math", "Art"))
        );
        // Ожидаемый результат:
        // {Art=[Charlie, Diana], CS=[Bob, Charlie], Math=[Alice, Bob, Diana], Physics=[Alice, Charlie]}
        List<String> courses = students.stream()
                .flatMap(student -> student.courses().stream()).toList();
        System.out.println(courses);

        List<Map.Entry<String,String>> courses2 = students.stream()
                .flatMap(student -> student.courses().stream()
                        .map(course -> Map.entry(course, student.name()))).toList();
        System.out.println(courses2);

        Map<String, Set<String>> courses3 = students.stream()
                .flatMap(student -> student.courses().stream()
                        .map(course -> Map.entry(course, student.name())))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        TreeMap::new,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toCollection(TreeSet::new))
                ));
        System.out.println(courses3);


        List<String> words = List.of("apple", "banana", "blue", "breeze", "cat");
        // Ожидаемый результат: "BREEZE"
        String word = words.stream().filter(w->w.startsWith("b"))
                .filter(w->w.endsWith("e")).map(String::toUpperCase)
                .findFirst().orElse("Not found");
        System.out.println(word);


        List<String> sentences2 = List.of(
                "Hello world hello",
                "World of Java and hello",
                "Java is great and world is great"
        );
        // Ожидаемый результат: [and, great, hello, java, world]
        List<String> words2 = sentences2.stream().flatMap(s->Arrays.stream(s.split("\\s+")))
                .map(String::toLowerCase).collect(Collectors.groupingBy(
                        Function.identity(),
                        TreeMap::new,
                        Collectors.counting()
                )).entrySet().stream().filter(e->e.getValue()> 1L)
                .map(Map.Entry::getKey).toList();
        System.out.println(words2);


        List<String> sentences3 = List.of(
                "The quick brown fox jumps over the lazy dog",
                "The lazy dog sleeps all day",
                "A quick movement of the enemy will be detected"
        );
        // Ожидаемый результат: [the, quick, lazy, over?] — давай посчитаем.
        List<String> words3 = sentences3.stream().flatMap(s-> Arrays.stream(s.split("\\s+")))
                .map(String::toLowerCase).collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )).entrySet().stream().filter(e->e.getKey().length()>3)
                .sorted((e1,e2)-> e2.getValue().compareTo(e1.getValue()))
                .limit(3L).map(e->e.getKey()).toList();
        System.out.println(words3);


        record Order2(String item, int quantity) {}
        List<Order2> orders2 = List.of(
                new Order2("apple", 3),
                new Order2("banana", 2),
                new Order2("apple", 4),
                new Order2("orange", 5),
                new Order2("banana", 1)
        );
        // Ожидаемый результат: {apple=7, banana=3, orange=5}
        Map<String, Integer> quantities = orders2.stream()
                .collect(Collectors.groupingBy(
                        o->o.item(),
                        TreeMap::new,
                        Collectors.reducing(0, Order2::quantity, Integer::sum)));
        System.out.println(quantities);


        // Дано: список чисел. Верни первый элемент, который делится на 3 и на 5 одновременно. Если такого нет — верни -1
        List<Integer> numbers2 = List.of(1, 2, 4, 7, 15, 22, 30);
        // Ожидаемый результат: 15
        Integer numb = numbers2.stream().filter(n-> n%3 == 0 && n%5 == 0)
                .findFirst().orElse(-1);
        System.out.println(numb);


        // Нужно получить две вещи:
        // Общую выручку по всем заказам (сумма quantity * pricePerUnit)
        // Список названий товаров, у которых цена за единицу больше 10.0
        record Order3(String item, int quantity, double pricePerUnit) {}
        List<Order3> orders3 = List.of(
                new Order3("apple", 3, 12.0),
                new Order3("banana", 2, 8.0),
                new Order3("orange", 5, 15.0),
                new Order3("kiwi", 4, 9.5)
        );
        double sum = orders3.stream().map(o->o.quantity()*o.pricePerUnit())
                .reduce(0.0, Double::sum);
        double sum2 = orders3.stream()
                .collect(Collectors.summingDouble(o -> o.quantity() * o.pricePerUnit()));
        double sum3 = orders3.stream().mapToDouble(o -> o.quantity() * o.pricePerUnit()).sum();
        List<String> items2 = orders3.stream().filter(o -> o.pricePerUnit()>10.0)
                .map(Order3::item).toList();
        System.out.println(sum);


        record Transaction2(String id, int amount) {}
        List<Transaction2> transactions2 = List.of(
                new Transaction2("T1", 500),
                new Transaction2("T2", 1200),
                new Transaction2("T3", 800)
        );
        // Ожидаемый результат: Optional[Transaction("T2", 1200)]
        Optional<Transaction2> trans = transactions2.stream()
                .reduce((tx, tx2)->tx.amount()>tx2.amount() ? tx : tx2);
        System.out.println(trans.get());


        List<Order2> orders4 = List.of(
                new Order2("apple", 5),
                new Order2("apple", 3),
                new Order2("apple", 7),
                new Order2("banana", 2),
                new Order2("banana", 9)
        );
        // Ожидаемый результат: {apple=7, banana=9}
        Map<String, Integer> result = orders4.stream()
                .collect(Collectors.groupingBy(
                        (Order2 o) -> o.item(),
                        TreeMap::new,
                        reducing(Integer.MIN_VALUE, (Order2 o) -> o.quantity(), Integer::max)
                ));
        Map<String, Integer> result2 = orders4.stream()
                .collect(Collectors.groupingBy(
                        Order2::item,
                        TreeMap::new,
                        reducing(Integer.MIN_VALUE, Order2::quantity, Integer::max)
                ));
        Map<String, Integer> result3 = orders4.stream()
                .collect(Collectors.groupingBy(
                        o -> o.item(),
                        TreeMap::new,
                        Collectors.reducing(Integer.MIN_VALUE, o -> o.quantity(), Integer::max)
                ));
        Map<String, Integer> result4 = orders4.stream()
                .collect(Collectors.groupingBy(
                        Order2::item,
                        TreeMap::new,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Order2::quantity)),
                                opt -> opt.map(Order2::quantity).orElse(0)
                        )
                ));
        System.out.println(result4);



    }
}
