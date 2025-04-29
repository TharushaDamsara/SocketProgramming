package part2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        String clientMsg;
        String capitalizedMsg;

        System.out.println("Waiting for client...");
        ServerSocket serverSocket = new ServerSocket(9806);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("connection established");
            System.out.println("Getting Client Data");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            clientMsg = in.readLine();
            capitalizedMsg = clientMsg.toUpperCase()+"\n";
            out.writeBytes(capitalizedMsg);

        }
    }
}
