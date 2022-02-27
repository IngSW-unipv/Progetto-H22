package model.persistence;


import model.persistence.service.AirportService;


import java.util.List;

public class Test {
    public static void main(String[] args) {
//        BookService bookService = new BookService();
//        List<Book> books = bookService.findAll();
        String MONSERRAT = "src/it.unipv.po.nsm.aerospin/resources/fonts/Montserrat-VariableFont_wght.ttf";



        AirportService airportService = new AirportService();

        List<String> airports = airportService.findByParam("airportName");
        for (String a : airports) {
            System.out.println(a);
        }


    }
}
