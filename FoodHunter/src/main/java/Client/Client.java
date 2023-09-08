package Client;

import Comunications.NetworkConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Client started..");
        Socket socket = new Socket("127.0.0.1", 80);
        System.out.println("Client Connected..");

        NetworkConnection nc = new NetworkConnection(socket);
        while (true) {
            Scanner sc = new Scanner(System.in);

            String message = sc.nextLine();

            if(message.equals("exit")){
                break;
            }

            //sent to server...
            //oos.writeObject(message);

            nc.write(message);

            try {
                //receive from server..
                //Object fromServer = ois.readObject();
                Object fromServer = nc.read();
                System.out.println("From Server: " + (String) fromServer);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        socket.close();

    }
}