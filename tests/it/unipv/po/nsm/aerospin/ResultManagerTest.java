package it.unipv.po.nsm.aerospin;

import controller.util.manager.ResultManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ResultManagerTest {

    ResultManger resultManger = new ResultManager();

    @Test
    public void testIsMinor() {
        LocalDate y15 = LocalDate.now().minusYears(15);
        LocalDate y16 = LocalDate.now().minusYears(16);
        LocalDate y17 = LocalDate.now().minusYears(17);

        assertTrue(resultManger.isMinor(y15));

        assertFalse(resultManger.isMinor(y16));
        assertFalse(resultManger.isMinor(y17));
    }

    @Test
    public void testFieldsCheck() {
        assertDoesNotThrow(() -> resultManger.fieldsCheck("ab","cd"));
        assertThrows(IllegalArgumentException.class, () -> resultManger.fieldsCheck("a","cd"));

        assertThrows(IllegalArgumentException.class, () -> resultManger.fieldsCheck("abc", null));
        assertThrows(IllegalArgumentException.class, () -> resultManger.fieldsCheck(null, "abc"));
        assertThrows(IllegalArgumentException.class, () -> resultManger.fieldsCheck(null, null));
    }

}
