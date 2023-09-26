import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class ClientHandler extends Thread {
    private Socket clienteSocket;

    public ClientHandler(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }

    public void run() {
        try {
            InputStream entrada = clienteSocket.getInputStream();
            OutputStream saida = clienteSocket.getOutputStream();

            while (true) {
                byte[] buffer = new byte[1024];
                int bytesRead = entrada.read(buffer);
                if (bytesRead == -1) {
                    break; // Encerra a comunicação com o cliente
                }

                String mensagemCliente = new String(buffer, 0, bytesRead, Charset.forName("UTF-8"));
                System.out.println("Cliente " + clienteSocket.hashCode() + " >> " + mensagemCliente);

                // Responder ao cliente (resposta em maiúsculas)
                String resposta = mensagemCliente.toUpperCase();
                byte[] dados = resposta.getBytes(Charset.forName("UTF-8"));
                saida.write(dados);
                saida.flush();
            }
        } catch (Exception e) {
            System.out.println("Cliente " + clienteSocket.hashCode() + " desconectado: " + e.getMessage());
        } finally {
            try {
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
