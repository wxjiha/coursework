import java.security.PrivateKey;
import java.security.PublicKey;

public class Alice {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public Alice(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public byte[] generateNonce() {
        return String.valueOf(System.currentTimeMillis()).getBytes();
    }

    public void authenticateWithBob(Server server, PublicKey bobPublicKey) throws Exception {
        // Generate nonce and encrypt with Bob's public key
        byte[] nonce = generateNonce();
        byte[] encryptedNonce = CryptographyHelper.encrypt(nonce, bobPublicKey);

        System.out.println("Alice sent encrypted nonce to Bob: " + CryptographyHelper.bytesToString(encryptedNonce));

        // Assume Bob responds with decrypted nonce + his own nonce
        // Logic to verify response goes here
    }
}
