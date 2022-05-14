package controller.util;

import java.security.Key;

import static controller.util.Encryption.*;


public class EncryptionTest {

    public static void main(String[] args) throws Exception {

        Key key = generateKey();
        String encriptValue = encrypt("K3lL0t0ksfUBtQyRxR4D8A==",key);

        System.out.println(decrypt("frKUNTZX332l2ux5mGPwhA==",key));

    }

}
