import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 8888; // Porta do servidor

        try {
            Socket clienteSocket = new Socket(serverAddress, serverPort);
            InputStream entrada = clienteSocket.getInputStream();
            OutputStream saida = clienteSocket.getOutputStream();
            int clienteId = new Random().nextInt(1000); // Identificador único para o cliente

            System.out.println("Cliente #" + clienteId + " conectado ao Servidor na porta " + serverPort);

            while (true) {
                Thread.sleep(10000);
                String mensagem = "Cliente #" + clienteId + ": Olá server";
                byte[] dados = mensagem.getBytes(Charset.forName("UTF-8"));
                saida.write(dados);
                saida.flush();
                System.out.println("Cliente #" + clienteId + " enviou mensagem!");

                byte[] buffer = new byte[1024];
                int bytesRead = entrada.read(buffer);
                if (bytesRead == -1) {
                    break; // Encerra a comunicação com o servidor
                }

                String mensagemServidor = new String(buffer, 0, bytesRead, Charset.forName("UTF-8"));
                System.out.println("SERVIDOR >> " + mensagemServidor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
