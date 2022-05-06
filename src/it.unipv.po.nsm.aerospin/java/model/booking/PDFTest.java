package model.booking;



import com.google.zxing.WriterException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import model.booking.GenerateQRCode;


import java.io.File;
import java.io.IOException;

public class PDFTest {


    public static void main(String[] args) throws IOException, WriterException {
        String name = "Boarding Pass Hamza";
        File file = new File("src/it.unipv.po.nsm.aerospin/resources/GeneratedPDF/BoardingPassTemplate.pdf");
        PDDocument document =  PDDocument.load(file);
        PDPage page = document.getPage(0);
        PDFont font = PDType0Font.load(document, new File("src/it.unipv.po.nsm.aerospin/resources/fonts/Roboto-Thin.ttf"));
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
        contentStream.beginText();
        contentStream.setFont(font,25);
        contentStream.setNonStrokingColor(255,255,255);
        contentStream.newLineAtOffset(113, 215);
        contentStream.drawString("Hamza");
        contentStream.newLineAtOffset(250,0);
        contentStream.drawString("Abbad");
        contentStream.setFont(font,70);
        contentStream.newLineAtOffset(-275,275);
        contentStream.drawString("DEL");
        contentStream.newLineAtOffset(300,0);
        contentStream.drawString("SFO");
        contentStream.setFont(font,25);
        contentStream.newLineAtOffset(-275,-190);
        contentStream.drawString("AES735");
        contentStream.newLineAtOffset(125,0);
        contentStream.drawString("17/08/2022");
        contentStream.newLineAtOffset(175,0);
        contentStream.drawString("14:00");
        contentStream.endText();


        GenerateQRCode qr = new GenerateQRCode("ciaoooo");
        qr.generate();

        PDImageXObject pdImage = PDImageXObject.createFromFile("src/it.unipv.po.nsm.aerospin/resources/GeneratedQr/qr.png", document);
        contentStream.drawImage(pdImage, 50, 50);
        System.out.println("Image inserted");
        contentStream.close();
        document.save("src/it.unipv.po.nsm.aerospin/resources/GeneratedPDF/" + name + ".pdf");
        document.close();



    }
}





