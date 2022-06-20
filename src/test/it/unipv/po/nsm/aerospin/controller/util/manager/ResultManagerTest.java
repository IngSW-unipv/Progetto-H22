package it.unipv.po.nsm.aerospin.controller.util.manager;

import controller.util.manager.ResultManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ResultManagerTest {

    ResultManager resultManager = new ResultManager();

    @Test
    public void testIsMinor() {
        LocalDate y15 = LocalDate.now().minusYears(15);
        LocalDate y16 = LocalDate.now().minusYears(16);
        LocalDate y17 = LocalDate.now().minusYears(17);

        assertTrue(resultManager.isMinor(y15));

        assertFalse(resultManager.isMinor(y16));
        assertFalse(resultManager.isMinor(y17));
    }

    @Test
    public void testFieldsCheck() {
        assertDoesNotThrow(() -> resultManager.fieldsCheck("aBc","cDe"));
        assertThrows(IllegalArgumentException.class, () -> resultManager.fieldsCheck("aaa","PIUDIQUINDICICAR"));
        assertThrows(IllegalArgumentException.class, () -> resultManager.fieldsCheck("PIUDIQUINDICICAR","aaa"));
        assertThrows(IllegalArgumentException.class, () -> resultManager.fieldsCheck("Caratterispeciali/","aaa"));
        //Fix
        //assertDoesNotThrow(() -> resultManager.fieldsCheck("aAa","COGNOME CON SPAZI"));

    }

}
