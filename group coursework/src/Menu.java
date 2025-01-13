import java.security.*;
import java.util.Base64;

public class Menu {

    public static void main(String[] args) throws Exception {
        // Step 1: Generate key pairs for Alice, Bob, and the Server
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        KeyPair aliceKeyPair = keyPairGenerator.generateKeyPair();
        KeyPair bobKeyPair = keyPairGenerator.generateKeyPair();
        KeyPair serverKeyPair = keyPairGenerator.generateKeyPair();

        // Initialize Alice, Bob, and Server objects
        Alice alice = new Alice(aliceKeyPair);
        Bob bob = new Bob(bobKeyPair);
        Server server = new Server(serverKeyPair);

        // Step 1: Alice requests Bob's public key from the Server
        System.out.println("Step 1: Alice requests Bob's public key from the server.");
        byte[] signedBobPublicKey = server.signMessage("Bob's public key", server.getKeyPair().getPrivate());
        System.out.println("Message: S → A : (KB, B).kS " + byteArrayToBase64(signedBobPublicKey));

        // Step 2: Alice sends a nonce to Bob
        int nonceA = alice.sendNonce();
        System.out.println("Step 2: Alice sends nonce to Bob: nA = " + nonceA);
        byte[] encryptedNonceA = CryptoUtils.encrypt(String.valueOf(nonceA), bob.getKeyPair().getPublic());
        System.out.println("Message: A → B : (nA, A).KB : " + byteArrayToBase64(encryptedNonceA));

        // Step 3: Bob requests Alice's public key from the Server
        System.out.println("Step 3: Bob requests Alice's public key from the server.");
        byte[] signedAlicePublicKey = server.signMessage("Alice's public key", server.getKeyPair().getPrivate());
        System.out.println("Message: S → B : (KA, A).kS " + byteArrayToBase64(signedAlicePublicKey));

        // Step 4: Bob sends his nonce and Alice's nonce back to Alice
        int nonceB = bob.sendNonce();
        System.out.println("Step 4: Bob sends both nonces back to Alice.");
        String noncesMessage = nonceA + "," + nonceB;
        byte[] encryptedNonces = CryptoUtils.encrypt(noncesMessage, alice.getKeyPair().getPublic());
        System.out.println("Message: B → A : (nA, nB).KA : " + byteArrayToBase64(encryptedNonces));

        // Step 5: Alice sends Bob's nonce back to Bob
        System.out.println("Step 5: Alice sends Bob's nonce back.");
        byte[] encryptedNonceB = CryptoUtils.encrypt(String.valueOf(nonceB), bob.getKeyPair().getPublic());
        System.out.println("Message: A → B : (nB).KB : " + byteArrayToBase64(encryptedNonceB));
    }

    // Utility function to convert byte[] to a Base64-encoded String
    private static String byteArrayToBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);  // Convert byte[] to Base64 string
    }
}

