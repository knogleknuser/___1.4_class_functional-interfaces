package demos.day1;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class Employee {
    String name;
    double salary;
    LocalDate birthday;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}
