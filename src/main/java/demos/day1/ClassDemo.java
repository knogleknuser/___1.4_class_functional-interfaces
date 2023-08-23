package demos.day1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class ClassDemo {
    // Functional interface with a single method
    interface MyFunction {
        int doOperate(int a, int b);
    }

    public static void main(String[] args) {
        // Using a lambda expression to define the implementation of the functional interface
        MyFunction addition = (a, b) -> a + b;
        Integer result = addition.doOperate(3, 5); // Output: 8
        System.out.println(result.toString());

        // Interface: Predicate to check if a number is even
        Predicate<Integer> isEven = num -> num % 2 == 0; // One input, generates a boolean result
        System.out.println("4 is even: " + isEven.test(4));    // Output: true
        System.out.println("7 is even: " + isEven.test(7));    // Output: false

        // Interface: Consumer to print each element in a list
        Consumer<String> printElement = element -> System.out.println(element); // One input, no result
        List<String> names = List.of("Alice", "Bob", "Charlie"); // List.of() is a Java 9 feature
        names.forEach(printElement);    // Output: Alice, Bob, Charlie

        // Interface: Function to convert a string to its length
        Function<String, Integer> lengthFunction = str -> str.length(); // One input, generates a result
        System.out.println(lengthFunction.apply("Java"));     // Output: 4
        System.out.println(lengthFunction.apply("Programming")); // Output: 11

        // Interface: Supplier to generate a random number
        Supplier<Integer> randomSupplier = () -> new Random().nextInt(100); // No input, generates a result

        System.out.println(randomSupplier.get());    // Output: Random number between 0 and 99
        System.out.println(randomSupplier.get());    // Another random number

        // ConstructorMethodReferenceDemo();
        constructorMethodReferenceDemo();

    }

    // Default methods in interfaces
    interface InterfaceA {
        default void doSomething() {
            System.out.println("Default implementation in InterfaceA");
        }
    }

    interface InterfaceB {
        default void doSomething() {
            System.out.println("Default implementation in InterfaceB");
        }
    }

    private static class MyClass implements InterfaceA, InterfaceB {
        // This class inherits default methods from both InterfaceA and InterfaceB

        // resolve the conflict by overriding the default method (since both methods have the same signature)
        @Override
        public void doSomething() {
            InterfaceA.super.doSomething(); // Calling specific default method
        }
    }

    private static class Main {
        public static void main(String[] args) {
            MyClass obj = new ClassDemo.MyClass();
            obj.doSomething();
        }
    }

    // Method reference
    public void MethodReferenceDemo(){
        String[] names = {"Alice", "Bob", "Charlie", "David"};

        // Using static method reference to sort the array
        Arrays.sort(names, ClassDemo::compareNames);

        // Printing the sorted array
        for (String name : names) {
            System.out.println(name);
        }
    }
    public static int compareNames(String a, String b) {
        return a.compareTo(b);
    }

    public void MethodReferenceDemo2(){
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        // Using "instance method reference" to print each element in the list
        names.forEach(System.out::println);
    }

    private static class Person {
        private String name;

        public Person() {
            this.name = "Unknown";
        }
        public Person(String name) {
            this.name = name;
        }
        public Person(String firstName, String lastName) {
            this.name = firstName + " " + lastName;
        }

        public String getName() {
            return name;
        }
    }

    @FunctionalInterface
    interface PersonCreator {
        Person createPerson(String name);
    }

    private static void constructorMethodReferenceDemo() {
        // Using constructor method reference to create instances
        Supplier<Person> personSupplier = Person::new; // no-arg constructor
        Person person = personSupplier.get();
        person.getName();

        Function<String, Person> personCreatorOne = Person::new; // one-arg constructor
        Person person1 = personCreatorOne.apply("Alice");

        BiFunction<String, String, Person> personCreatorTwo = Person::new; // two-arg constructor
        Person person2 = personCreatorTwo.apply("Alice", "Johnson");

        // Using constructor method reference to create a Person object using a custom functional interface
        PersonCreator creator = Person::new;
        Person person3 = creator.createPerson("Alice");
        System.out.println("Person Name: " + person1.getName());
        System.out.println("Person Name: " + person2.getName());
        System.out.println("Person Name: " + person3.getName());
    }
}
