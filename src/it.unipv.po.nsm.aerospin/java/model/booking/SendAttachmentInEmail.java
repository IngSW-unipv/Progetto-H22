package model.booking;

    import java.util.Properties;
    import javax.activation.*;
    import javax.mail.*;
    import javax.mail.internet.InternetAddress;
    import javax.mail.internet.MimeBodyPart;
    import javax.mail.internet.MimeMessage;
    import javax.mail.internet.MimeMultipart;

//public class MailConfirm {
//
//    public static void main(String[] args) {
//
//        final String username = "h22aerospin@gmail.com";
//        final String password = "kbeoqsm7";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", true);
//        props.put("mail.smtp.starttls.enable", true);
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//
//            Message message = new MimeMessage(session);
////            message.setFrom(new InternetAddress("from.mail.id@gmail.com"));
//            message.setFrom(new InternetAddress("h22aerospin@gmail.com"));
////            message.setRecipients(Message.RecipientType.TO,
////                    InternetAddress.parse("to.mail.id@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("dvdmorano@gmail.com"));
//            message.setSubject("Il tuo Biglietto");
//            message.setText("PFA");
//
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//
//            Multipart multipart = new MimeMultipart();
//
//            String file = "src/it.unipv.po.nsm.aerospin/resources/GeneratedPDF/BoardingPassTemplate.pdf";
//            String fileName = "YourTicket.pdf";
//            DataSource source = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(fileName);
//            multipart.addBodyPart(messageBodyPart);
//
//            message.setContent(multipart);
//
//            System.out.println("Sending");
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//}

public class SendAttachmentInEmail {
    public static void main(String[] args) {
        // Recipient's email ID needs to be mentioned.
        String to = "dvdmorano@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "h22aerospin@gmail.com";

        final String username = "h22aerospin@gmail.com";//change accordingly
        final String password = "kbeoqsm7";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();

//        props.put("mail.smtp.EnableSSL.enable","true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        //or 587

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
            message.setSubject("Il Tuo Biglietto");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "src/it.unipv.po.nsm.aerospin/resources/GeneratedPDF/BoardingPassTemplate.pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);
            System.out.println("Sent prova message successfully....");
            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}