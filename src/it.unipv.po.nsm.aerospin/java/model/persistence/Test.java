package model.persistence;

import model.persistence.entity.Book;
import model.persistence.service.BookService;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        /*Book book1 = new Book();
        book1.setAuthor("Davide");
        book1.setId(10);
        book1.setTitle("Basi Di dati 2018");
        bookService.persist(book1);*/
        List<Book> books2 = bookService.findAll();
        System.out.println("Books found are :");
        for (Book b : books2) {
            System.out.println("-" + b.toString());
        }

    }
}
