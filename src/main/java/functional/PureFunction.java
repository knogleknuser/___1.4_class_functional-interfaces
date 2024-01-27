package functional;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: Demo pure functions: Pure functions are functions that always produce the same output for the same input and have no side effects
 * Author: Thomas Hartmann
 */
public class PureFunction {
    private void pureFunctionDemo1() {

    }
    // Example of pure function with no side effects, since a new list is created and returned:
    public static List<Integer> squareList(List<Integer> inputList) {
        List result = new ArrayList();
        for (Integer number : inputList) {
            result.add(number * number);
        }
        return result;
    }

    // Example of non pure function with side effects, since the input list is modified and output depends on the state of the input list:
    public static void addToList(List<Integer> numbers, int value) {
        numbers.add(value);
    }

}
