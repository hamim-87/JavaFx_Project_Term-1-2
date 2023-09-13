package DataBaseSystem;

public class ClientFood{

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
    public int getFoodCount()
    {
        return FoodCount;
    }

    private Food food;

    public void setFood(Food food)
    {
        this.food = food;
    }

    public Food getFood()
    {
        return food;
    }


}
