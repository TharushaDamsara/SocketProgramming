package ChatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

public class ChatServer {
    private static final int port =9001;
    private static HashSet<String> usernames = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    public static void main(String[] args) throws IOException {
        System.out.println("Starting Chat Server");
        ServerSocket listner = new ServerSocket(port);
        try {
            while (true) {
                Socket client = listner.accept();
                Thread handlerThred =new Thread(new Handler(client));
                handlerThred.start();
            }
        }
        finally {
            listner.close();
        }
    }

    private static class Handler implements Runnable {

        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String username;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("Enter Username");
                    username = in.readLine();
                    if (username==null) return;

                    if (!usernames.contains(username)) {
                      usernames.add(username);
                      break;
                    }
                }
                System.out.println("Username Accepted");
                writers.add(out);

                while (true) {
                    String input = in.readLine();
                    if (input==null) break;

                    for (PrintWriter writer : writers) {
                        writer.println("Massage"+username+" "+input);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
            finally {

                    if (username!=null) {
                        usernames.remove(username);
                    }
                    if (writers!=null) {
                        writers.remove(out);
                    }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
