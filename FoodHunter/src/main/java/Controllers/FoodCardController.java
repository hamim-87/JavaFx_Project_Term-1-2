package Controllers;

import Client.MyFoodListener;
import Client.MyListListener;
import Comunications.FileOperation;
import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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


    public FileOperation Fo = new FileOperation();
    private List<Restaurant> RestaurantList = Fo.ReadFileForRestaurant();

    private MyFoodListener myListListener;



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

    public void ClickedFood(MouseEvent mouseEvent)
    {
        myListListener.onclickFoodListener(food);
    }



    public void setDataForFood(Food food, MyFoodListener myListListener)
    {
        this.food = food;
        this.myListListener = myListListener;
        foodName.setText(food.getName());
        foodCat.setText(food.getCategory());
        foodprice.setText(Double.toString(food.getPrice()));
        restaurant.setText(GetRestaurantNameFromId(food.getRestaurantId()));


    }
}
