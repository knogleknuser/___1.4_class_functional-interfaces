package demos.day1;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassExercise {
    public static void main(String[] args) {
        ClassExercise ce = new ClassExercise();
        // ex1
        ce.ex1();
        // ex1.2
        System.out.println(ce.operate(10, 20, (a, b) -> a + b)); // 30
        ce.operate(new int[]{3,6,9},new int[]{2,4,6}, (a, b) -> a - b); // [1, 2, 3]
        // ex2: Functional Programming

        System.out.println("ex2 Functional Programming");
        System.out.println("map");
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14, 15};
        int[] b = ce.map(a, (x) -> x * x);
        for (int i : b) {
            System.out.println(i);
        }
        System.out.println("filter");
        int[] c = {1, 2, 3, 4, 5};
        int[] d = ce.filter(c, (x) -> x % 2 == 0);  // [0, 2, 0, 4, 0]
        for (int i : d) {
            System.out.println(i);
        }

        System.out.println("Ex3 Functional Programming");
        System.out.println("predicate");
        Predicate<Integer> divisibleBy3 = num -> num % 3 == 0; // One input, generates a boolean result
        int[] result = new int[a.length];
        for(int i = 0; i < a.length; i++){
            if(divisibleBy3.test(a[i])){
                result[i] = a[i];
            }
        }
        System.out.println("Supplier");
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");
        Supplier<Employee> employeeSupplier = () -> new Employee(names.get((int) (Math.random() * names.size())), (int)(Math.random() * 100000-9000));
        List<Employee> employees = Stream.generate(employeeSupplier).limit(10).collect(Collectors.toList());
        System.out.println("Consumer");
        Consumer writeEmployee = (emp) -> System.out.println(emp);
        employees.forEach(writeEmployee);

        System.out.println("Function interface to convert a list of Employees to a list of names");
        // Use Function to convert a list of Employees to a list of names
        Function<Employee, String> getEmployeeName = (emp) -> emp.getName();
        List<String> employeeNames = employees.stream().map(getEmployeeName).collect(Collectors.toList());
        System.out.println(employeeNames);

        System.out.println("Predicate to find adult employees");
        Predicate<Employee> isAdult = (emp) -> emp.getSalary() >= 10000;
        List<Employee> adultEmployees = employees.stream().filter(isAdult).collect(Collectors.toList());

        System.out.println("Ex 4 java Time API");
        System.out.println("LocalDate");
        for(Employee emp : employees){
            // create random date between 1990 and 2020
            LocalDate randomDate = LocalDate.of((int)(Math.random() * 30) + 1990, (int)(Math.random() * 12) + 1, (int)(Math.random() * 28) + 1);
            emp.setBirthday(randomDate);
            System.out.println(emp.getName() + " " + emp.getSalary() + " " + emp.getBirthday());
        }
        for(Employee emp : employees){
            // Calculate emp age
            LocalDate today = LocalDate.now();
            // get duration between today and birthday
            int age = Period.between(emp.getBirthday(), today).getYears(); // g
            System.out.println(emp.getName() + " is " + age + " years old");
        }
        // Calculate average age
        int summedAges = 0;
        for(Employee emp : employees){
            LocalDate today = LocalDate.now();
            int age = today.getYear() - emp.getBirthday().getYear();
            summedAges += age;
        }
        System.out.println("Average age: " + summedAges / employees.size());

        // Filter employees with a birthday in april
        System.out.println("Filter employees with a birthday in april");
        Predicate<Employee> isBirthdayInApril = (emp) -> emp.getBirthday().getMonthValue() == 4;
        List<Employee> employeesWithBirthdayInApril = employees.stream().filter(isBirthdayInApril).collect(Collectors.toList());
        for(Employee emp : employeesWithBirthdayInApril){
            System.out.println(emp.getName() + " " + emp.getBirthday());
        }
        //Group employees by birth month and display the count of employees in each group
        System.out.println("Group employees by birth month and display the count of employees in each group");
        Function<Employee, Integer> getBirthMonth = (emp) -> emp.getBirthday().getMonthValue();
        Map<Integer,Integer> numberOfEmpsPerBirthMonth = employees.stream().collect(Collectors.groupingBy(getBirthMonth, Collectors.summingInt(emp -> 1)));
        System.out.println(numberOfEmpsPerBirthMonth);
        // Without streams
        Map<Integer,Integer> numberOfEmpsPerBirthMonth2 = new java.util.HashMap<>();
        for(Employee emp : employees){
            int birthMonth = emp.getBirthday().getMonthValue();
            if(numberOfEmpsPerBirthMonth2.containsKey(birthMonth)){
                numberOfEmpsPerBirthMonth2.put(birthMonth, numberOfEmpsPerBirthMonth2.get(birthMonth) + 1);
            } else {
                numberOfEmpsPerBirthMonth2.put(birthMonth, 1);
            }
        }
        System.out.println(numberOfEmpsPerBirthMonth2);

        Predicate<Integer>[] isIs = new Predicate[10];
        for(int i = 0; i < 10; i++){
            int finalI = i;
            Predicate<Integer> isI = (num) -> {
                return num == finalI;
            };
            isIs[i] = isI;
        }
        for(int i = 0; i < 10; i++){
            System.out.println("TESTING::::::::::"+isIs[i].test(5));
        }

        System.out.println("List all employees with birthday in current month");
        Predicate<Employee> isBirthdayInCurrentMonth = (emp) -> emp.getBirthday().getMonthValue() == LocalDate.now().getMonthValue();
        List<Employee> employeesWithBirthdayInCurrentMonth = employees.stream().filter(isBirthdayInCurrentMonth).collect(Collectors.toList());
        for(Employee emp : employeesWithBirthdayInCurrentMonth){
            System.out.println(emp.getName() + " " + emp.getBirthday());
        }

        System.out.println("Ex 5: Method references");
        int[] y = {1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14, 15};
        MyTransformingType square = (x) -> x * x;
        int[] x = ce.map(a, square::transform);
        MyValidatingType isDivisibleBy3 = num -> num % 3 == 0;
        int[] z = ce.filter(y, isDivisibleBy3::test);









    }
    interface ArithmeticOperation {
        int perform(int a, int b);
    }
    public void ex1(){
        // - Addition
        ArithmeticOperation addition = (a, b) -> a + b;
        System.out.println(addition.perform(10, 20)); // 30
        //- Subtraction
        ArithmeticOperation subtraction = (a, b) -> a - b;
        System.out.println(subtraction.perform(10, 20)); // -10
        //- Multiplication
        ArithmeticOperation multiplication = (a, b) -> a * b;
        System.out.println(multiplication.perform(10, 20)); // 200
        //- Division
        ArithmeticOperation division = (a, b) -> a / b;
        System.out.println(division.perform(10, 20)); // 0 - Integer division since return value from interface is int
        //- Modulus
        ArithmeticOperation modulus = (a, b) -> a % b;
        System.out.println(modulus.perform(210, 20)); // 10
        //- Power
        ArithmeticOperation power = (a, b) -> (int) Math.pow(a, b);
        System.out.println(power.perform(2, 3)); // 8
    }

    // ex1.2
    public int operate(int a, int b, ArithmeticOperation op) {
        return op.perform(a, b);
    }
    public int[] operate(int[] a, int[] b, ArithmeticOperation op) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = op.perform(a[i], b[i]);
        }
        return result;
    }

    // ex2
    public interface MyTransformingType {
        int transform(int a);
    }
    public interface MyValidatingType {
        boolean test(int a);
    }

    public int[] map(int[] a, MyTransformingType op){
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = op.transform(a[i]);
        }
        return result;
    }
    public int[] filter(int[] a, MyValidatingType op){
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (op.test(a[i])){
                result[i] = a[i];
            }
        }
        return result;
    }
}
