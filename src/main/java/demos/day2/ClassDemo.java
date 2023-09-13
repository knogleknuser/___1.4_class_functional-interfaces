package demos.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

public class ClassDemo {
    public static void main(String[] args) {
        // Collectors API
        // Stream from Collection:
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        Stream<String> streamFromList = list.stream();

        // Stream from Array:
        String[] array = {"one", "two", "three"};
        Stream<String> streamFromArray = Arrays.stream(array);

        // From I/O:
        System.out.println(System.getProperty("user.dir"));
        try (Stream<String> lines = Files.lines(Path.of("./src/main/java/solutions/day2/file.txt"))) {
            // Process each line
            List<String> names = lines.toList();
            names.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // From stream of
        Stream<String> streamOfElements = Stream.of("one", "two", "three");

        // Infite stream from iterate
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2); // Generates even numbers
        infiniteStream.limit(10).forEach(System.out::println);

        // From IntStream, LongStream, DoubleStream
        IntStream intStream = IntStream.range(1, 6); // Generates 1 to 5
        LongStream longStream = LongStream.rangeClosed(1, 5); // Generates 1 to 5
        DoubleStream doubleStream = DoubleStream.of(1.0, 2.0, 3.0);

        // Stream Methods:
        // Intermediate methods: filter, map, flatMap, distinct, sorted, peek, limit, skip
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "apricot", "banana");
        List<String> processedWords = words.stream()
                .filter(word -> !word.startsWith("a")) // Filter out words starting with "a"
                .map(String::toUpperCase) // Transform to uppercase
                .distinct() // Remove duplicates
                .sorted() // Sort alphabetically
                .collect(Collectors.toList()); // Collect to list
        System.out.println(processedWords);

        // Terminal methods: forEach, collect, reduce, min, max, count, anyMatch, allMatch, noneMatch, findFirst, findAny
        words.stream().forEach(System.out::println); // Print each element
        int totalLengthOfWords = words.stream().map((word) -> word.length()).reduce(0, (subTotal, element) -> subTotal + element); // Calculate total length
        System.out.println("Total length of words: " + totalLengthOfWords);
        int countOfWords = (int) words.stream().count(); // Count number of words
        System.out.println("Number of words: " + countOfWords);
        Optional<String> firstWord = words.stream().findFirst(); // Find first word
        System.out.println("First word: " + firstWord.get());
        Optional<String> anyWord = words.stream().findAny(); // Find any word
        System.out.println("Any word: " + anyWord.get());

        // Collectors API
       // toMap
        Map<String, Integer> nameLengthMap = Stream.of("alice", "bob", "charlie")
                .collect(Collectors.toMap(name -> name, name -> name.length()));
        System.out.println("Length of each name: "+nameLengthMap);
        // joining
        String joinedNames = Stream.of("alice", "bob", "charlie")
                .collect(Collectors.joining(", "));
        System.out.println("Joined names: "+joinedNames);

        // groupBy
        Map<Character, List<String>> groupedNames = Stream.of("Alice", "Bob", "Charlie","Allan")
                .collect(Collectors.groupingBy(name -> name.charAt(0)));
        System.out.println("Grouped names: "+groupedNames);

        // Partitioning
        Map<Boolean, List<Integer>> evenOddPartition = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.partitioningBy(num -> num % 2 == 0));
        System.out.println("Even and odd numbers: "+evenOddPartition);

        // Custom Collector
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        NumbersPartition partition = numbers.stream()
                .collect(new EvenOddPartitioningCollector());

        System.out.println("Even numbers: " + partition.evenNumbers);
        System.out.println("Odd numbers: " + partition.oddNumbers);

        int sum = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.summingInt(Integer::intValue)); // 15

        double average = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.averagingInt(Integer::intValue)); // 3.0

        Optional<Integer> max = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.maxBy(Comparator.naturalOrder())); // 5
        System.out.println("SUM: " + sum + ", AVERAGE: " + average + ", MAX: " + max.get());
    }

    private static class NumbersPartition {
        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();
    }
    private static class EvenOddPartitioningCollector implements Collector<Integer, NumbersPartition, NumbersPartition> {
        @Override
        public Supplier<NumbersPartition> supplier() {
            return NumbersPartition::new;
        }

        @Override
        public BiConsumer<NumbersPartition, Integer> accumulator() {
            return (partition, number) -> {
                if (number % 2 == 0) {
                    partition.evenNumbers.add(number);
                } else {
                    partition.oddNumbers.add(number);
                }
            };
        }

        @Override
        public BinaryOperator<NumbersPartition> combiner() {
            return (left, right) -> {
                left.evenNumbers.addAll(right.evenNumbers);
                left.oddNumbers.addAll(right.oddNumbers);
                return left;
            };
        }

        @Override
        public Function<NumbersPartition, NumbersPartition> finisher() {
            return partition -> partition;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return EnumSet.noneOf(Characteristics.class);
        }
    }

}
