package Servers;

import  Comunications.*;
import DataBaseSystem.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class NewThreadServer implements Runnable{

        private NetworkConnection nc;
        private Socket clientSocket;
        private Thread t;

        private  Server server;

        private List<Restaurant> ExtractedRestaurantList = new ArrayList<>();

        private List<Food> ExtractedFoodList = new ArrayList<>();

        private ConcurrentHashMap<String,String> PasswordList = new ConcurrentHashMap<>();

    NewThreadServer(Socket clientSocket,Server server) throws IOException {
            this.clientSocket = clientSocket;
            nc = new NetworkConnection(this.clientSocket);
            this.server = server;

            ExtractedRestaurantList = server.getExtractedRestaurantList();

            ExtractedFoodList = server.getExtractedFoodList();

            PasswordList = server.getPasswords();



            t= new Thread(this);
            t.start();

        }


        @Override
        public void run() {

            try {
                while (true) {

                    Object fromClient = nc.read();

                    if(fromClient instanceof LoginDataTransferObject)
                    {
                        LoginDataTransferObject LoginDTO = new LoginDataTransferObject();
                        LoginDTO = (LoginDataTransferObject) fromClient;
                        if(PasswordList.containsKey(LoginDTO.getUserName()))
                        {
                            System.out.println("Valid user");
                            String RealPass = PasswordList.get(LoginDTO.getUserName());
                            if(RealPass.equals(LoginDTO.getPassword()))
                            {
                                LoginDTO.setStatus(true);
                                nc.write(ExtractedRestaurantList);
                                System.out.println(LoginDTO.getUserName()+ " Logged in...");
                            }
                            else{
                                nc.write(LoginDTO);
                            }
                        }
                        else{
                            nc.write(LoginDTO);
                        }

                    }



                }

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }