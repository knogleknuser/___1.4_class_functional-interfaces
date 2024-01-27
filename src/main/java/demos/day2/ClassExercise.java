package demos.day2;

import lombok.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassExercise {
    public static void main(String[] args) {
        ClassExercise ce = new ClassExercise();

        // streams and lambdas
        List<Book> books = ce.createBooks(20);
        System.out.println(books);

        // ex1: Find average rating of all books
        double averageRating = books.stream().mapToDouble(Book::getRating).average().getAsDouble();
        System.out.println("Average rating: " + averageRating);

        //Find all books published after 2000
        List<Book> booksAfter2000 = books.stream().filter(book -> book.getPublicationYear() > 2000).toList();
        System.out.println("Books after 2000: " + booksAfter2000);

        // Sort books by rating in descendin order
        List<Book> sortedBooks = books.stream().sorted((b1,b2)-> (int) (b2.getRating() - b1.getRating())).toList();
        System.out.println("Sorted books: " + sortedBooks);

        //Find book with the highest rating
        Book highestRated = books.stream().max((b1,b2)-> (int) (b1.getRating() - b2.getRating())).get();
        System.out.println("Highest rated book: " + highestRated);

        //Group book by author
        Map<String,Double> booksByAuthorRating = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.averagingDouble(Book::getRating)));
        System.out.println("Books by author: " + booksByAuthorRating);

        // Calculate total number of pages
        int totalPages = books.stream().map(Book::getNumberOfPages).reduce(0, (subTotal, element) -> subTotal + element);
        System.out.println("Total pages: " + totalPages);

        // Collectors API
        List<Transaction> transactions = List.of(
                new Transaction(1, 100.0, "USD"),
                new Transaction(2, 150.0, "EUR"),
                new Transaction(3, 400.0, "USD"),
                new Transaction(4, 75.0, "GBP"),
                new Transaction(5, 120.0, "EUR"),
                new Transaction(6, 300.0, "GBP")
        );

        // Calculate the total sum of all transaction amounts
        double totalSum = transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
        System.out.println("Total sum of all transactions: " + totalSum);

        // Group transactions by currency and calculate sum for each currency
        Map<String,Double> map = transactions
                .stream()
                .collect(Collectors
                        .groupingBy(
                                Transaction::getCurrency,
                                Collectors.summingDouble(Transaction::getAmount)
                        )
                );
        System.out.println("Sum of transactions by currency: " + map);

        // Find the highest transaction amount
        double highestTransactionAmount = transactions
                .stream()
                .mapToDouble(Transaction::getAmount)
                .max()
                .getAsDouble();
        System.out.println("Highest transaction amount: " + highestTransactionAmount);

        // Find the average transaction amount
        double averageTransactionAmount = transactions
                .stream()
                .mapToDouble(Transaction::getAmount)
                .average()
                .getAsDouble();
        System.out.println("Average transaction amount: " + averageTransactionAmount);


        // GENERICS
        // ex1: Create a generic class that can store any type of data
        Demo demo = new Demo("John", 20);

        DataStorage<Demo> memoryStorage = new MemoryStorage<>();
        memoryStorage.store(demo);
        System.out.println("From MemoryStorage: "+memoryStorage.retrieve("not used"));

        DataStorage<Demo> fileStorage = new FileStorage<>();
        String fileName = fileStorage.store(demo);
        System.out.println("From FileStorage: "+fileStorage.retrieve(fileName));
    }
    // streams and lambdas
    public List<Book> createBooks(int numberOfBooks) {
        List<String> authors = List.of("J.K. Rowling", "J.R.R. Tolkien", "George R.R. Martin", "Stephen King", "Dan Brown", "Agatha Christie", "Terry Pratchett", "J.D. Salinger", "James Patterson", "Douglas Adams", "Roald Dahl", "Johanna Spyri");
        List<String> titles = List.of("Harry Potter and the Philosopher's Stone", "Harry Potter and the Chamber of Secrets", "Harry Potter and the Prisoner of Azkaban", "Harry Potter and the Goblet of Fire", "Harry Potter and the Order of the Phoenix", "Harry Potter and the Half-Blood Prince", "Harry Potter and the Deathly Hallows", "The Hobbit", "The Fellowship of the Ring");
        List<Integer> years = List.of(1910, 1920, 1930, 1940, 1950, 1960, 1970, 1980,1990, 2000, 2010, 2020);
        List<Double> ratings = List.of(1.0, 2.0, 3.0, 4.0, 5.0);

        List<Book> books = new ArrayList<>();
        for(int i = 0; i < numberOfBooks; i++){
            String author = authors.get((int) (Math.random() * authors.size()));
            String title = titles.get((int) (Math.random() * titles.size()));
            int year = years.get((int) (Math.random() * years.size()));
            double rating = ratings.get((int) (Math.random() * ratings.size()));
            int numberOfPages = (int) (Math.random() * 1000);
            Book book = new Book(title, author, year, rating, numberOfPages);
            books.add(book);
        }
        return books;
    }

    // Collectors API
    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    private static class Transaction{
        private int id;
        private double amount;
        private String currency;
    }
    // GENERICS
    static interface DataStorage<T extends Serializable> {
        String store(T data);
        T retrieve(String source);
    }
    static class MemoryStorage<T extends Serializable> implements DataStorage<T> { // Bounded type parameter T extends Serializable (That is the syntax even though Serializable is an interface)
        private T data;
        @Override
        public String store(T data) {
            this.data = data;
            return "Memory";
        }
        @Override
        public T retrieve(String source) { // source is not used
            return data;
        }
    }
    static class FileStorage<T extends Serializable> implements DataStorage<T> {
        // T must be serializable!
        WriterReader<T> writerReader = new WriterReader<>();
        @Override
        public String store(T data) {
            String fileName = writerReader.writeObjectToFile(data);
            return fileName;
        }
        @Override
        public T retrieve(String source) {
            String fileName = source;
            return writerReader.getObjectFromFile(fileName);
        }

    }
    static class Demo implements Serializable {
        private String name;
        private int age;
        public Demo(String name, int age) {
            this.name = name;
            this.age = age;
        }
        @Override
        public String toString() {
            return "Demo{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
