package ChatRoom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    BufferedReader in ;
    PrintWriter out ;
    JFrame frame = new JFrame("Chat Application");
    JTextField textField = new JTextField(40);
    JTextArea textArea = new JTextArea(8,40);

    public ChatClient() {
        textArea.setEditable(false);
        textArea.setEditable(false);
        frame.getContentPane().add(textField);
        frame.getContentPane().add(new JScrollPane(textArea),"Center");
        frame.pack();

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textField.getText());
                textArea.setText("");
            }
        });
    }
    private String getServerAddress() {
        return JOptionPane.showInputDialog(frame,"Enter Server Address :",
                "Welcome to Chat Application",JOptionPane.QUESTION_MESSAGE);
    }
    public String getName(){
        return JOptionPane.showInputDialog(frame,"Choose a Screen Name","Screen Name",JOptionPane.PLAIN_MESSAGE);
    }
    private void run() throws IOException {
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress,9001);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);

        while (true) {
            String message = in.readLine();
            if (message.startsWith("SUBMITNAME")) {
                out.println(getName());
            }
            else if (message.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            }
            else if (message.startsWith("MASSAGE")) {
                textArea.append(message+"\n");
            }
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.frame.pack();
    }
}
