package model.booking.ticket;

import com.google.zxing.WriterException;
import model.persistence.entity.Booking;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe che si occupa della creazione e della gestione di un biglietto.
 *
 * @author GruppoNoSuchMethod
 */
public class Ticket {
    private final String bookingId;
    private final String name;
    private final String surname;
    private final String iataDep;
    private final String iataArr;
    private final String cityDep;
    private final String cityArr;
    private final String flightNumber;
    private final String date;
    private final String depTime;
    private final String arrTime;
    private final String path =
            "src/it.unipv.po.nsm.aerospin/resources/GeneratedDoc/BoardingPass.pdf";


    /**
     * Costruttore dell'oggetto biglietto.
     *
     * @param booking Viene passata la prenotazione.
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O.
     */
    public Ticket(Booking booking) throws IOException {
        this.bookingId = "APN" + booking.getId();
        this.name = booking.getPassengerById().getName();
        this.surname = booking.getPassengerById().getSurname();
        this.iataDep = booking.getFlightById().getRouteById().getAirportDep().getIata();
        this.iataArr = booking.getFlightById().getRouteById().getAirportArr().getIata();
        this.cityDep = booking.getFlightById().getRouteById().getAirportDep().getCity();
        this.cityArr = booking.getFlightById().getRouteById().getAirportArr().getCity();
        this.flightNumber = booking.getFlightById().getFlightNumber();
        this.date = booking.getFlightById().getScheduledDate().toString();
        this.depTime = booking.getFlightById().getScheduledTime().toString();
        this.arrTime = booking.getFlightById().getArrivalTime().toString();
        generateTicket();
    }

    /**
     * Metodo per la generazione di un biglietto.
     *
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O.
     */
    private void generateTicket() throws IOException {
        File file = new File(getClass().getClassLoader().getResource("GeneratedDoc/BoardingPassTemplate.pdf").getFile());
        PDDocument doc =  PDDocument.load(file);
        PDPage page = doc.getPage(0);
        PDFont font = PDType0Font.load(doc,
                new File(getClass().getClassLoader().getResource("fonts/Kollektif.ttf").getFile()));
        PDFont bold = PDType0Font.load(doc,
                new File(getClass().getClassLoader().getResource("fonts/Kollektif-Bold.ttf").getFile()));
        PDPageContentStream contentStream = new PDPageContentStream(
                doc, page, PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setFont(font,75);
        contentStream.setNonStrokingColor(new Color(0,92,185));
        contentStream.newLineAtOffset(205,942);
            contentStream.showText(iataDep);
        contentStream.newLineAtOffset(265, 0);
            contentStream.showText(iataArr);

        contentStream.setFont(font,20);
        contentStream.setNonStrokingColor(Color.black);
        contentStream.newLineAtOffset(-252, -50);
            contentStream.showText(cityDep);
        contentStream.newLineAtOffset(285, 0);
            contentStream.showText(cityArr);

        contentStream.setFont(bold,22);
        contentStream.newLineAtOffset(-150,-140);
            contentStream.showText(surname + " " + name);
        contentStream.newLineAtOffset(-102,-90);
            contentStream.showText(flightNumber);
        contentStream.newLineAtOffset(193,0);
            contentStream.showText(date);
        contentStream.newLineAtOffset(-193,-85);
            contentStream.showText(depTime);
        contentStream.newLineAtOffset(193,0);
            contentStream.showText(arrTime);
        contentStream.endText();
        try {
            QRCode.generate(bookingId + " " + flightNumber);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        PDImageXObject pdImage = PDImageXObject.createFromFile(
                getClass().getClassLoader().getResource("GeneratedDoc/qr.png").getFile(), doc);
        contentStream.drawImage(pdImage, 255, 250);
        contentStream.close();
        doc.save(path);
        doc.close();
    }

    /**
     * Metodo per ottenere un indirizzo di memoria.
     *
     * @return Indirizzo.
     */
    public String getPath(){
        return path;
    }
}
