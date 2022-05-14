package controller.util;

import java.security.Key;

import static controller.util.Encryption.*;


public class EncryptionTest {

    public static void main(String args[]) throws Exception {

        Key key = generateKey();
        String encriptValue = encrypt("K3lL0t0ksfUBtQyRxR4D8A==",key);

        decrypt("K3lL0t0ksfUBtQyRxR4D8A==",key);

    }

}
