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



    Server() throws IOException, ClassNotFoundException {
        FileOperation FO = new FileOperation();
        ExtractedRestaurantList = FO.ReadFileForRestaurant();
        System.out.println("Restaurent is loaded..");

        ExtractedFoodList = FO.ReadFileForMenu();
        System.out.println("Food list is loaded..");

        System.out.println(ExtractedRestaurantList.get(0).getFoodList());




        Passwords = FO.getPassword();
        System.out.println("Password collected..");




        ServerSocket serverSocket = new ServerSocket(80);
        System.out.println("Server Started..");


        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected..");

//            NetworkConnection nc = nc = new NetworkConnection(socket);
//            Object fromClient = nc.read();
//
//            if(fromClient instanceof LoginDataTransferObject)
//            {
//                LoginDataTransferObject LoginDTO = new LoginDataTransferObject();
//                LoginDTO = (LoginDataTransferObject) fromClient;
//                if(Passwords.containsKey(LoginDTO.getUserName()))
//                {
//                    System.out.println("Valid user");
//                    String RealPass = Passwords.get(LoginDTO.getUserName());
//                    if(RealPass.equals(LoginDTO.getPassword()))
//                    {
//                        LoginDTO.setStatus(true);
//                        nc.write(ExtractedRestaurantList);
//                        System.out.println(LoginDTO.getUserName()+ " Logged in...");
//                    }
//                    else{
//                        nc.write(LoginDTO);
//                    }
//                }
//                else{
//                    nc.write(LoginDTO);
//                }
//
//            }

            // new Server Thread Start...
            new NewThreadServer(socket,this);

        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server s = new Server();
    }
}


