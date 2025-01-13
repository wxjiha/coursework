import java.security.*;
import java.util.Random;

public class Bob {
    // This will hold Bob's public and private key pair
    private KeyPair keyPair;

    // Constructor: Initializes Bob's key pair when an object of Bob is created
    public Bob(KeyPair keyPair) {
        // Assigns the provided key pair to Bob's keyPair
        this.keyPair = keyPair;
    }

    // Getter method for Bob's key pair
    public KeyPair getKeyPair() {
        return keyPair;  // Returns Bob's key pair
    }

    // Method to generate and send a nonce to Alice (used for authentication)
    public int sendNonce() {
        // Generates a random integer between 0 and 999 (nonce)
        int nonce = new Random().nextInt(1000);

        // Prints the nonce being sent by Bob to Alice
        System.out.println("Bob sends nonce to Alice: nB = " + nonce);

        // Returns the generated nonce so Alice can receive it
        return nonce;
    }
}





