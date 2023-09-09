package DataBaseSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable {
    private int Id;
    private String Name;
    private double Score;
    private String Price;
    private String ZipCode;
    private String[] Categories;
    private int CategorySize;
    List<Food> FoodList = new ArrayList<Food>();
    
    public Restaurant(int Id, String Name, double Score, String Price, String ZipCode, String category1, String category2, String category3)
    {
        this.Id = Id;
        this.Name = Name;
        this.Score = Score;
        this.Price = Price;
        this.ZipCode = ZipCode;
        Categories = new String[3];
        Categories[0] = category1;
        Categories[1] = category2;
        Categories[2] = category3;
        CategorySize = 3;

    }
    public Restaurant(int Id, String Name, double Score, String Price, String ZipCode, String category1, String category2)
    {
        this.Id = Id;
        this.Name = Name;
        this.Score = Score;
        this.Price = Price;
        this.ZipCode = ZipCode;
        Categories = new String[3];
        Categories[0] = category1;
        Categories[1] = category2;
        CategorySize = 2;


    }
    public Restaurant(int Id, String Name, double Score, String Price, String ZipCode, String category1)
    {
        this.Id = Id;
        this.Name = Name;
        this.Score = Score;
        this.Price = Price;
        this.ZipCode = ZipCode;
        Categories = new String[3];
        Categories[0] = category1;
        CategorySize = 1;


    }
    Restaurant(){}

    //getters and setters
    public int getId(){ return this.Id; }
    public String getName(){ return this.Name; }
    public Double getScore(){ return this.Score;}
    public String getPrice(){ return this.Price; }
    public String getZipCode(){ return this.ZipCode; }
    public String[] getCategoriese() { return this.Categories;}
    public int getCategorySize(){ return CategorySize;}


    public void setFoodList(Food food)
    {
        FoodList.add(food);
    }
    public List<Food> getFoodList()
    {

        return FoodList;
    }

}