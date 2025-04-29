package part2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        String msg;
        String modifiedMsg;

        BufferedReader indormUser = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = new Socket(host,9806);

        System.out.println("input a Msg");
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        msg = indormUser.readLine();

        out.writeBytes(msg + "\n");

        modifiedMsg = inFromServer.readLine();
        System.out.println("From Server :" + modifiedMsg);
        socket.close();


    }
}
