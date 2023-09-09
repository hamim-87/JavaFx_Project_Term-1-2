package Controllers;

import Client.Main;
import Comunications.LoginDataTransferObject;
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
    public void loginAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String name = userText.getText();
        String password = passwordText.getText();

        LoginDataTransferObject LoginObj = new LoginDataTransferObject();
        LoginObj.setUserName(name);
        LoginObj.setPassword(password);

        main.getNetwork().write(LoginObj);
        System.out.println("User login info send to server...");

        Object o = main.getNetwork().read();

        if(o instanceof LoginDataTransferObject)
        {
            main.ShowAlert("Incorrect","Incorrect","Username or Password wrong");
        }
        else{
            RestaurantList = (List<Restaurant>) o;
            System.out.println("List Found..");
        }

    }
    public  void resetAction(ActionEvent actionEvent)
    {

    }

    public void setMain(Main main)
    {
        this.main = main;
    }
}
