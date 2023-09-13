package Servers;

import  Comunications.*;
import DataBaseSystem.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.spec.RSAOtherPrimeInfo;
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
                    System.out.println("why" + fromClient);

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

                                LoginDTO.setRestaurantList(ExtractedRestaurantList);
                                LoginDTO.setFoodList(ExtractedFoodList);
                                nc.write(LoginDTO);
                                System.out.println(LoginDTO.getUserName()+ " Logged in...");
                            }
                            else{
                                nc.write(LoginDTO);
                            }
                        }
                        else{
                            nc.write(LoginDTO);
                        }

                    }else if(fromClient instanceof RestaurantLoginInfo)
                    {
                        System.out.println("From Restaurant..");

                        RestaurantLoginInfo restaurantLoginInfo = new RestaurantLoginInfo();
                        restaurantLoginInfo = (RestaurantLoginInfo) fromClient;
                        if(PasswordList.containsKey(restaurantLoginInfo.getUserName()))
                        {
                            System.out.println("Valid Restaurart...");

                            String realpass = PasswordList.get(restaurantLoginInfo.getUserName());
//                            System.out.println(realpass);
//                            System.out.println("user"+ restaurantLoginInfo.getUserName());
                            if(realpass.equals(restaurantLoginInfo.getPassword()))
                            {
                                System.out.println("Restaurant log in");
                                restaurantLoginInfo.setStatus(true);

                                int id = Integer.parseInt(restaurantLoginInfo.getUserName());

                                for (Restaurant r : ExtractedRestaurantList)
                                {
                                    if(r.getId() == id)
                                    {
                                        restaurantLoginInfo.setRestaurant(r);
                                        //---..>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                                        System.out.println(r.getName());
                                        break;
                                    }
                                }



                            }
                            else{
                                restaurantLoginInfo.setStatus(false);
                            }

                        }else{

                            restaurantLoginInfo.setStatus(false);

                        }
                        nc.write(restaurantLoginInfo);

                    }else if( fromClient instanceof OrderList)
                    {
                        OrderList list = new OrderList();
                        list = (OrderList) fromClient;
                        System.out.println("serverside:" + list.getListOfFood());
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