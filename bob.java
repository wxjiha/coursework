import java.security.PrivateKey;
import java.security.PublicKey;

public class Bob {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public Bob(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public void handleRequest(byte[] encryptedNonceFromAlice) throws Exception {
        // Decrypt the nonce
        byte[] decryptedNonce = CryptographyHelper.decrypt(encryptedNonceFromAlice, privateKey);

        System.out.println("Bob decrypted Alice's nonce: " + new String(decryptedNonce));

        // Respond to Alice with both nonces
        // Logic goes here
    }
}
