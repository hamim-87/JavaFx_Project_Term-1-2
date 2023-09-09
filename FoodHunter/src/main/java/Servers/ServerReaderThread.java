package Servers;

import Comunications.LoginDataTransferObject;
import Comunications.NetworkConnection;
import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ServerReaderThread {

    private Socket socket;

    private List<Restaurant> ExtractedRestaurantList = new ArrayList<Restaurant>();

    public List<Restaurant> getExtractedRestaurantList(){ return ExtractedRestaurantList;}

    private List<Food> ExtractedFoodList = new ArrayList<Food>();

    public List<Food> getExtractedFoodList() { return  ExtractedFoodList;}

    private ConcurrentHashMap<String,String> Passwords = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String,String> getPasswords() { return Passwords;}

    public ServerReaderThread(Socket socket,List<Restaurant> rs,List<Food> fd)
    {
        this.ExtractedFoodList = fd;
        this.ExtractedRestaurantList = rs;
        this.socket = socket;
    }

    public synchronized void CustomerLogIn() throws IOException, ClassNotFoundException {
        NetworkConnection nc = nc = new NetworkConnection(socket);
        Object fromClient = nc.read();

        if(fromClient instanceof LoginDataTransferObject)
        {
            LoginDataTransferObject LoginDTO = new LoginDataTransferObject();
            LoginDTO = (LoginDataTransferObject) fromClient;
            if(Passwords.containsKey(LoginDTO.getUserName()))
            {
                System.out.println("Valid user");
                String RealPass = Passwords.get(LoginDTO.getUserName());
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
}
