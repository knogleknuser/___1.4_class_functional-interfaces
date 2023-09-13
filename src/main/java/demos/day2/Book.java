package demos.day2;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
public class Book implements java.io.Serializable{
    String title;
    String author;
    int publicationYear;
    double rating;
    int numberOfPages;
}
