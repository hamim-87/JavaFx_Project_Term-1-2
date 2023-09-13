package Client;

import Comunications.LoginDataTransferObject;
import Comunications.NetworkConnection;
import Comunications.OrderList;
import Comunications.RestaurantLoginInfo;
import javafx.application.Platform;

import java.io.IOException;

public class ReaderThreadOfRestaurant implements Runnable{
    private final RestaurantClient main;

    private final Thread t;

    private NetworkConnection network;

    ReaderThreadOfRestaurant(RestaurantClient main, NetworkConnection network)
    {
        this.main = main;
        this.network = network;
        t = new Thread(this);



        t.start();
    }
    @Override
    public void run() {
        try {
            System.out.println("Waiting for Server to send something");


            while (true)
            {

                Object o = network.read();


                if(o instanceof RestaurantLoginInfo)
                {
                    //System.out.println("hehe");

                    RestaurantLoginInfo restaurantLogInfo = new RestaurantLoginInfo();
                    restaurantLogInfo = (RestaurantLoginInfo ) o;
                    if(restaurantLogInfo.getStatus())
                    {
                        System.out.println("Restaurant can log in..");


                        //LoginDataTransferObject finalLoginDTO = LoginDTO;
                        RestaurantLoginInfo finalRestaurantLogInfo = restaurantLogInfo;
                        Platform.runLater(() -> {
                            try {
                                main.ShowRestaurantHomePage(finalRestaurantLogInfo);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                    else{
                        Platform.runLater(() -> main.ShowAlert("Incorrect","Incorrect","Username or Password wrong"));
                    }
                }
                else if(o instanceof OrderList){
                   OrderList orderList = new OrderList();
                   orderList = (OrderList) o;
                    System.out.println("Restaurant found"+ orderList.getListOfFood());


                }
            }








        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}

