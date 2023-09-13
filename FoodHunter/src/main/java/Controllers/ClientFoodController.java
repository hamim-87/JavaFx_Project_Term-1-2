package Controllers;

import DataBaseSystem.ClientFood;
import DataBaseSystem.Restaurant;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class ClientFoodController {
    @FXML
    private Button IncreaseItem;

    @FXML
    private Button decreaseItem;

    @FXML
    private Label foodCat;

    @FXML
    private Label foodName;

    @FXML
    private Label foodcounter;

    @FXML
    private Label foodprice;

    @FXML
    private Label restaurant;

    private List<Restaurant> restaurantList = new ArrayList<>();

    public void setRestaurantList(List<Restaurant> list)
    {
        restaurantList = list;
    }

    public String getResName(int id)
    {
        for(Restaurant r: restaurantList)
        {
            if(r.getId() == id)
            {
                return r.getName();
            }
        }
        return "Not Found";
    }


    public void setDataForClient(ClientFood clientFood)
    {
        foodName.setText(clientFood.getFood().getName());
        foodcounter.setText(Integer.toString(clientFood.getFoodCount()));
        foodprice.setText(Double.toString(clientFood.getFood().getPrice()));
        foodCat.setText(clientFood.getFood().getCategory());
        restaurant.setText(getResName(clientFood.getFood().getRestaurantId()));
    }
}
