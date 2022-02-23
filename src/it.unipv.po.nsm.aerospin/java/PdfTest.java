import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class PdfTest {
    public static void main(String[] args) {

        try {
            String file_name = "C:\\Users\\hamza\\Desktop\\Progetto-H22\\src\\" +
                    "it.unipv.po.nsm.aerospin\\resources\\GeneratedPDF\\first.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();
            Paragraph paragraph = new Paragraph("ileqwrhcfb2ilrb2ip3rfbpi23rbgpu2io3rgbpui23rbguib23rv");
            document.add(paragraph);
            document.add(Image.getInstance("C:\\Users\\hamza\\Desktop\\Progetto-H22\\src\\it.unipv.po.nsm.aerospin\\resources\\img\\icon.png"));





            document.close();
            System.out.println("finished");

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
