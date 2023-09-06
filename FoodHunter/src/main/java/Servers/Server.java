package Servers;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    ServerSocket serverSocket;
    Server() throws IOException {
        serverSocket = new ServerSocket(80);
        System.out.println("Server waiting for client...");

        while(true)
        {
            serverSocket.accept();
            System.out.println("Client found...");



        }

    }

    public static void main(String[] args) throws IOException {
            Server n = new Server();
    }
}
