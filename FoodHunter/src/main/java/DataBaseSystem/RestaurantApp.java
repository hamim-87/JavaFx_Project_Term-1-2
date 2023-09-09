package DataBaseSystem;

import java.util.Scanner;


import java.util.List;

public class RestaurantApp {

    private static final String INPUT_FILE_RESTAURANT = "restaurant.txt";
    private static final String INPUT_FILE_MENU = "menu.txt";

    
    

    public static void main(String[] args) {
        
        RestaurantDatabaseSystem rds = new RestaurantDatabaseSystem();
        RestaurantApp app = new RestaurantApp();

//
//        rds.OpenFileForRestaurant(INPUT_FILE_RESTAURANT);
//
//        rds.OpenFileForMenu(INPUT_FILE_MENU);
        
        

        Scanner scanner = new Scanner(System.in);
        int choice,option;
        double num1,num2;
        String input_text;
        do {
            //showing Main menu
            System.out.println("Main Menu:");
            System.out.println("1) Search Restaurants");
            System.out.println("2) Search Food Items");
            System.out.println("3) Add Restaurant");
            System.out.println("4) Add Food Item to the Menu");
            System.out.println("5) Exit System");
            
            System.out.println();
            System.out.print("Enter option: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume  character
            String input;
            switch (choice) {
                case 1: 
                    do{
                        System.out.println("1) By Name");
                        System.out.println("2) By Score");
                        System.out.println("3) By Category");
                        System.out.println("4) By Price");
                        System.out.println("5) By Zip COde");
                        System.out.println("6) Different Category Wise List of Restaurants");
                        System.out.println("7) Back to Main Menu");

                        System.out.println();
                        System.out.print("Enter option: ");

                        option = scanner.nextInt();
                        scanner.nextLine();
                        switch (option) {
                            case 1: 
                                System.out.print("Enter Name: ");
                                input = scanner.nextLine();
                                int IndexOfRestaurant = rds.FindRestaurantsByName(input);
                                if(IndexOfRestaurant == -1)
                                {
                                    System.out.println("No such restaurant with this name");
                                }
                                else
                                {
                                    Restaurant SearchedRestaurant = rds.GetSearchedRestaurant(IndexOfRestaurant);
                                    List<Food> FoodOfSearchedRestaurants = rds.CollectFoodMenu(SearchedRestaurant.getId());
                                    app.ShowDetailsOfRestaurant(SearchedRestaurant);
                                    app.ShowDetailsOfFood(FoodOfSearchedRestaurants);
                                }
                                
                                break;
                            case 2:
                                
                               System.out.print("Enter Initial Number: ");
                               num1 =scanner.nextDouble();
                               System.out.print("Enter Final Number: ");
                               num2 =scanner.nextDouble();

                               List<Restaurant> SearchedRestaurants = rds.GetRestaurantByScore(num1, num2);
                               if(SearchedRestaurants.size() == 0)
                               {
                                    System.out.println("No such restaurant with this score range.");
                               }
                               else{
                                    for(Restaurant r : SearchedRestaurants)
                                    {
                                        app.ShowDetailsOfRestaurant(r);
                                    }
                               }
                                

                                break;
                            case 3:
                               System.out.print("Enter a Category: ");
                               input_text = scanner.nextLine();
                               List<Restaurant> SearchedRestaurantsByCategory = rds.GetRestaurantByCategoty(input_text);
                               if(SearchedRestaurantsByCategory.size() == 0)
                               {
                                    System.out.println("No such restaurant with this category.");
                               }
                               else{
                                    for(Restaurant r : SearchedRestaurantsByCategory)
                                    {
                                        app.ShowDetailsOfRestaurantByCategory(r);
                                    }
                               }


                                break;
                            case 4:
                                System.out.print("Enter Price: ");
                                input_text = scanner.nextLine();
                                List<Restaurant> SearchedRestaurantsByPrice = rds.GetRestaurantByPrice(input_text);
                                if(SearchedRestaurantsByPrice.size() == 0)
                               {
                                    System.out.println("No such restaurant with this price.");
                               }
                               else{
                                    for(Restaurant r : SearchedRestaurantsByPrice)
                                    {
                                        app.ShowDetailsOfRestaurantByPrice(r);
                                    }
                               }
                               
                                break;
                            case 5:
                                System.out.print("Enter Zip Code: ");
                                input_text = scanner.nextLine();
                                List<Restaurant> SearchedRestaurantsByZipCode = rds.GetRestaurantByZipCode(input_text);
                                if(SearchedRestaurantsByZipCode.size() == 0)
                               {
                                    System.out.println("No such restaurant with this Zip Code.");
                               }
                               else{
                                    for(Restaurant r : SearchedRestaurantsByZipCode)
                                    {
                                        app.ShowDetailsOfRestaurantByZipCode(r);
                                    }
                               }
                                break;
                            case 6:
                               List<RestaurantCatagory> AllCategories = rds.getRestaurantCatergoryList();
                               app.ShowCategoty(AllCategories);

                                break;
                            case 7:
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                        System.out.println();


                    }while (option != 7);
                        
                    

                    break;

                case 2:
                    
                    do{
                        System.out.println("1) By Name");
                        System.out.println("2) By Name in a Given Restaurany");
                        System.out.println("3) By Category");
                        System.out.println("4) By Category in a Given Restaurant");
                        System.out.println("5) Price Range");
                        System.out.println("6) By Price Range in a Given Restaurant");
                        System.out.println("7) Costilest Food Item(s) on the Menu in a Given Restaurant");
                        System.out.println("8) List if Restaurants and Total Food Item on the Menu");
                        System.out.println("9) Back to Main Menu");

                        System.out.println();
                        System.out.print("Enter Option: ");
                        option = scanner.nextInt();
                        scanner.nextLine(); // consume line

                            switch (option){
                                case 1:
                                    System.out.print("Enter Food Name: ");
                                    input_text = scanner.nextLine();
                                    List<Food> SerchedFoodList = rds.getFoodByName(input_text);
                                    if(SerchedFoodList.size() == 0)
                                    {
                                         System.out.println("No such food itme with this name");
                                    }
                                    else{
                                        app.ShowFoodByName(SerchedFoodList);
                                    }
                                    break;
                                case 2:
                                    System.out.print("Enter Food Name: ");
                                    input = scanner.nextLine();
                                    System.out.print("Enter Restaurant Name: ");
                                    input_text = scanner.nextLine();
                                    int RestaurantIndex = rds.FindRestaurantsByName(input_text);
                                    if(RestaurantIndex == -1)
                                    {
                                        System.out.println("No such food item with this name on the menu of this restaurant");

                                    }
                                    else{
                                        Restaurant SearchedRestaurant = rds.GetSearchedRestaurant(RestaurantIndex);
                                        List<Food> FoodList = rds.getFoodByNameAndRestaurant(input, SearchedRestaurant.getId() );
                                        if(FoodList.size() == 0)
                                        {
                                             System.out.println("No such food item with this name on the menu of this restaurant");
                                        }
                                        else{
                                            app.ShowDetailsOfFood(FoodList);
                                        }
                                    }
                                    break;
                                case 3:
                                    System.out.print("Enter Category: ");
                                    input_text = scanner.nextLine();
                                    List<Food> fl = rds.getFoodByCategory(input_text);
                                    if(fl.size() == 0)
                                    {
                                        System.out.println("No such food item with this category");
                                    }
                                    else{
                                        app.ShowFoodByName(fl);
                                    }
                                    break;
                                case 4:
                                    System.out.print("Enter Category: ");
                                    input = scanner.nextLine();
                                    System.out.print("Enter Restaurant Name: ");
                                    input_text = scanner.nextLine();
                                    int RestaurantInd = rds.FindRestaurantsByName(input_text);
                                    if(RestaurantInd == -1)
                                    {
                                        System.out.println("No such food item with this name on the menu of this restaurant");

                                    }
                                    else{
                                        Restaurant SearchedRestaurant = rds.GetSearchedRestaurant(RestaurantInd);
                                        List<Food> FoodList = rds.getFoodByCategoryAndRestaurant(input, SearchedRestaurant.getId() );
                                        if(FoodList.size() == 0)
                                        {
                                             System.out.println("No such food item with this name on the menu of this restaurant");
                                        }
                                        else{
                                            app.ShowFoodByName(FoodList);
                                        }
                                    }

                                    break;
                                case 5:
                                    System.out.print("Enter Initial Number: ");
                                    num1 =scanner.nextDouble();
                                    System.out.print("Enter Final Number: ");
                                    num2 =scanner.nextDouble();
                                    List<Food> FoodList = rds.getFoodByRange(num1, num2);
                                    if(FoodList.size() == 0)
                                    {
                                        System.out.println("No such food item with this price range");
                                    }
                                    else{
                                        app.ShowDetailsOfFood(FoodList);
                                    }

                                    
                                    break;
                                case 6:
                                    System.out.print("Enter Initial Number: ");
                                    num1 =scanner.nextDouble();
                                    System.out.print("Enter Final Number: ");
                                    num2 =scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.print("Enter Restaurant Name:");
                                    input = scanner.nextLine();
                                    int ResInd = rds.FindRestaurantsByName(input);
                                    if(ResInd == -1)
                                    {
                                        System.out.println("No such food item with this name on the menu of this restaurant");

                                    }
                                    else{
                                        Restaurant SearchedRestaurant = rds.GetSearchedRestaurant(ResInd);

                                        List<Food> foodList = rds.getFoodByRangeAndRestaurant(SearchedRestaurant.getId(), num1,num2);
                                        if(foodList.size() == 0)
                                        {
                                            System.out.println("No such food item with this price range on the menu of this restaurant");

                                        }
                                        else{
                                            app.ShowDetailsOfFood(foodList);
                                        }


                                    }
                                    break;
                                case 7:
                                     System.out.print("Enter Restaurant Name:");
                                    input = scanner.nextLine();
                                    int ResIndex= rds.FindRestaurantsByName(input);
                                    if(ResIndex == -1)
                                    {
                                        System.out.println("No such restaurant");

                                    }
                                    else{
                                        Restaurant SearchedRestaurant = rds.GetSearchedRestaurant(ResIndex);
                                        List<Food> foodlist = rds.FindCostliestFoodInaRestaurant(SearchedRestaurant.getId());
                                        if(foodlist.size() == 0)
                                        {
                                            System.out.println("No food in that restaurant.");
                                        }
                                        else{
                                            app.ShowDetailsOfFood(foodlist);
                                        }

                                    }

                                    
                                    break;
                                case 8:
                                System.out.println();
                                    for(Restaurant r : rds.getRestaurantList())
                                    {
                                        System.out.print(r.getName()+": ");
                                        List<Food> lf = rds.CollectFoodMenu(r.getId());
                                        System.out.print(lf.size());
                                        System.out.println();
                                    }
                                System.out.println();
                                    break;
                                case 9:
                                    break;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }
                    }while(option != 9);
                    
                    break;

                case 3: 
                    String name,price,zipcode,cat1,cat2,cat3,line;
                    double score;
                    System.out.print("Enter Restaurant Name: ");
                    name = scanner.nextLine();
                    if(!rds.isValidRestaurant(name))
                    {
                        System.out.println("This Restaurant is Already Registered!");
                        break;
                    }
                    
                    System.out.print( "Enter Restaurant Score: ");
                    score = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Restaurant Price:  ");

                    price = scanner.nextLine();

                    System.out.print("Enter Restaurant Zip Code: ");

                    zipcode = scanner.nextLine();
                    System.out.print("Enter Category1: ");
                    cat1 = scanner.nextLine();
                    System.out.print("Enter Category2: ");
                    cat2 = scanner.nextLine();
                    System.out.print("Enter Category3: ");

                    cat3 = scanner.nextLine();

                    line = (rds.getRestaurantList().size()+1)+","+name+","+score+","+price+","+zipcode+","+cat1+","+cat2+","+cat3;
                    
                    rds.AddRestaurant(line);
                    
                  

                    break;

                case 4: 
                    System.out.print("Enter Restaurant Name:");
                    input = scanner.nextLine();
                    int ResInd = rds.FindRestaurantsByName(input);
                    if(ResInd == -1)
                    {
                        System.out.println("No such  restaurant");
                        break;

                    }
                    
                    Restaurant SearchedRestaurant = rds.GetSearchedRestaurant(ResInd);

                    
                    String foodname,category;
                    double priceOfFood;
                    System.out.print("Enter Food Name: ");
                    foodname = scanner.nextLine();
                    System.out.print("Enter Category: ");
                    category = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    priceOfFood = scanner.nextDouble();
                    scanner.nextLine();

                    if(rds.IsSameFoodInSameCategory(foodname,category,SearchedRestaurant.getId()))
                    {
                            System.out.println("This Food is already Registered!");
                            break;
                    }
                    String LineOfFood = SearchedRestaurant.getId()+","+category+","+foodname+","+priceOfFood;

                    rds.addMenu(LineOfFood);
                    System.out.println();
                    System.out.println("Successfully Added!");
                    System.out.println();



                    
                    break;
                case 5:
                    rds.WriteTheRestaurantList(INPUT_FILE_RESTAURANT);
                    rds.WriteTheMenuList(INPUT_FILE_MENU);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        } while (choice != 5); 
    }
    
    public void ShowDetailsOfRestaurant(Restaurant restaurant)
    {
        
        System.out.println();
        System.out.println("Restaurant Name: " + restaurant.getName());
        System.out.println("Score          : " + restaurant.getScore());
        System.out.println("Price          : " + restaurant.getPrice());
        System.out.println("Zip Code       : "+ restaurant.getZipCode());
        System.out.println("Categories     : ");
        for(int i =0;i<restaurant.getCategorySize();i++)
        {
            System.out.println("                 "+restaurant.getCategoriese()[i]);
        }
        System.out.println();
        
    }

    public void ShowDetailsOfFood(List<Food> foodList)
    {
        System.out.println("       <--Menu-->");
        for(int i = 0;i< foodList.size();i++)
        {
            System.out.println("Food: " + foodList.get(i).getName());
            System.out.println("Category: " + foodList.get(i).getCategory());
            System.out.println("Price: " + foodList.get(i).getPrice());
            System.out.println();
        }
    }
    
    public void ShowDetailsOfRestaurantByCategory( Restaurant restaurant)
    {
        System.out.println();
        System.out.println("Restaurant Name: " + restaurant.getName());
        System.out.println("Score          : " + restaurant.getScore());
        System.out.println("Price          : " + restaurant.getPrice());
        System.out.println("Zip Code       : "+ restaurant.getZipCode());
    }

    public void ShowDetailsOfRestaurantByPrice( Restaurant restaurant)
    {
        System.out.println();
        System.out.println("Restaurant Name: " + restaurant.getName());
        System.out.println("Score          : " + restaurant.getScore());
        System.out.println("Zip Code       : "+ restaurant.getZipCode());
        System.out.println("Categories     : ");
        for(int i =0;i<restaurant.getCategorySize();i++)
        {
            System.out.println("                 "+restaurant.getCategoriese()[i]);
        }
        System.out.println();
    }

    public void ShowDetailsOfRestaurantByZipCode(Restaurant restaurant)
    {
        System.out.println();
        System.out.println("Restaurant Name: " + restaurant.getName());
        System.out.println("Score          : " + restaurant.getScore());
        System.out.println("Price          : " + restaurant.getPrice());
        System.out.println("Categories     : ");
        for(int i =0;i<restaurant.getCategorySize();i++)
        {
            System.out.println("                 "+restaurant.getCategoriese()[i]);
        }
        System.out.println();
    }

    void ShowCategoty(List<RestaurantCatagory> AllCategories)
    {
        System.out.println();
        for(RestaurantCatagory category : AllCategories)
        {
            System.out.print(category.getResCategory()+":");
            for(int i = 0;i<category.getRestaurantInThatCategory().size();i++)
            {
                System.out.print(" "+category.getRestaurantInThatCategory().get(i));
                if(i != category.getRestaurantInThatCategory().size()-1)
                {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }


    // <--- search Food Items  --->
    public void ShowFoodByName(List<Food> flist)
    {
        System.out.print("<-- Food found -->");
            System.out.println();
        for(Food f : flist){
            

            System.out.println("Food: " + f.getName());
            System.out.println("Price: " + f.getPrice());
            System.out.println();
        }

    }
      

}
