package DataBaseSystem;

import java.util.List;
import java.util.ArrayList;

public class RestaurantCatagory {
    private String ResCategory;
    private List<String> RestaurantInThatCategory=new ArrayList<String>();

    public RestaurantCatagory(String ResCategory, String Restaurant)
    {
        this.ResCategory = ResCategory;
        RestaurantInThatCategory.add(Restaurant);
    }

    public RestaurantCatagory() {
    }

    String getResCategory(){return ResCategory;}
    List<String> getRestaurantInThatCategory(){return RestaurantInThatCategory;}
    public void setRestaurantInThatCategory(String Restaurant)
    {
        RestaurantInThatCategory.add(Restaurant);
    }




}
