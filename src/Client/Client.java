package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import Client.dto.Author;
import Client.printCustom.Print;

public class Client implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;
    private static Author author;

    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1", 5050);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inHandler = new InputHandler();
            Thread t = new Thread(inHandler);
            t.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null) {
                // inMessage = inMessage ;
                String message[] = inMessage.split("Ξ",3);
                
                // System.out.println(inMessage.split("Ξ")[0]);
                Print.color(message[2], message[0], message[1]);
            }

        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    public void shutdown() {
        done = true;
        try {
            in.close();
            out.close();
            if (!client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {
            try {
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (!done) {
                    String message = inReader.readLine();
                    author.setMessage(message);
                    if (message.equals("/quit")) {
                        out.println("/quit");
                        inReader.close();
                        shutdown();
                    } else {
                        out.println(author);
                    }
                }

            } catch (IOException e) {
                shutdown();
                // TODO: handle exception
            }
        }
    }

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        author = new Author();
        System.out.print("seu nickname: ");
        String nickname = leitor.nextLine();
        author.setNickname(nickname);
        Client client = new Client();
        client.run();
    }
}
