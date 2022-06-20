package model.booking.ticket;

import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Classe che si occupa dell'invio via E-Mail di un biglietto.
 *
 * @author GruppoNoSuchMethod
 */
public class TicketMail {
    private static final String from = "h22aerospin@gmail.com";     // Sender's email ID
    private static final String username = from;
    private static final String password = "hiufwebvzteaqxrz";
    private static final String host = "smtp.gmail.com";            // We are sending through smtp.gmail.com
    private String subject;
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Metodo per la generazione e l'invio della e-mail relativa alla prenotazione.
     *
     * @param to Destinatario e-mail.
     * @param filePath Indirizzo in memoria del file da inviare.
     * @throws RuntimeException Segnala un errore durante l'esecuzione del processo.
     */
    public void send(String to, String filePath) throws RuntimeException {
        Properties props = new Properties();
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            // Create a default MimeMessage object.
                Message message = new MimeMessage(session);
            // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));
            // Set Subject: header field
                message.setSubject(subject);
            // Create the message part
                BodyPart messageBodyPart = new MimeBodyPart();
            // Now set the actual message
                messageBodyPart.setText(text);
            // Create a multipart message
                Multipart multipart = new MimeMultipart();
            // Set text message part
                multipart.addBodyPart(messageBodyPart);

            if(filePath != null) {
                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
                String fileName = "BoardingPass.pdf";
                DataSource source = new FileDataSource(filePath);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(fileName);
                multipart.addBodyPart(messageBodyPart);
            }

            // Send the complete message parts
                message.setContent(multipart);
            // Send message
                Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
