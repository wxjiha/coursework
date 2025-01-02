public class main {
    public static void main(String[] args) throws Exception {
        // Key and server setup
        KeyManager aliceKeys = new KeyManager();
        KeyManager bobKeys = new KeyManager();
        KeyManager serverKeys = new KeyManager();

        Server server = new Server(serverKeys.getPrivateKey());
        server.registerUser("Alice", aliceKeys.getPublicKey());
        server.registerUser("Bob", bobKeys.getPublicKey());

        // Simulate protocol
        Alice alice = new Alice(aliceKeys.getPublicKey(), aliceKeys.getPrivateKey());
        Bob bob = new Bob(bobKeys.getPublicKey(), bobKeys.getPrivateKey());

        alice.authenticateWithBob(server, bobKeys.getPublicKey());
    }
}
