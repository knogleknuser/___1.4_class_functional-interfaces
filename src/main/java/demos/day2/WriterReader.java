package demos.day2;

import java.io.*;
import java.lang.reflect.Type;

// Show how to Serialize an object and write it to a file
public class WriterReader<T> {
    public static void main(String[] args) {
        Book book = Book.builder()
                .title("The Lord of the Rings")
                .author("J.R.R. Tolkien")
                .publicationYear(1954)
                .rating(4.5)
                .numberOfPages(1178)
                .build();

        // Book implements Serializable
        WriterReader<Book> writerReader = new WriterReader<>();
        String fileName = writerReader.writeObjectToFile(book);
        Book bookFromFile = writerReader.getObjectFromFile(fileName);
        System.out.println("Book read from file"+bookFromFile);
    }

    // Write object to file and returns the filename:
    public String writeObjectToFile(T obj){
        Type typeOf = obj.getClass();
        String fileName = typeOf.toString();
        String fileSuffix = (java.time.LocalDateTime.now()).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        fileName = fileName.replace("class demos.day2.", "");
        fileName = fileName + fileSuffix;
        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(obj);
            out.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
    public T getObjectFromFile(String fileName){
        try{
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            T obj = (T) in.readObject();
            in.close();
            fis.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
