import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyManager {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    // Constructor to generate the RSA key pair
    public KeyManager() throws Exception {
        // Initialize the RSA KeyPairGenerator
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // Key size: 2048 bits for strong encryption

        // Generate the key pair
        KeyPair keyPair = keyGen.generateKeyPair();

        // Assign the keys
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
    }

    // Getter for the public key
    public PublicKey getPublicKey() {
        return publicKey;
    }

    // Getter for the private key
    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
