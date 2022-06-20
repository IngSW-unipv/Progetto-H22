package model.booking.ticket;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * Classe che si occupa della creazione del QR Code relativo al biglietto.
 *
 * @author GruppoNoSuchMethod
 */
public class QRCode {
    /**
     * Metodo per la generazione di un QR Code.
     *
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O.
     * @throws WriterException Segnala un errore nel processo di scrittura di Maven/Hibernate.
     */
    public static void generate(String text) throws IOException, WriterException {
        File qrFile = new File("src/main/resources/GeneratedDoc/qr.png");
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300, hintMap);
        // Make the BufferedImage that are to hold the QRCode
        int matrixEdge = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixEdge, matrixEdge, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixEdge, matrixEdge);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixEdge; i++) {
            for (int j = 0; j < matrixEdge; j++) {
                if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, "png", qrFile);
    }
}
