import java.security.*;
import java.util.Random;

public class Alice {
    private KeyPair keyPair;  // This will hold Alice's public and private key pair

    // Constructor: Initializes Alice's key pair when an object of Alice is created
    public Alice(KeyPair keyPair) {
        this.keyPair = keyPair;  // Assigns the provided key pair to Alice's keyPair
    }

    // Getter method for Alice's key pair
    public KeyPair getKeyPair() {
        return keyPair;  // Returns Alice's key pair
    }

    // Method to generate and send a nonce to Bob (used for authentication)
    public int sendNonce() {
        // Generates a random integer between 0 and 999 (nonce)
        int nonce = new Random().nextInt(1000);

        // Prints the nonce being sent by Alice to Bob
        System.out.println("Alice sends nonce to Bob: nA = " + nonce);

        // Returns the generated nonce so Bob can receive it
        return nonce;
    }
}
