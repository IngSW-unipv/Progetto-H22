package it.unipv.po.nsm.aerospin.controller.util.manager;

import controller.util.manager.ResultManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ResultManagerTest {

    private final ResultManager resultManager = new ResultManager();

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
        String[] notAllowedVal = {"a", "A", "PIUDIQUINDICICAR", "CaratteriSpeciali/$"};
        String[] allowedVal = {"aBc", "QUINDICICARATTE", "Campo con spazi"};

        for (String s : notAllowedVal){
            assertThrows(IllegalArgumentException.class, () -> resultManager.fieldsCheck(s,s));
        }
        for (String s : allowedVal){
            assertDoesNotThrow(()->resultManager.fieldsCheck(s,s));
        }
    }

}
