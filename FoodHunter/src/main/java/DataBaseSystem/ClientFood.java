package DataBaseSystem;

public class ClientFood extends Food{

    private  int FoodCount = 1;

    private String RestaurantName;

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }
    public String getRestaurantName()
    {
        return RestaurantName;
    }

    public void IncreaseFoodCount()
    {
        FoodCount++;
    }

    public void DecreaseFoodCount()
    {
        if(FoodCount > 1)
        {
            FoodCount--;
        }
    }
    public ClientFood(int RestaurantId, String Category, String Name, double Price) {
        super(RestaurantId, Category, Name, Price);
    }
}
