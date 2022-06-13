package model.booking;

import com.google.zxing.WriterException;
import model.persistence.entity.Booking;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
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
    private final String departure;
    private final String arrival;
    private final String flightNumber;
    private final String date;
    private final String depTime;
    private final String arrTime;
    private final String path =
            "src/it.unipv.po.nsm.aerospin/resources/GeneratedPDF/BoardingPass%s.pdf";

//    /**
//     * Costruttore dell'oggetto biglietto.
//     *
//     * @param name Nome
//     * @param surname Cognome
//     * @param departure Partenza
//     * @param arrival Arrivo
//     * @param flightNumber Numero del Volo
//     * @param date Data
//     * @param depTime Ora Partenza
//     * @param arrTime Ora Arrivo
//     */
    public Ticket(Booking booking) throws IOException {
        this.bookingId = booking.getId();
        this.name = booking.getPassengerById().getName();
        this.surname = booking.getPassengerById().getSurname();
        this.departure = booking.getFlightById().getRouteById().getAirportDep().getIata();
        this.arrival = booking.getFlightById().getRouteById().getAirportArr().getIata();
        this.flightNumber = booking.getFlightById().getFlightNumber();
        this.date = booking.getFlightById().getScheduledDate().toString();
        this.depTime = booking.getFlightById().getScheduledTime().toString();
        this.arrTime = booking.getFlightById().getArrivalTime().toString();
        generateTicket();
    }

    private void generateTicket() throws IOException {
        File file = new File(
                "src/it.unipv.po.nsm.aerospin/resources/GeneratedPDF/BoardingPassTemplate.pdf");
        PDDocument document =  PDDocument.load(file);
        PDPage page = document.getPage(0);
        PDFont font = PDType0Font.load(document,
                new File("src/it.unipv.po.nsm.aerospin/resources/fonts/Roboto-Thin.ttf"));
        PDPageContentStream contentStream = new PDPageContentStream(
                document, page, PDPageContentStream.AppendMode.APPEND, true, true);
        contentStream.beginText();
        contentStream.setFont(font,25);
        contentStream.setNonStrokingColor(255,255,255);
        contentStream.newLineAtOffset(113, 215);
        contentStream.drawString(name);
        contentStream.newLineAtOffset(250,0);
        contentStream.drawString(surname);
        contentStream.setFont(font,70);
        contentStream.newLineAtOffset(-275,275);
        contentStream.drawString(departure);
        contentStream.newLineAtOffset(300,0);
        contentStream.drawString(arrival);
        contentStream.setFont(font,25);
        contentStream.newLineAtOffset(-275,-190);
        contentStream.drawString(flightNumber);
        contentStream.newLineAtOffset(125,0);
        contentStream.drawString(date);
        contentStream.newLineAtOffset(175,0);
        contentStream.drawString(depTime);
        contentStream.newLineAtOffset(200,0);
        contentStream.drawString(arrTime);
        contentStream.endText();
        //TODO VEDERE STYLE TICKET

        GenerateQRCode qr = new GenerateQRCode(flightNumber+ "" + name + "" + surname);
        try {
            qr.generate();
        } catch (WriterException e) {
            e.printStackTrace();
        }

        PDImageXObject pdImage = PDImageXObject.createFromFile("src/it.unipv.po.nsm.aerospin/resources/GeneratedQr/qr.png", document);
        contentStream.drawImage(pdImage, 50, 50);
        System.out.println("Image inserted");
        contentStream.close();
        document.save(String.format(path, bookingId));
        document.close();
    }

    public String getPath(){
        return String.format(path, bookingId);
    }
}
