import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        int porta = 8888;
        List<ClientHandler> clientes = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Servidor iniciado na porta: " + porta);

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Novo cliente conectado!");

                ClientHandler cliente = new ClientHandler(clienteSocket);
                clientes.add(cliente);
                cliente.start();
            }
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
}
