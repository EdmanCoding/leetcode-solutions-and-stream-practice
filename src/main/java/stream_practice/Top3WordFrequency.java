package stream_practice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Parent {
    void method() { System.out.println("Parent"); }
}

class Child extends Parent {
    @Override
    void method() { System.out.println("Child"); }
}

public class Top3WordFrequency {
    public static void main(String[] args) {
        Parent p = new Child();
        ((Parent) p).method();

        List<String> sentences = List.of(
                "Hello world and hello Java",
                "The world of Java is vast",
                "Hello to the world of streams"
        );
        List<String> result = sentences.stream().flatMap(sentence-> Arrays.stream(sentence.split(" ")))
                .map(String::toLowerCase).filter(word -> word.length()>=3)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(result);
        List<String> lines = List.of("a,b,c", "d,e", "f");
        List<String> result2 = lines.stream().flatMap(lin->Arrays.stream(lin.split(","))).toList();
        System.out.println(result2);

    }
}
