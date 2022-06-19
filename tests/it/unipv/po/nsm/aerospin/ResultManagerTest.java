package it.unipv.po.nsm.aerospin;

import controller.util.manager.ResultManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ResultManagerTest {

    @Test
    public void testIsMinor() {
        LocalDate y15 = LocalDate.now().minusYears(15);
        LocalDate y16 = LocalDate.now().minusYears(16);
        LocalDate y17 = LocalDate.now().minusYears(17);

        assertTrue(ResultManager.isMinor(y15));

        assertFalse(ResultManager.isMinor(y16));
        assertFalse(ResultManager.isMinor(y17));
    }

    @Test
    public void testFieldsCheck() {
        assertDoesNotThrow(() -> ResultManager.fieldsCheck("ab","cd"));
        assertThrows(IllegalArgumentException.class, () -> ResultManager.fieldsCheck("a","cd"));

        assertThrows(IllegalArgumentException.class, () -> ResultManager.fieldsCheck("abc", null));
        assertThrows(IllegalArgumentException.class, () -> ResultManager.fieldsCheck(null, "abc"));
        assertThrows(IllegalArgumentException.class, () -> ResultManager.fieldsCheck(null, null));
    }

}
