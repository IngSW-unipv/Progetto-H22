package model.persistence;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import model.persistence.entity.Airport;
import model.persistence.entity.Book;
import model.persistence.service.AirportService;
import model.persistence.service.BookService;

import java.io.FileOutputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        List<Book> books = bookService.findAll();



       try {
            String file_name = "C:\\Users\\hamza\\Desktop\\Progetto-H22\\src\\" +
                    "it.unipv.po.nsm.aerospin\\resources\\GeneratedPDF\\first.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            Image img = Image.getInstance("src/it.unipv.po.nsm.aerospin/resources/img/icon.png");
            img.setDpi(10 ,10);
            document.add(img);

            Paragraph paragraph = new Paragraph("Elenco Libri Aggiunti");
            document.add(paragraph);

            for (Book b : books) {
                Paragraph para = new Paragraph(b.toString());
                document.add(para);
                document.add(new Paragraph("  "));

            }




            document.close();
            System.out.println("finished");

        }catch (Exception e){
            System.out.println(e);
        }









        AirportService airportService = new AirportService();
        /*Book book1 = new Book();
        book1.setAuthor("Davide");
        book1.setId(10);
        book1.setTitle("Basi Di dati 2018");
        bookService.persist(book1);*/
        /*List<Airport> airports = (List<Airport>) airportService.findByName("Kennedy");
        for (Airport a : airports) {
            System.out.println(a);
        }*/


    }
}
