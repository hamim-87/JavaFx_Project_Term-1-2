package Controllers;

import Client.Client;
import Client.OrderCompleted;
import DataBaseSystem.ClientFood;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class showRestaurantTheOrderController {


    @FXML
    private Label name;

    @FXML
    private Label foodCat;

    @FXML
    private Label foodName;

    @FXML
    private Label foodcounter;

    @FXML
    private Label foodprice;

    private OrderCompleted orderCompleted;

    private ClientFood clientFood;

    @FXML
    public void clickedCompleted(MouseEvent mouseEvent) throws IOException {
        orderCompleted.CompletedClientFood(clientFood);
    }



    public void setDataForOrders(ClientFood clientFood,OrderCompleted orderCompleted)
    {
        this.clientFood = clientFood;
        this.orderCompleted = orderCompleted;
        //if(name != null) {
            name.setText(clientFood.getUsername());
        //}

        foodCat.setText(clientFood.getFood().getCategory());
        foodName.setText(clientFood.getFood().getName());
        foodcounter.setText(Integer.toString(clientFood.getFoodCount()));
        foodprice.setText(Double.toString(clientFood.getFood().getPrice()));
    }
}
