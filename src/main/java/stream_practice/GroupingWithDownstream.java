package stream_practice;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
        //===================================================================================

        record Order(String category, String city, double price) {}
        List<Order> orders = List.of(
                new Order("Electronics", "Moscow", 500.0),
                new Order("Electronics", "SPb", 750.0),
                new Order("Electronics", "Moscow", 1200.0),
                new Order("Books", "Moscow", 300.0),
                new Order("Books", "Kazan", 450.0),
                new Order("Books", "SPb", 200.0),
                new Order("Clothing", "Kazan", 150.0),
                new Order("Clothing", "Moscow", 150.0)
        );

        Map<String, Map.Entry<String, Double>> theBiggestPrice = orders.stream()
                .collect(Collectors.groupingBy(
                        o -> o.category(),
                        TreeMap::new,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(o->o.price())),
                                opt -> opt.map(order -> Map.entry(order.city(), order.price()))
                                        .orElse(null)
                )));
        System.out.println(theBiggestPrice);
        //===================================================================================

        record Product(String category, String name, double price){}
        List<Product> products = List.of(
                new Product("Electronics", "Laptop", 1200.0),
                new Product("Electronics", "Phone", 800.0),
                new Product("Electronics", "Tablet", 900.0),
                new Product("Books", "Java Guide", 45.0),
                new Product("Books", "Clean Code", 50.0),
                new Product("Books", "Design Patterns", 40.0),
                new Product("Clothing", "Jacket", 150.0),
                new Product("Clothing", "Sneakers", 120.0),
                new Product("Clothing", "Hat", 30.0)
        );
        // Нужно: для каждой категории найти самый дорогой товар и вернуть Map<String, String>:
        // ключ – категория (отсортировано по алфавиту)
        // значение – строка "название товара (цена)"
        // Если в категории несколько товаров с одинаковой максимальной ценой, можно взять любой.

        // Как бы ты изменил код, чтобы для каждой категории вернуть и самый дорогой и самый дешёвый
        // товар одновременно? Например, в виде строки "Max: Laptop (1200.0), Min: Phone (800.0)"
        Map<String, String> productsByCategory = products.stream().collect(Collectors.groupingBy(
                Product::category,
                TreeMap::new,
                Collectors.teeing(
                    Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Product::price)),
                opt->opt.map(p->{return p.name() + " (" + p.price() + "$)";}).orElse("")),
                    Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingDouble(Product::price)),
                            opt-> opt.map(p->{
                                return p.name() + " (" + p.price() + "$)";
                            }).orElse("")),
                        (max,min)->{
                            return "Max: " + max + ", Min: " + min;
                        }
                        )));
        System.out.println(productsByCategory);

        //===================================================================================

    }
}
