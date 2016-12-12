package echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Piotrek on 05.11.2016.
 */
public class echoc {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9898);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String msg = in.readLine();
        while (true)
        {

            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("Client name: " + args[0]);
            pw.println("Message: " + msg);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String answer = input.readLine();
            String answer2 = input.readLine();
            System.out.println(answer);
            System.out.println(answer2);
            try{
                Thread.sleep(2000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
