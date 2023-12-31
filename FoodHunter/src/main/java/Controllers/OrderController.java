package Controllers;

import Client.FoodIncrease;
import Client.Main;
import Comunications.LoginDataTransferObject;
import Comunications.OrderList;
import DataBaseSystem.ClientFood;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderController {


    @FXML
    private GridPane ClientFoodgrid;

    @FXML
    private Label totalprice;

    public void setTotalprice(Double total)
    {
        totalprice.setText(Double.toString(total));
    }

    @FXML
    void sendtorestaurant(ActionEvent event) throws IOException {

        OrderList list = new OrderList();
        list.setListOfFood(OrderedFoodList);
        //
        System.out.println(list.getListOfFood());


            main.getNetwork().write(list);


    }

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }



    private List<ClientFood> OrderedFoodList = new ArrayList<>();

    public void setOrderList(List<ClientFood> list)
    {
        this.OrderedFoodList = list;
    }

    private LoginDataTransferObject loginDTO;

    public void setLoginDTO(LoginDataTransferObject loginDTO) {
        this.loginDTO = loginDTO;
    }

    private FoodIncrease foodIncrease;

    public void increment(ClientFood food) throws IOException {
        System.out.println(food.getFood().getName());
        for(int i = 0;i<OrderedFoodList.size();i++)
        {
            if(food.getFood().getName().equals(OrderedFoodList.get(i).getFood().getName()))
            {
                OrderedFoodList.get(i).IncreaseFoodCount();
                break;
            }
        }
        setTotalprice(main.calculate(OrderedFoodList));
        ClientFoodgrid.getChildren().clear();
        showOrderList();

    }

    public void showOrderList() throws IOException {
        if(OrderedFoodList.size()>0)
        {

            System.out.println(OrderedFoodList);

            foodIncrease = new FoodIncrease() {
                @Override
                public void increse(ClientFood food) throws IOException {
                    increment(food);
                }
            };


            int colom = 0,row = 0;
            for (int i = 0;i<OrderedFoodList.size();i++)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FxmlFiles/ClientFood.fxml"));

                AnchorPane anchorPane = loader.load();

                ClientFoodController clientFoodController = loader.getController();
                clientFoodController.setRestaurantList(loginDTO.getRestaurantList());

                clientFoodController.setDataForClient(OrderedFoodList.get(i),foodIncrease);

                AnchorPane dummy1 = new AnchorPane();
                dummy1.setMaxWidth(260);
                dummy1.setMaxHeight(10);

                if(i == 0)
                {
                    ClientFoodgrid.add(dummy1,colom,row++);
                }

                ClientFoodgrid.add(anchorPane,colom,row++);

                //width
                ClientFoodgrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                ClientFoodgrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                ClientFoodgrid.setMaxWidth(Region.USE_PREF_SIZE);

                //height
                ClientFoodgrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                ClientFoodgrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                ClientFoodgrid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane,new Insets(10));


            }
        }

    }


}
