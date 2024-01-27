package functional;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: Show how to use functional programming in Java and difference between imperative and declarative style
 * Author: Thomas Hartmann
 */
public class DeclarativeStyle {
    List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private void imperativeStyle() {

        // Imperative style: Filtering even numbers and then doubling them
        List<Integer> result = new ArrayList<>();
        for (int number : numbers) {
            if (number % 2 == 0) {
                result.add(number * 2);
            }
        }

        // Printing the result
        for (int doubledNumber : result) {
            System.out.println(doubledNumber);
        }
    }

    private void declarativeStyle() {
        // Declarative style: Filtering even numbers and then doubling them
        numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(number -> number * 2)
                .forEach(System.out::println);
    }

}
