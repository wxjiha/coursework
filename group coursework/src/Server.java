import java.security.*;

public class Server {
    // This will hold the server's public and private key pair
    private KeyPair keyPair;

    // Constructor: Initializes the server's key pair when a Server object is created
    public Server(KeyPair keyPair) {
        // Assigns the provided key pair to the server's keyPair
        this.keyPair = keyPair;
    }

    // Getter method for the server's key pair
    public KeyPair getKeyPair() {
        // Returns the server's key pair (both public and private keys)
        return keyPair;
    }

    // Method to sign a message using the server's private key
    public byte[] signMessage(String message, PrivateKey privateKey) throws Exception {
        // Create a Signature object using the SHA256withRSA algorithm for signing
        Signature signature = Signature.getInstance("SHA256withRSA");

        // Initialize the signature with the server's private key
        signature.initSign(privateKey);

        // Update the signature with the message to be signed
        signature.update(message.getBytes());

        // Generate and return the signed message as a byte array
        return signature.sign();
    }

    // Method to verify the signature of a message using the server's public key
    public boolean verifySignature(String message, byte[] signedMessage, PublicKey publicKey) throws Exception {
        // Create a Signature object using the SHA256withRSA algorithm for verification
        Signature signature = Signature.getInstance("SHA256withRSA");

        // Initialize the signature object with the server's public key for verification
        signature.initVerify(publicKey);

        // Update the signature with the message to verify
        signature.update(message.getBytes());

        // Verify the signed message with the original message and the public key
        return signature.verify(signedMessage);  // Returns true if the signature is valid, false otherwise
    }
}
