import javax.crypto.Cipher;
import java.security.*;

public class CryptoUtils {

    // Method to encrypt a message using RSA encryption with a given public key
    public static byte[] encrypt(String message, PublicKey publicKey) throws Exception {
        // Create a Cipher instance for RSA encryption
        Cipher cipher = Cipher.getInstance("RSA");

        // Initialize the Cipher object for encryption using the provided public key
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Perform the encryption and return the encrypted message as a byte array
        return cipher.doFinal(message.getBytes());
    }

    // Method to decrypt a message using RSA decryption with a given private key
    public static String decrypt(byte[] encryptedMessage, PrivateKey privateKey) throws Exception {
        // Create a Cipher instance for RSA decryption
        Cipher cipher = Cipher.getInstance("RSA");

        // Initialize the Cipher object for decryption using the provided private key
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // Perform the decryption and return the decrypted message as a string
        return new String(cipher.doFinal(encryptedMessage));
    }
}
