package stream_practice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Mix {
    public static void main(String[] args) {
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
        Map<String, Double> orderSumPrice = customers.stream().collect(Collectors.toMap(
                Customer::name,
                c->c.orders().stream().mapToDouble(Order::amount).sum()
        )).entrySet().stream().filter(e->e.getValue()>100)
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a,b)->a,
                        LinkedHashMap::new
                ));
        Map<String, Double> orderSumPrice2 = customers.stream().collect(Collectors.groupingBy(
                Customer::name,
                Collectors.summingDouble(c -> c.orders().stream().mapToDouble(Order::amount).sum())
                )).entrySet().stream().filter(e->e.getValue()>100)
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a,b)->a,
                        LinkedHashMap::new
                ));
        System.out.println(orderSumPrice2);

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
                Collectors.reducing(0.0,t-> t.isDeposit()?t.amount():-t.amount(),
                        Double::sum
        ))).entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2)->e1,
                                LinkedHashMap::new
                        ));
        System.out.println(balance);




    }
}
