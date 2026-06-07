package stream_practice;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Teeing {
    public static void main(String[] args) {
        // Найди товар с максимальной суммарной выручкой (quantity * price) и количество уникальных товаров. Верни строку вида:
        // "Best: apple ($150.0), unique items: 5"
        // Если список пуст, верни "No orders".
        record Order(String item, int quantity, double price) {}
        List<Order> orders = List.of(
                new Order("apple", 2, 1.5),   // 3.0
                new Order("banana", 4, 0.75), // 3.0
                new Order("apple", 3, 2.0),   // 6.0 -> apple total 9.0
                new Order("orange", 5, 3.0)   // 15.0 -> orange total 15.0
        );

        String maxRev = orders.stream()
                .collect(Collectors.teeing(
                        Collectors.groupingBy(Order::item, Collectors.summingDouble(o->o.quantity()*o.price())),
                        Collectors.collectingAndThen( Collectors.mapping(Order::item, Collectors.toSet()), Set::size ),
                        (map, size)->{
                            Map.Entry<String, Double> bestEntry = map.entrySet().stream()
                                    .max(Map.Entry.comparingByValue())
                                    .orElse(null);
                            if (bestEntry == null) return "No orders";
                            return String.format("Best: %s ($%.1f), unique items: %d",
                                    bestEntry.getKey(), bestEntry.getValue(), size);
                        }
                ));
        System.out.println(maxRev);
        //===================================================================================

        // Дан список целых чисел.
        // Найди глобальный максимум (самое большое число во всём списке), используя stream.max(...).
        // Разбей числа на две группы: чётные и нечётные. Для каждой группы найди свой максимум,
        // используя groupingBy + Collectors.maxBy(...).
        // Верни результат в виде строки:
        // "Global max: X, Even max: Y, Odd max: Z"
        // Если список пуст, верни "No numbers".
        List<Integer> numbers = List.of(3, 7, 2, 8, 5, 10);

        String maxAndMaxBy = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.groupingBy(el -> el%2!=0, Collectors.collectingAndThen(
                                Collectors.maxBy(Integer::compareTo), (o->o.orElse(0)))),
                        Collectors.groupingBy(el -> el%2==0, Collectors.collectingAndThen(
                                Collectors.maxBy(Integer::compareTo), (o->o.orElse(0)))),
                        (oddMap,  evenMap)->{
                            int oddMax = oddMap.get(true);
                            int evenMax = evenMap.get(true);
                            int globMax = numbers.stream().max(Integer::compareTo).orElse(0);
                            return "Global max: "+globMax+", Even max: "+evenMax+", Odd max: "+oddMax;
                        }
                ));
        String threeMax = numbers.stream()
                .collect(Collectors.collectingAndThen(
                        // groupingBy возвращает Map<Boolean, Integer>
                        Collectors.groupingBy(
                                n -> n % 2 == 0,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Integer::compareTo),
                                        opt -> opt.orElse(0)
                                )
                        ),
                        // finisher: берём готовую мапу и формируем строку
                        map -> {
                            int evenMax = map.getOrDefault(true, 0);
                            int oddMax = map.getOrDefault(false, 0);
                            int globalMax = Math.max(evenMax, oddMax);
                            return String.format("Global max: %d, Even max: %d, Odd max: %d",
                                    globalMax, evenMax, oddMax);
                        }
                ));
        System.out.println(threeMax);
    }
}
