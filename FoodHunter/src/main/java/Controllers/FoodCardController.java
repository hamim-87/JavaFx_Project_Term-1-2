package Controllers;

import Comunications.FileOperation;
import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FoodCardController {
    @FXML
    private Button Addtocard;

    @FXML
    private Label foodCat;

    @FXML
    private Label foodName;

    @FXML
    private Label foodprice;

    @FXML
    private Label restaurant;

    private Food food;

    //test-------------------------------------------------------------------------
    public FileOperation Fo = new FileOperation();
    private List<Restaurant> RestaurantList = Fo.ReadFileForRestaurant();

    public FoodCardController() throws IOException {
    }

    public void setRestaurantList(List<Restaurant> r)
    {
        RestaurantList = r;
    }

    public String GetRestaurantNameFromId(int id)
    {
        String name = new String();
        for(Restaurant r : RestaurantList)
        {
            if(r.getId() == id)
            {
                name = r.getName();
                break;
            }
        }
        return name;
    }

    public void setDataForFood(Food food)
    {
        this.food = food;
        foodName.setText(food.getName());
        foodCat.setText(food.getCategory());
        foodprice.setText(Double.toString(food.getPrice()));
        restaurant.setText(GetRestaurantNameFromId(food.getRestaurantId()));

    }
}
