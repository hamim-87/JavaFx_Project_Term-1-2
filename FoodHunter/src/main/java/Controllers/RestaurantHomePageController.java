package Controllers;

import Client.RestaurantClient;
import DataBaseSystem.ClientFood;
import DataBaseSystem.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantHomePageController {

    @FXML
    private GridPane Foodgrid;


    @FXML
    private GridPane orderGrid;
    private RestaurantClient main;

    public void setMain(RestaurantClient main) {
        this.main = main;
    }

    public Restaurant restaurant;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    private List<ClientFood> clientFoodList = new ArrayList<>();

    public List<ClientFood> getClientFoodList() {
        return clientFoodList;
    }

    public void setClientFoodList(List<ClientFood> clientFoodList) {
        this.clientFoodList = clientFoodList;
    }

    public void showFoodlist() throws IOException {

        int colom = 0,row = 0;

        for(int i = 0;i<restaurant.getFoodList().size();i++)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FxmlFiles/RestaurantFood.fxml"));


            AnchorPane anchorPane = fxmlLoader.load();

            RestaurantFoodController restaurantFoodController= fxmlLoader.getController();

            restaurantFoodController.setDataForFood(restaurant.getFoodList().get(i));

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

    public void ShowOrders() throws IOException {
        int colom = 0,row = 0;

        System.out.println("Restaurant client"+clientFoodList);

        for(int i = 0;i<clientFoodList.size();i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FxmlFiles/showRestaurantTheOrderCard.fxml"));


            AnchorPane anchorPane = fxmlLoader.load();

            showRestaurantTheOrderController controller = fxmlLoader.getController();

            controller.setDataForOrders(clientFoodList.get(i));

            AnchorPane dummy1 = new AnchorPane();
            dummy1.setMaxWidth(260);
            dummy1.setMaxHeight(10);


            if (i == 0) {
                orderGrid.add(dummy1, colom, row++);

            }

            orderGrid.add(anchorPane, colom, row++);

            //width
            orderGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
            orderGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            orderGrid.setMaxWidth(Region.USE_PREF_SIZE);

            //height
            orderGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
            orderGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            orderGrid.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));
        }
    }

    public void init() throws IOException {
        showFoodlist();
        ShowOrders();

    }



}
