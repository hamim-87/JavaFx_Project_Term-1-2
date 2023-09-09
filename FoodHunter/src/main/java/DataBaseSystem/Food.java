package DataBaseSystem;

import java.io.Serializable;

public class Food implements Serializable {
    private int RestaurantId;
    private String Category;
    private String Name;
    private double Price;

    public Food(int RestaurantId, String Category, String Name, double Price)
    {
        this.RestaurantId = RestaurantId;
        this.Category = Category;
        this.Name = Name;
        this.Price = Price;
    }

    //getters and setters
    public int getRestaurantId() { return RestaurantId; }
    public  String getCategory() { return Category; }
    public  String getName() { return Name; }
    public double getPrice() { return Price; }
}

