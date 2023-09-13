package Controllers;

import DataBaseSystem.ClientFood;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class showRestaurantTheOrderController {

    @FXML
    private Label ClientName;

    @FXML
    private Label foodCat;

    @FXML
    private Label foodName;

    @FXML
    private Label foodcounter;

    @FXML
    private Label foodprice;

    public void setDataForOrders(ClientFood clientFood)
    {
        if (ClientName != null) {
            ClientName.setText(clientFood.getUsername());
        }

        foodCat.setText(clientFood.getFood().getCategory());
        foodName.setText(clientFood.getFood().getName());
        foodcounter.setText(Integer.toString(clientFood.getFoodCount()));
        foodprice.setText(Double.toString(clientFood.getFood().getPrice()));
    }
}
