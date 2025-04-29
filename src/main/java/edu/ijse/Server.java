package edu.ijse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try {
            System.out.println("Waiting for Client request");
            ServerSocket serverSocket = new ServerSocket(9806);
            Socket socket = serverSocket.accept();
            System.out.println("Client accepted");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
