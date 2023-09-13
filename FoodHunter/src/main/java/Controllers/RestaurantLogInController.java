package Controllers;

import Client.Main;
import Client.RestaurantClient;
import Comunications.LoginDataTransferObject;
import Comunications.RestaurantLoginInfo;
import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantLogInController {

    private RestaurantClient main;
    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;







    private HomePageController homePageController = new HomePageController();


    public void loginAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String name = userText.getText();
        String password = passwordText.getText();

        RestaurantLoginInfo restaurantLogInfo = new RestaurantLoginInfo();
        restaurantLogInfo.setUserName(name);
        restaurantLogInfo.setPassword(password);

        main.getNetwork().write(restaurantLogInfo);
        System.out.println("User login info send to server...");

//        Object o = main.getNetwork().read();
//
//        if(o instanceof LoginDataTransferObject)
//        {
//
//
//
//            main.ShowAlert("Incorrect","Incorrect","Username or Password wrong");
//        }
//        else if(o instanceof List<?>)
//        {
//            List<?> list = (List<?>) o;
//            if(list.get(0) instanceof Food)
//            {
//                FoodList = (List<Food>) o;
//                main.setExtractedFoodList(FoodList);
//
//
//            }
//            else{
//                RestaurantList = (List<Restaurant>) o;
//                main.SetExtractedRestaurant(RestaurantList);
//
//                System.out.println(RestaurantList);
//                main.ShowHomePage();
//            }
//        }
//        else{
//
//
//
//
//        }

    }


    public void setMain(RestaurantClient main)
    {
        this.main = main;
    }
}
