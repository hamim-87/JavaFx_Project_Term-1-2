package Controllers;

import Comunications.FileOperation;
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

public class HomePageController implements Initializable {
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

                AnchorPane dummy = new AnchorPane();
                dummy.setMaxWidth(400);
                dummy.setMaxHeight(70);


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

                GridPane.setMargin(anchorPane,new Insets(20));



            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }
}
