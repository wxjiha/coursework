import java.security.*;

public class AuthenticationProtocol {
    private static KeyPair aliceKeyPair, bobKeyPair, serverKeyPair;

    public static void main(String[] args) throws Exception {
        // Step 1: Generate key pairs
        generateKeyPairs();

        Alice alice = new Alice(aliceKeyPair);
        Bob bob = new Bob(bobKeyPair);
        Server server = new Server(serverKeyPair);

        // Step 1: Alice requests Bob's public key from the server
        System.out.println("Step 1: Alice requests Bob's public key from the server.");
        byte[] signedKeyBob = server.signMessage("Bob's public key", server.getKeyPair().getPrivate());
        System.out.println("Server sends signed Bob's public key to Alice: " + byteArrayToBase64(signedKeyBob));

        // Step 2: Alice sends a nonce to Bob, encrypted with Bob's public key
        int nonceA = alice.sendNonce();
        System.out.println("Step 3: Alice encrypts nonce with Bob's public key.");
        byte[] encryptedNonceA = CryptoUtils.encrypt(String.valueOf(nonceA), bob.getKeyPair().getPublic());
        System.out.println("Message: A → B : (nA, A).KB : " + byteArrayToBase64(encryptedNonceA));

        // Step 3: Bob requests Alice's public key from the server
        System.out.println("Step 4: Bob requests Alice's public key from the server.");
        byte[] signedKeyAlice = server.signMessage("Alice's public key", server.getKeyPair().getPrivate());
        System.out.println("Server sends signed Alice's public key to Bob: " + byteArrayToBase64(signedKeyAlice));

        // Step 4: Bob sends nonce and Alice's nonce back to Alice
        int nonceB = bob.sendNonce();
        System.out.println("Step 6: Bob sends both nonces back to Alice.");
        String message = nonceA + "," + nonceB;
        byte[] encryptedNonces = CryptoUtils.encrypt(message, alice.getKeyPair().getPublic());
        System.out.println("Message: B → A : (nA, nB).KA : " + byteArrayToBase64(encryptedNonces));

        // Step 5: Alice sends Bob's nonce back to Bob
        System.out.println("Step 7: Alice sends Bob's nonce back.");
        byte[] encryptedNonceB = CryptoUtils.encrypt(String.valueOf(nonceB), bob.getKeyPair().getPublic());
        System.out.println("Message: A → B : (nB).KB : " + byteArrayToBase64(encryptedNonceB));
    }

    // Generate key pairs for Alice, Bob, and Server
    private static void generateKeyPairs() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        aliceKeyPair = keyPairGenerator.generateKeyPair();
        bobKeyPair = keyPairGenerator.generateKeyPair();
        serverKeyPair = keyPairGenerator.generateKeyPair();
    }

    // Utility function to convert byte[] to a Base64-encoded String
    private static String byteArrayToBase64(byte[] byteArray) {
        // Convert byte[] to Base64 string
        return java.util.Base64.getEncoder().encodeToString(byteArray);
    }
}



