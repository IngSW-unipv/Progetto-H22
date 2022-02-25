package model.persistence;


import model.persistence.entity.Airport;
import model.persistence.entity.Book;
import model.persistence.service.AirportService;
import model.persistence.service.BookService;
import org.w3c.dom.Document;


import java.io.FileOutputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        List<Book> books = bookService.findAll();
        String MONSERRAT = "src/it.unipv.po.nsm.aerospin/resources/fonts/Montserrat-VariableFont_wght.ttf";



        AirportService airportService = new AirportService();

        List<Airport> airports = (List<Airport>) airportService.findByCity("Valencia");
        for (Airport a : airports) {
            System.out.println(a);
        }


    }
}
