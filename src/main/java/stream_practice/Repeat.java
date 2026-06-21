package stream_practice;

import java.util.*;
import java.util.stream.Collectors;

public class Repeat {
    public static void main(String[] args) {
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

        Map<String, String> maxMinProducts = products.stream().collect(Collectors.groupingBy(
                Product::category,
                TreeMap::new,
                Collectors.teeing(
                        Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Product::price)),
                                opt-> opt.map(p->{
                                    return "Max: "+p.name()+" ("+p.price()+")";
                                }).orElse("")),
                        Collectors.collectingAndThen(
                                Collectors.minBy(Comparator.comparingDouble(Product::price)),
                                opt-> opt.map(p->{
                                    return "Min: "+p.name()+" ("+p.price()+")";
                                }).orElse("")
                        ),
                        (max,min)->{
                            return max+", "+min;
                        }
                )
        ));
        System.out.println(maxMinProducts);

        //===================================================================================

        record Order(double amount) {}
        record Customer(String name, List<Order> orders) {}

        List<Customer> customers = List.of(
                new Customer("Alice", List.of(new Order(150.0), new Order(200.0), new Order(50.0))),
                new Customer("Bob", List.of(new Order(250.0), new Order(100.0))),
                new Customer("Charlie", List.of()),
                new Customer("Diana", List.of(new Order(75.0)))
        );
        /* Требуется:
        Для каждого клиента найти суммарную стоимость всех его заказов.
        Отфильтровать клиентов, у которых сумма заказов больше 100.0.
        Вернуть Map<String, Double> (имя → сумма), отсортированную по убыванию суммы.*/
        Map<String,Double> ordersCost = customers.stream().collect(Collectors.toMap(
                Customer::name,
                c-> c.orders().stream().mapToDouble(Order::amount).sum()
        )).entrySet().stream().filter(e-> e.getValue()>100)
                .sorted(Map.Entry.<String,Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (s,t)->s,
                        LinkedHashMap::new
                ));
        System.out.println(ordersCost);

        //===================================================================================
        record Transaction(String account, double amount, boolean isDeposit) {}

        List<Transaction> transactions = List.of(
                new Transaction("A", 100, true),   // депозит +100
                new Transaction("A", 50, false),   // снятие -50
                new Transaction("B", 200, true),
                new Transaction("A", 30, true),
                new Transaction("B", 40, false)
        );
        /* Нужно:

        Для каждого счёта вычислить итоговый баланс (депозит добавляется, снятие вычитается).
        Вернуть Map<String, Double>, отсортированную по убыванию баланса (самый богатый в начале).
        Использовать groupingBy с downstream reducing(identity, mapper, operation).

        Ожидаемый результат:
        {B=160.0, A=80.0} */
        Map<String, Double> balance = transactions.stream().collect(Collectors.groupingBy(
                Transaction::account,
                Collectors.reducing(0.0, (Transaction t)-> t.isDeposit()?t.amount:-t.amount,
                        Double::sum)
        )).entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b)->a,
                LinkedHashMap::new
        ));
        System.out.println(balance);



    }
}
