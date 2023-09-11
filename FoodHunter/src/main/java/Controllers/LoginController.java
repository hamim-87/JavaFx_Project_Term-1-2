package Controllers;

import Client.Main;
import Comunications.LoginDataTransferObject;
import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    private  Main main;
    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    private List<Restaurant> RestaurantList = new ArrayList<>();

    public List<Restaurant> getRestaurantList()
    {
        return RestaurantList;
    }
    private List<Food> FoodList = new ArrayList<>();

    public List<Food> getFoodList()
    {
        return FoodList;
    }

    private HomePageController homePageController = new HomePageController();


    public void loginAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String name = userText.getText();
        String password = passwordText.getText();

        LoginDataTransferObject LoginObj = new LoginDataTransferObject();
        LoginObj.setUserName(name);
        LoginObj.setPassword(password);

        main.getNetwork().write(LoginObj);
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
    public  void resetAction(ActionEvent actionEvent)
    {

    }

    public void setMain(Main main)
    {
        this.main = main;
    }
}
