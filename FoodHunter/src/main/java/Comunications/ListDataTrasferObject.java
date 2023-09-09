package Comunications;

import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListDataTrasferObject implements Serializable {
    private List<Restaurant> RestaurantList = new ArrayList<>();

    private List<Food> FoodList = new ArrayList<>();

    public void setRestaurantList(List<Restaurant> RestaurantList)
    {
        this.RestaurantList = RestaurantList;
    }

    public void setFoodList(List<Food> foodList) {
        this.FoodList = foodList;
    }
    public List<Restaurant> getRestaurantList() {
        return RestaurantList;
    }

    public List<Food> getFoodList() {
        return FoodList;
    }
}
