package Controllers;

import Comunications.FileOperation;
import DataBaseSystem.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RestaurantViewController implements Initializable {

    @FXML
    private GridPane gridRestaurant;

    @FXML
    private ScrollPane ScrollRestaurant;

    private List<Restaurant> RestaurantList = new ArrayList<>();

    public  void setRestaurantList(List<Restaurant> restaurantList)
    {
        this.RestaurantList = restaurantList;
    }

    public List<Restaurant> getRestaurantList()
    {
        return RestaurantList;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Test
        FileOperation Fo = new FileOperation();
        try {
            setRestaurantList(Fo.ReadFileForRestaurant());



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //main part

        try {
            int row = 0;
            int colom = 0;
            for (int i = 0;i<RestaurantList.size();i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/FxmlFiles/Restaurant.fxml"));


                AnchorPane anchorPane = fxmlLoader.load();


                RestaurantCardController restaurantCardController = fxmlLoader.getController();
                restaurantCardController.setData(RestaurantList.get(i));

                gridRestaurant.add(anchorPane,colom,row++);
                GridPane.setMargin(anchorPane,new Insets(10));


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }
}
