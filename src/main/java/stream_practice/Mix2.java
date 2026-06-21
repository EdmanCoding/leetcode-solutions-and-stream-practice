package stream_practice;

import java.util.*;
import java.util.stream.Collectors;

public class Mix2 {
    public static void main(String[] args) {
        //===================================================================================

        record Item(String name, double price) {}
        record Order(int id, List<Item> items) {}
        record Customer(String name, List<Order> orders) {}
        /* Цель: для каждого клиента найти самый дорогой товар среди всех его заказов (если таких несколько — любой).
        Вернуть Map<String, String>, где ключ — имя клиента, значение — строка "название товара (цена)".
        Клиенты должны быть отсортированы по алфавиту имён. */
        List<Customer> customers = List.of(
                new Customer("Alice", List.of(
                        new Order(1, List.of(new Item("Laptop", 1200.0), new Item("Mouse", 50.0))),
                        new Order(2, List.of(new Item("Keyboard", 100.0)))
                )),
                new Customer("Bob", List.of(
                        new Order(3, List.of(new Item("Phone", 800.0)))
                )),
                new Customer("Charlie", List.of(
                        new Order(4, List.of(new Item("Tablet", 300.0), new Item("Pen", 5.0))),
                        new Order(5, List.of(new Item("Notebook", 10.0)))
                ))
        );
        /* Ожидаемый результат:
        Alice -> Laptop (1200.0)
        Bob   -> Phone (800.0)
        Charlie -> Tablet (300.0) */

        Map<String, String> mostExpensiveGoods = customers.stream().collect(Collectors.groupingBy(
                Customer::name,
                TreeMap::new,
                Collectors.flatMapping(c->c.orders().stream().flatMap(o-> o.items().stream()),
                    Collectors.collectingAndThen(
                            Collectors.maxBy(Comparator.comparingDouble(Item::price)),
                            opt-> opt.map(item-> item.name() + " ("+item.price()+")").orElse("")
                    )
        )));

        Map<String, String> mostExpensiveGoods2 = customers.stream()
                .flatMap(c -> c.orders().stream()
                        .flatMap(o -> o.items().stream())
                        .map(item -> Map.entry(c.name(), item)))            // Stream<Entry<String, Item>>
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        TreeMap::new,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(e -> e.getValue().price())),
                                opt -> opt.map(e -> e.getValue().name() + " (" + e.getValue().price() + ")").orElse("")
                        )
                ));
        System.out.println(mostExpensiveGoods);

        // Усложним: теперь нужно найти не просто самый дорогой товар,
        // а самый дорогой заказ (по сумме всех товаров в нём) для каждого клиента.
        Map<String, String> mostExpensiveOrders = customers.stream().collect(Collectors.groupingBy(
                Customer::name,
                TreeMap::new,
                Collectors.flatMapping(c->c.orders().stream(),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(order -> order.items()
                                                .stream().mapToDouble(i->i.price()).sum())),
                                opt-> opt.map(o-> "Order #"+o.id() + "("+
                                        o.items().stream().mapToDouble(Item::price).sum() + ")").orElse("")
                        )
                )));
        System.out.println(mostExpensiveOrders);
    }
}
