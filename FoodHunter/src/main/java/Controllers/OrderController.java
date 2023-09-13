package Controllers;

import Comunications.LoginDataTransferObject;
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

    @FXML
    void sendtorestaurant(ActionEvent event) {

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

    public void showOrderList() throws IOException {
        if(OrderedFoodList.size()>0)
        {

            System.out.println(OrderedFoodList);


            int colom = 0,row = 0;
            for (int i = 0;i<OrderedFoodList.size();i++)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FxmlFiles/ClientFood.fxml"));

                AnchorPane anchorPane = loader.load();

                ClientFoodController clientFoodController = loader.getController();
                clientFoodController.setRestaurantList(loginDTO.getRestaurantList());

                clientFoodController.setDataForClient(OrderedFoodList.get(i));

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
