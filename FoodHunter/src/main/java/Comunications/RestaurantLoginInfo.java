package Comunications;

import Controllers.RestaurantLogInController;
import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RestaurantLoginInfo implements Serializable {
    private String userName;

    private String password;

    private boolean status;

    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setUserName(String userName){ this.userName = userName;}

    public void setPassword(String password) { this.password = password;}

    public  void setStatus(Boolean status) {this.status = status;}

    public String getUserName() { return this.userName;}
    public String getPassword(){ return  this.password;}

    public Boolean getStatus() { return  this.status;}
}
