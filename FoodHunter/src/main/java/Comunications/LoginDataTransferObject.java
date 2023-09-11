package Comunications;

import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class LoginDataTransferObject implements Serializable {
    private String userName;

    private String password;

    private boolean status;

    private List<Restaurant> RestaurantList = new ArrayList<>();

    public void setRestaurantList(List<Restaurant> restaurantList) {

        RestaurantList = restaurantList;
    }

    public List<Restaurant> getRestaurantList() {
        return RestaurantList;
    }

    private List<Food> FoodList = new ArrayList<>();

    public List<Food> getFoodList() {
        return FoodList;
    }

    public void setFoodList(List<Food> foodList) {
        FoodList = foodList;
    }

    public void setUserName(String userName){ this.userName = userName;}

    public void setPassword(String password) { this.password = password;}

    public  void setStatus(Boolean status) {this.status = status;}

    public String getUserName() { return this.userName;}
    public String getPassword(){ return  this.password;}

    public Boolean getStatus() { return  this.status;}
}
