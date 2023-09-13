package Comunications;

import DataBaseSystem.ClientFood;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderList implements Serializable {
    private List<ClientFood> listOfFood = new ArrayList<>();

    public List<ClientFood> getListOfFood() {
        return listOfFood;
    }

    public void setListOfFood(List<ClientFood> listOfFood) {
        this.listOfFood = listOfFood;
    }
}
