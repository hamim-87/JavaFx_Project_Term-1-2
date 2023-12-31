package Servers;

import Comunications.*;
import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Server {


    private List<Restaurant> ExtractedRestaurantList = new ArrayList<Restaurant>();

    public List<Restaurant> getExtractedRestaurantList(){ return ExtractedRestaurantList;}

    private List<Food> ExtractedFoodList = new ArrayList<Food>();

    public List<Food> getExtractedFoodList() { return  ExtractedFoodList;}

    private ConcurrentHashMap<String,String> Passwords = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String,String> getPasswords() { return Passwords;}

    private List<NewThreadServer> Allservers = new ArrayList<>();

    public void SendToRestaurant(OrderList list) throws IOException {
        for(NewThreadServer tr : Allservers)
        {
            System.out.println("how many server?");
            tr.getNc().write(list);
        }
    }



    Server() throws IOException {
        FileOperation FO = new FileOperation();
        ExtractedRestaurantList = FO.ReadFileForRestaurant();
        System.out.println("Restaurent is loaded..");

        ExtractedFoodList = FO.ReadFileForMenu();
        System.out.println("Food list is loaded..");






        Passwords = FO.getPassword();
        System.out.println("Password collected..");





        ServerSocket serverSocket = new ServerSocket(80);
        System.out.println("Server Started..");


        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected..");

            // new Server Thread Start...
            NewThreadServer N = new NewThreadServer(socket,this);
            Allservers.add(N);

        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server s = new Server();
    }
}


