package Controllers;

import Client.Main;
import Client.MyListListener;
import Comunications.FileOperation;
import Comunications.LoginDataTransferObject;
import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController {
    @FXML
    private Button ByNameRestaurant;

    @FXML
    private Button CatByFood;

    @FXML
    private Button CatInGivenResByFood;

    @FXML
    private Button CatWiseResByRestaurant;

    @FXML
    private Button CategoryByRestaurant;

    @FXML
    private Button NameByFood;

    @FXML
    private Button NameInGivenResByFood;

    @FXML
    private Button PriceRangeByFood;

    @FXML
    private Button PriceRangeGIvenResByFood;

    @FXML
    private Button ScoreByRestaurant;

    @FXML
    private ScrollPane ScrollRestaurant;

    @FXML
    private Button TotalFoodAndResByFood;

    @FXML
    private Label Username;

    @FXML
    private Button ZipCodeByRestaurant;

    @FXML
    private GridPane gridRestaurant;

    @FXML
    private GridPane Foodgrid;

    private MyListListener myListListener;

    private Main main;


    @FXML
    private AnchorPane RestaurantCard;
    private List<Restaurant> RestaurantList = new ArrayList<>();


    //FOOD
    private List<Food> FoodList = new ArrayList<>();

    private LoginDataTransferObject LogInDTO = new LoginDataTransferObject();

    public void setLogInDTO(LoginDataTransferObject logInDTO)
    {
        this.LogInDTO = logInDTO;
    }

    public void setUsername(String Name)
    {
        Username.setText(Name);
    }




    public void setChosenRestaurant(Restaurant restaurant) throws IOException {
        //System.out.println("hehe");
        Foodgrid.getChildren().clear();
        FoodList.clear();
        updateFoodList(restaurant.getFoodList());
        showFoods();
    }

    public void updateRestaurantList(List<Restaurant> restaurantList)
    {
        this.RestaurantList = restaurantList;
    }

    public void updateFoodList(List<Food> FoodList)
    {
        this.FoodList = FoodList;
    }

    public void ShowRestaurants()
    {
//        FileOperation Fo = new FileOperation();
//        try {
//            setRestaurantList(Fo.ReadFileForRestaurant());
//
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        //main part
        //RestaurantList = LogInDTO.getRestaurantList();



        System.out.println(RestaurantList);

        if(RestaurantList.size()>0)
        {
            myListListener = new MyListListener() {
                @Override
                public void onclickRestaurantListener(Restaurant restaurant) throws IOException {
                    setChosenRestaurant(restaurant);
                }
            };
        }

        try {
            int row = 0;
            int colom = 0;
            for (int i = 0;i<RestaurantList.size();i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/FxmlFiles/Restaurant.fxml"));


                AnchorPane anchorPane = fxmlLoader.load();


                RestaurantCardController restaurantCardController = fxmlLoader.getController();
                restaurantCardController.setData(RestaurantList.get(i),myListListener);

                AnchorPane dummy = new AnchorPane();
                dummy.setMaxWidth(400);
                dummy.setMaxHeight(10);


                if(i == 0)
                {
                    gridRestaurant.add(dummy,colom,row++);
                }

                gridRestaurant.add(anchorPane,colom,row++);

                //width
                gridRestaurant.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridRestaurant.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridRestaurant.setMaxWidth(Region.USE_PREF_SIZE);

                //height
                gridRestaurant.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridRestaurant.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridRestaurant.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane,new Insets(10));



            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void showFoods() throws IOException {
//        FileOperation fo = new FileOperation();
//
//        FoodList = fo.ReadFileForMenu();

        //FoodList = LogInDTO.getFoodList();




        int colom = 0,row = 0;
        for (int i = 0;i<FoodList.size();i++)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FxmlFiles/Food.fxml"));


            AnchorPane anchorPane = fxmlLoader.load();

            FoodCardController foodCardController = fxmlLoader.getController();

            foodCardController.setDataForFood(FoodList.get(i));

            AnchorPane dummy1 = new AnchorPane();
            dummy1.setMaxWidth(260);
            dummy1.setMaxHeight(10);

            AnchorPane dummy2 = new AnchorPane();
            dummy2.setMaxWidth(260);
            dummy2.setMaxHeight(10);



            if(i == 0)
            {
                Foodgrid.add(dummy1,colom++,row);
                Foodgrid.add(dummy2,colom++,row);
            }
            if(colom%2 == 0)
            {
                colom = 0;
                row++;
            }
            Foodgrid.add(anchorPane,colom++,row);

            //width
            Foodgrid.setMinWidth(Region.USE_COMPUTED_SIZE);
            Foodgrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            Foodgrid.setMaxWidth(Region.USE_PREF_SIZE);

            //height
            Foodgrid.setMinHeight(Region.USE_COMPUTED_SIZE);
            Foodgrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            Foodgrid.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane,new Insets(10));


        }

    }


    public void init() {
        //Test



        System.out.println("home page");
        ShowRestaurants();
        try {
            showFoods();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void setMain(Main main) {
        this.main = main;
    }
}
