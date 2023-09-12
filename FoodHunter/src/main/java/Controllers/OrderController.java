package Controllers;

import DataBaseSystem.ClientFood;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private List<ClientFood> OrderedFoodList = new ArrayList<>();

    public void setOrderList(List<ClientFood> list)
    {
        this.OrderedFoodList = list;
    }
}
