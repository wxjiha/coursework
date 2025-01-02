import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private Map<String, PublicKey> publicKeyDatabase;
    private PrivateKey privateKey;

    public Server(PrivateKey privateKey) {
        this.publicKeyDatabase = new HashMap<>();
        this.privateKey = privateKey;
    }

    public void registerUser(String userName, PublicKey publicKey) {
        publicKeyDatabase.put(userName, publicKey);
    }

    public byte[] getSignedPublicKey(String userName) throws Exception {
        PublicKey publicKey = publicKeyDatabase.get(userName);
        if (publicKey == null) {
            throw new Exception("User not found");
        }

        // Sign the public key
        return CryptographyHelper.sign(publicKey.getEncoded(), privateKey);
    }
}
