package model.persistence;

import java.security.Key;

import static model.persistence.Encryption.*;


public class EncryptionTest {

    public static void main(String[] args) throws Exception {

        Key key = generateKey();
        String encriptValue = encrypt("K3lL0t0ksfUBtQyRxR4D8A==",key);

        System.out.println(decrypt("frKUNTZX332l2ux5mGPwhA==",key));

    }

}
