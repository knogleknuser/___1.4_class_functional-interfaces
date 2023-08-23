package functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalComposition {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        printOutComposedFunctions();
        supplierFunction();
        personTester();
    }
    public static void printOutComposedFunctions(){
        //java.util.function.Function<T,T> is an interface that takes one value and returns another
        Function<Double, Double> log = (value) -> Math.log(value);
        Function<Double, Double> sqrt = (value) -> Math.sqrt(value);
        // the log function and the sqrt function are chained
        // compose applies the function passed in the argument first and then the function on which it's invoked.
        Function<Double, Double> logThenSqrt = sqrt.compose(log);
        System.out.println(String.valueOf(logThenSqrt.apply(3.14)));
        // chaining the opposite direction with andThen()
        Function<Double, Double> sqrtThenLog = sqrt.andThen(log);
        System.out.println(String.valueOf(sqrtThenLog.apply(3.14)));
    }
    public static void supplierFunction(){
        // to reuse a stream object we can use the Supplier. Supplier has a get() method that returns what the lambda expression returned
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok
    }
    public static void personTester(){

        List<Person> persons = Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 21),
                        new Person("David", 12));
        List<Person> filtered = persons
                        .stream()
                        .filter(p -> p.name.startsWith("P"))
                        .collect(Collectors.toList());
        System.out.println(filtered);
    }

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name+" of "+age;
        }
    }

}
