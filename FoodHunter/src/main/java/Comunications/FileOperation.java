package Comunications;

import DataBaseSystem.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class FileOperation {
    private static final String INPUT_FILE_RESTAURANT = "restaurant.txt";
    private static final String INPUT_FILE_MENU = "menu.txt";

    private static final String PASSWORD_FILE = "password.txt";

    private List<Restaurant> RestaurantList = new ArrayList<Restaurant>();

    private List<String> RestaurantString = new ArrayList<String>();

    public List<Restaurant> ReadFileForRestaurant() throws IOException {
        BufferedReader RestaurantBr = new BufferedReader(new FileReader(INPUT_FILE_RESTAURANT));
            RestaurantDatabaseSystem rds = new RestaurantDatabaseSystem();
            while(true)
            {
                String line = RestaurantBr.readLine();
                if(line == null) break;

                rds.AddRestaurant(line);
            }
            RestaurantBr.close();
            BufferedReader FoodBr = new BufferedReader(new FileReader(INPUT_FILE_MENU));


        List<Restaurant> RestaurantList = rds.getRestaurantList();

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

    public List<Food> ReadFileForMenu() throws IOException {
        BufferedReader FoodBr = new BufferedReader(new FileReader(INPUT_FILE_MENU));

        RestaurantDatabaseSystem rds = new RestaurantDatabaseSystem();

            while(true)
            {
                String line = FoodBr.readLine();
                if(line == null) break;
                rds.addMenu(line);
            }
            FoodBr.close();

            return rds.getFoodList();
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
