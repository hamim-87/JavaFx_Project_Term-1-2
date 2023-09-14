package Controllers;

import Client.Client;
import Client.OrderCompleted;
import Client.RestaurantClient;
import Comunications.FileOperation;
import DataBaseSystem.ClientFood;
import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    @FXML
    private TextField cat;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    private OrderCompleted orderCompleted;

    @FXML
    private Label Username;

    public void setUsername(String username) {
        Username.setText(username);
    }



    @FXML
    void AddFood(ActionEvent event) throws IOException {
        String foodname = name.getText();
        String foodprice = price.getText();
        String foodcat = cat.getText();

        name.setText("");
        cat.setText("");
        price.setText("");

        String id = Integer.toString(restaurant.getId());
        String line = id+foodcat+foodname+foodprice;

        FileOperation FO = new FileOperation();
        FO.setFoodString(line);
        Food newFood = new Food(Integer.parseInt(id),foodcat,foodname,Double.parseDouble(foodprice));
        restaurant.setFoodList(newFood);
        main.ShowAlert("Food Added",foodname +" is added to the Restaurant","Successfully added" );
        showFoodlist();




    }
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

    public void CompleteTheOrder(ClientFood food) throws IOException {
        System.out.println(food.getFood().getName());
        orderGrid.getChildren().clear();
        if(clientFoodList.contains(food))
        {
            clientFoodList.remove(food);
        }
        ShowOrders();
    }

    public void ShowOrders() throws IOException {

        if(clientFoodList.size()>0) {
            int colom = 0, row = 0;

            orderCompleted = new OrderCompleted() {
                @Override
                public void CompletedClientFood(ClientFood food) throws IOException {
                    CompleteTheOrder(food);
                }
            };



            for (int i = 0; i < clientFoodList.size(); i++) {
                if(clientFoodList.get(i).getFood().getRestaurantId() == restaurant.getId())
                {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/FxmlFiles/showRestaurantTheOrderCard.fxml"));


                    AnchorPane anchorPane = fxmlLoader.load();

                    showRestaurantTheOrderController controller = fxmlLoader.getController();

                    controller.setDataForOrders(clientFoodList.get(i),orderCompleted);
                    System.out.println("Restaurant home page" + clientFoodList.get(i).getUsername());


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
        }
    }

    public void init() throws IOException {
        showFoodlist();
        ShowOrders();

    }



}
