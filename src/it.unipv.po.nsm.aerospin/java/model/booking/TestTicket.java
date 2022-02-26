package model.booking;

import com.google.zxing.WriterException;

import java.io.IOException;

public class TestTicket {
    public static void main(String[] args) throws IOException, WriterException {
        Ticket ticket = new Ticket("Antonino","Nocera","SUF","MXP","AES736","28/02/2022","8:00");
        ticket.generateTicket();
    }
}
