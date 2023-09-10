package Controllers;

import DataBaseSystem.Restaurant;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RestaurantCardController {
    @FXML
    private ImageView CardImage;

    @FXML
    private VBox RestaurantCard;

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

    Restaurant Restaurant;

    public static String ImagePath(String name)
    {
        String imgsrc ="/Image/"+name+".png";
        return imgsrc;
    }

    public void setData(Restaurant restaurant)
    {
        this.Restaurant = restaurant;
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
