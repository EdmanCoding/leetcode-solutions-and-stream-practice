package stream_practice;

import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class Repeat2 {
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

        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        list.stream().forEach(x -> {
            if (x.equals("A")) {
                list.remove(x);
            }
        });

    }
}
