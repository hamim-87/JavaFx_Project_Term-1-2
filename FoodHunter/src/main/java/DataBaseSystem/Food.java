package DataBaseSystem;

public class Food {
    private int RestaurantId;
    private String Category;
    private String Name;
    private double Price;

    Food(int RestaurantId, String Category, String Name, double Price)
    {
        this.RestaurantId = RestaurantId;
        this.Category = Category;
        this.Name = Name;
        this.Price = Price;
    }

    //getters and setters
    int getRestaurantId() { return RestaurantId; }
    String getCategory() { return Category; }
    String getName() { return Name; }
    double getPrice() { return Price; }
}

