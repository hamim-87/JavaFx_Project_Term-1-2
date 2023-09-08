package DataBaseSystem;

import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RestaurantDatabaseSystem {
    
    List<Restaurant> RestaurantList = new ArrayList<Restaurant>();
    List<Food> FoodList = new ArrayList<Food>();
    List<RestaurantCatagory> RestaurantCatagoryList = new ArrayList<RestaurantCatagory>();
    List<String> RestaurantString = new ArrayList<String>();
    List<String> FoodString = new ArrayList<String>();
    

    
    public List<Restaurant> getRestaurantList() { return RestaurantList; }

    public List<Food> getFoodList() { return FoodList;}


    //file operations
//    public void OpenFileForRestaurant(String name)
//    {
//        try (BufferedReader RestaurantBr = new BufferedReader(new FileReader(name))) {
//            while(true)
//            {
//                String line = RestaurantBr.readLine();
//                if(line == null) break;
//                AddRestaurant(line);
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void OpenFileForMenu(String name)
//    {
//        try(BufferedReader FoodBr = new BufferedReader(new FileReader(name)))
//        {
//            while(true)
//            {
//                String line = FoodBr.readLine();
//                if(line == null) break;
//                addMenu(line);
//            }
//
//
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//    }

    //Adding Restaurant and category
    public void AddRestaurant(String line){

        RestaurantString.add(line);
        String[] array = line.split(",",-1);
        Restaurant NewRestaurant;
        if(array[7] == "" && array[6] == "" ){
            NewRestaurant = new Restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), array[3], array[4], array[5]);
             AddRestaurantInACatagory(array[5],array[1]);
        }
        else if(array[7]== "" )
        {
             NewRestaurant = new Restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), array[3], array[4], array[5], array[6]);
            AddRestaurantInACatagory(array[5],array[1]);
            AddRestaurantInACatagory(array[6],array[1]);


        }
        else{
            NewRestaurant = new Restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), array[3], array[4], array[5], array[6],array[7]);
            //
            // if(array[7].equals(""))
            //     System.out.println(array[7]+"hehe");
            AddRestaurantInACatagory(array[5],array[1]);
            AddRestaurantInACatagory(array[6],array[1]);
            AddRestaurantInACatagory(array[7],array[1]);
            
        }
        
        RestaurantList.add(NewRestaurant);       
    }
    
    //Add food 
    public void addMenu(String line)
    {
        FoodString.add(line);
        String[] MenuDetail = line .split(",",-1);
        Food NewMenu = new Food(Integer.parseInt(MenuDetail[0]),MenuDetail[1],MenuDetail[2],Double.parseDouble(MenuDetail[3]));
        FoodList.add(NewMenu);
    }

    //<--   SEARCH METHODS   -->

    // BY Name Search --> if found will return index else -1
    public int FindRestaurantsByName(String name)
    {
        for(int i=0;i<RestaurantList.size();i++)
        {
            if(RestaurantList.get(i).getName().toLowerCase().contains(name))
            {
                return i;
            }
        }

        return -1;
    }

    public Restaurant GetSearchedRestaurant(int index)
    {
        return RestaurantList.get(index);
    }

    public List<Food> CollectFoodMenu(int id)
    {
        List<Food> list = new ArrayList<Food>();
        for(int i = 0;i<FoodList.size();i++)
        {
            if(FoodList.get(i).getRestaurantId() == id)
            {
                list.add(FoodList.get(i));
            }
        }
        return list;

    }


    //2 By score
    List<Restaurant> GetRestaurantByScore(double num1, double num2)
    {
        List<Restaurant> list = new ArrayList<Restaurant>();
        
        for(Restaurant r : RestaurantList)
        {
            double score = r.getScore();
            if(num1<= score && num2>= score)
            {
                list.add(r);
            }
        }

        return list;

    }

    //by category
    List<Restaurant> GetRestaurantByCategoty(String category)
    {
        List<Restaurant> list = new ArrayList<Restaurant>();
        for(Restaurant r : RestaurantList)
        {
            for(int i = 0;i< r.getCategorySize();i++)
            {
                if(r.getCategoriese()[i].equalsIgnoreCase(category))
                {
                    list.add(r);
                }
            }
        }
        return list;
    }

    //price
    List<Restaurant> GetRestaurantByPrice(String price)
    {
        List<Restaurant> list = new ArrayList<Restaurant>();
        for(Restaurant r : RestaurantList)
        {
            
            if(r.getPrice().equals(price))
            {
                list.add(r);
            }
        }
        return list;

    }

    List<Restaurant> GetRestaurantByZipCode(String Zipcode)
    {
        List<Restaurant> list = new ArrayList<Restaurant>();
        for(Restaurant r : RestaurantList)
        {
            
            if(r.getZipCode().equals(Zipcode))
            {
                list.add(r);
            }
        }
        return list;

    }

    //6 restaurantcatList methods
    public void AddRestaurantInACatagory(String category, String Restaurant)
    {
        
        for(RestaurantCatagory r: RestaurantCatagoryList)
        {
            if(r.getResCategory().equals(category))
            {
                r.setRestaurantInThatCategory(Restaurant);
                return;
            }
        }
        RestaurantCatagory NewCatagory = new RestaurantCatagory(category, Restaurant);
        RestaurantCatagoryList.add(NewCatagory);
    }
    List<RestaurantCatagory> getRestaurantCatergoryList()
    {
        return RestaurantCatagoryList;
    }


    // <---  Search Food Items  --->

    List<Food> getFoodByName(String FoodName)
    {
        List<Food> FList = new ArrayList<Food>();
        for(Food f : FoodList)
        {
            if(f.getName().toLowerCase().contains(FoodName.toLowerCase()))
            {
                FList.add(f);
            }
        }

        return FList;
    }

    public List<Food> getFoodByNameAndRestaurant(String name, int id)
    {
        List<Food> list = new ArrayList<Food>();
        for(int i = 0;i<FoodList.size();i++)
        {
            if(FoodList.get(i).getRestaurantId() == id)
            {
                if(FoodList.get(i).getName().toLowerCase().contains(name.toLowerCase()))
                {
                    list.add(FoodList.get(i));
                }
            }
        }
        return list;

    }

    public List<Food> getFoodByCategory(String category)
    {
        List<Food> list = new ArrayList<Food>();
        for(Food f: FoodList)
        {
            if(f.getCategory().equalsIgnoreCase(category))
            {
                list.add(f);
            }
        }

        return list;
    }

     public List<Food> getFoodByCategoryAndRestaurant(String category, int id)
    {
        List<Food> list = new ArrayList<Food>();
        for(int i = 0;i<FoodList.size();i++)
        {
            if(FoodList.get(i).getRestaurantId() == id)
            {
                if(FoodList.get(i).getCategory().equalsIgnoreCase(category))
                {
                    list.add(FoodList.get(i));
                }
            }
        }
        return list;
    }

    public List<Food> getFoodByRange(double min, double max)
    {
        List<Food> list = new ArrayList<Food>();
        for(Food f : FoodList)
        {
            if(f.getPrice() >= min && f.getPrice() <= max)
            {
                list.add(f);
            }
        }

        return list;
    }



    List<Food> getFoodByRangeAndRestaurant(int id, double min, double max)
    {
        List<Food> list = new ArrayList<Food>();
        for(Food f : FoodList)
        {
            if(f.getRestaurantId() ==id)
            {
                if(f.getPrice() >= min && f.getPrice() <= max)
                {
                list.add(f);
                }
            }
        }

        return list;
    }

    List<Food> FindCostliestFoodInaRestaurant(int id)
    {
        List<Food> list = new ArrayList<Food>();
        double mx_price = -1;
        for(Food f : FoodList)
        {
            if(f.getRestaurantId() == id)
            {
                mx_price = Math.max(mx_price, f.getPrice());
            }
        }

        for(Food f : FoodList)
        {
            if(f.getRestaurantId() == id)
            {
                if(f.getPrice() == mx_price)
                {
                    list.add(f);
                }
            }
        }
        return list;
    }


    boolean isValidRestaurant(String name)
    {
        for(Restaurant r: RestaurantList)
        {
            if(r.getName().equalsIgnoreCase(name))
            {
                return false;
            }
        }
        return true;
    }



    //<--- Adding Food --->
    boolean IsSameFoodInSameCategory(String name, String category, int id)
    {
        for(Food f : FoodList)
        {
            if(f.getRestaurantId() == id && f.getName().equalsIgnoreCase(name) && f.getCategory().equalsIgnoreCase(category))
            {
                return true;
            }
        }
        return false;
    }


    //<-- file -->
    void WriteTheRestaurantList(String name)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(name));) {
            for(String line: RestaurantString){
                bw.write(line);
                bw.write(System.lineSeparator());
            }

        } catch (Exception e) {
            
        }

    }

    void WriteTheMenuList(String name)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(name));) {
            for(String line: FoodString){
                bw.write(line);
                bw.write(System.lineSeparator());
            }

        } catch (Exception e) {
            
        }

    }


}
