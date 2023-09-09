package Comunications;

import DataBaseSystem.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class FileOperation implements Serializable{
    private static final String INPUT_FILE_RESTAURANT = "restaurant.txt";
    private static final String INPUT_FILE_MENU = "menu.txt";

    private static final String PASSWORD_FILE = "password.txt";

    private List<Restaurant> RestaurantList = new ArrayList<Restaurant>();

    private List<String> RestaurantString = new ArrayList<String>();

    private List<String> FoodString = new ArrayList<>();

    private List<Food> FoodList = new ArrayList<>();

    public  List<Restaurant> ReadFileForRestaurant() throws IOException {
        BufferedReader RestaurantBr = new BufferedReader(new FileReader(INPUT_FILE_RESTAURANT));

            while(true)
            {
                String line = RestaurantBr.readLine();
                RestaurantString.add(line);
                if(line == null) break;

                String[] array = line.split(",",-1);
                Restaurant NewRestaurant;
                if(array[7] == "" && array[6] == "" ){
                    NewRestaurant = new Restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), array[3], array[4], array[5]);

                }
                else if(array[7]== "" )
                {
                    NewRestaurant = new Restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), array[3], array[4], array[5], array[6]);



                }
                else{
                    NewRestaurant = new Restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), array[3], array[4], array[5], array[6],array[7]);


                }
                System.out.println(NewRestaurant.getName());

                RestaurantList.add(NewRestaurant);
            }
            RestaurantBr.close();

            BufferedReader FoodBr = new BufferedReader(new FileReader(INPUT_FILE_MENU));




        while(true)
            {
                String line = FoodBr.readLine();
                if(line == null) break;

                String[] MenuDetail = line .split(",",-1);
                //Food NewMenu = new Food(Integer.parseInt(MenuDetail[0]),MenuDetail[1],MenuDetail[2],Double.parseDouble(MenuDetail[3]));
                Food newMenu = new Food(Integer.parseInt(MenuDetail[0]), MenuDetail[1], MenuDetail[2], Double.parseDouble(MenuDetail[3]));

                for (Restaurant r : RestaurantList)
                {
                    if(r.getId() == newMenu.getRestaurantId())
                    {
                        r.setFoodList(newMenu);
                        break;
                    }
                }



            }

            return  RestaurantList;

    }

    public  List<Food> ReadFileForMenu() throws IOException {
        BufferedReader FoodBr = new BufferedReader(new FileReader(INPUT_FILE_MENU));



            while(true)
            {
                String line = FoodBr.readLine();
                if(line == null) break;

                FoodString.add(line);
                String[] MenuDetail = line .split(",",-1);
                Food NewMenu = new Food(Integer.parseInt(MenuDetail[0]),MenuDetail[1],MenuDetail[2],Double.parseDouble(MenuDetail[3]));


                FoodList.add(NewMenu);
            }
            FoodBr.close();

            return FoodList;
    }



    public ConcurrentHashMap<String,String> getPassword() throws IOException {
        BufferedReader PasswordBr = new BufferedReader(new FileReader(PASSWORD_FILE));
        ConcurrentHashMap<String,String> PasswordMap = new ConcurrentHashMap<>();
        while(true)
        {
            String line = PasswordBr.readLine();
            if(line == null) break;

            String[] word = line.split(",",-1);
            PasswordMap.put(word[0],word[1]);
        }


        return PasswordMap;


    }


}
