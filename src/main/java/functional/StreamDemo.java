package functional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * In functional programming, a monad is a structure that represents computations defined as sequences of steps.
 * A type with a monad structure defines what it means to chain operations, or nest functions of that type together.
 * java.util.streams.Steams is such a Monad type
 */
public class StreamDemo {
    public static void main(String[] args) {
        streamTester();
    }
    public static void streamTester(){
        //filter, map, sorted
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
            //the operation pipeline:
            .stream()
            .filter(s -> s.startsWith("c")) //intermediate operation
            .map(String::toUpperCase) //intermediate
            .sorted()
            .forEach(System.out::println); //forEach is a terminal operation since it does not return a Stream object

        // using Stream.of() to create stream from many different object types
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);

        // alternative to for loop in java
        IntStream.range(1, 4) //IntStream is a special primitive type stream that has methods: sum() and average();
                .forEach(System.out::println);

        // parsing to integer
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);  // 3


    }
}
