package Controllers;

import Client.MyListListener;
import DataBaseSystem.Restaurant;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RestaurantCardController {
    @FXML
    private ImageView CardImage;

    @FXML
    private AnchorPane RestaurantCard;

    @FXML
    private Label RestaurantName;

    @FXML
    private Label RestaurantPrice;

    @FXML
    private Label RestaurantScore;

    @FXML
    private Label cat1;

    @FXML
    private Label cat2;

    @FXML
    private Label cat3;

    @FXML
    private Label zipcode;

    private Restaurant Restaurant;





    private MyListListener myListListener;
    public static String ImagePath(String name)
    {
        String imgsrc ="/Image/"+name+".png";
        return imgsrc;
    }
    public void clickedRestaurant(MouseEvent mouseEvent) throws IOException {
        myListListener.onclickRestaurantListener(Restaurant);
    }

    public void setData(Restaurant restaurant,MyListListener myListListener)
    {
        this.Restaurant = restaurant;
        this.myListListener = myListListener;
        RestaurantName.setText(Restaurant.getName());
        RestaurantScore.setText(Double.toString(Restaurant.getScore()));

        RestaurantPrice.setText(Restaurant.getPrice());

        cat1.setText(Restaurant.getCategoriese()[0]);

        if(Restaurant.getCategorySize()<=2)
        {
            cat2.setText(Restaurant.getCategoriese()[1]);

        }
        else{
            cat2.setText(Restaurant.getCategoriese()[1]);
            cat3.setText(Restaurant.getCategoriese()[2]);
        }

        zipcode.setText(Restaurant.getZipCode());

        String path = ImagePath(Restaurant.getName());
        Image img = new Image(getClass().getResourceAsStream(path));
        CardImage.setImage(img);


    }


}
