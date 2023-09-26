public class Main {
    public static void main(String[] args) throws Exception {
        Server servidor = new Server(8888);
        servidor.start();

        while (true) {
            // Crie e inicie um novo cliente sempre que quiser
            Client cliente = new Client("localhost", 8888);
            cliente.start();
            
            // Aguarde um curto período antes de criar outro cliente
            Thread.sleep(1000); // 1 segundo
        }
    }
}
