package echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Piotrek on 05.11.2016.
 */
public class echos {
    public static void main(String[] args) throws Exception {
        System.out.println("Uruchomiono serwer.");
        int connectionNumber = 0;
        ServerSocket listener = new ServerSocket(9898);
        try {
            while (true) {
                connection c = new connection(listener.accept(), connectionNumber++);
                c.start();
            }
        }
        finally {
            listener.close();
        }
    }

    static class connection extends Thread {
        private Socket socket;
        private int connectionNumber;
        private String message;

        public connection(Socket socket, int connectionNumber){
            this.socket = socket;
            this.connectionNumber = connectionNumber;
            this.message = message;
            log("Connection number id: " + connectionNumber);
            log("Message: " + message);
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                pw.println("nr: " + connectionNumber + ".");

                while (true) {
                    String input = in.readLine();
                    System.out.println("\t\t"+connectionNumber+" : "+input);
                    pw.println(input.toUpperCase());
                }
            }
            catch (IOException e) {
                log("error number: " + connectionNumber + ": " + e);
            }
            finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Cannot close");
                }
                log("Connection nr: " + connectionNumber + " closed");
            }
        }

        private void log(String a) {

            System.out.println(a);
        }
    }
}
