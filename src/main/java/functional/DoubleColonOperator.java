package functional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DoubleColonOperator {
    public static void main(String[] args) {
        simple();
        consumer();
        usingFunction();
    }

    public static void simple() {
        List<String> list = Arrays.asList("node", "java", "python", "ruby");
        list.forEach(str -> System.out.println(str)); // lambda
        list.forEach(System.out::println);          // method references
    }

    public static void consumer() {
        List<String> list = Arrays.asList("A", "B", "C");
        // anonymous class
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String x) {
                DoubleColonOperator.SimplePrinter.print(x);
            }
        });

        // lambda expression
        list.forEach(x -> SimplePrinter.print(x));

        // method reference
        list.forEach(SimplePrinter::print);

    }

    public static void usingFunction() {
        List<String> list = Arrays.asList("1", "2", "3");

        // anonymous class
        List<Integer> collect1 = list.stream()
                .map(
                        new Function<String, Integer>() {
                            @Override
                            public Integer apply(String s) {
                                return Integer.parseInt(s);
                            }
                        })
                .collect(Collectors.toList());

        // lambda expression
        List<Integer> collect2 = list.stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        // method reference
        List<Integer> collect3 = list.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static class SimplePrinter {
        public static void print(String str) {
            System.out.println(str);
        }
    }


    @FunctionalInterface
    private static interface IComputer {
        Computer create();
    }

    private static class Computer implements IComputer, Comparable {
        private int year;
        private String color;
        private double price;

        @Override
        public Computer create() {
            return this;
        }

        public Computer() {

        }

        public Computer(int year, String color, double price) {
            this.year = year;
            this.color = color;
            this.price = price;
        }

        public int getYear() {
            return year;
        }

        public Integer getAge() {
            return LocalDate.now().getYear() - year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public int compareTo(Object o) {
            return this.getAge() - ((Computer) o).getAge();
        }
    }
}
