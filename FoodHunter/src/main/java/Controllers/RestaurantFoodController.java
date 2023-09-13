
package Controllers;

        import Client.MyFoodListener;
        import Client.MyListListener;
        import Comunications.FileOperation;
        import DataBaseSystem.Food;
        import DataBaseSystem.Restaurant;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.input.MouseEvent;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

public class  RestaurantFoodController {


    @FXML
    private Label foodCat;

    @FXML
    private Label foodName;

    @FXML
    private Label foodprice;

    private Food food;

    private MyFoodListener myListListener;




    public void setDataForFood(Food food)
    {
        this.food = food;

        foodName.setText(food.getName());
        foodCat.setText(food.getCategory());
        foodprice.setText(Double.toString(food.getPrice()));



    }
}
