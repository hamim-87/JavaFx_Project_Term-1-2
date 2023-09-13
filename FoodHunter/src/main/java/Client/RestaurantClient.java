package Client;

import Comunications.NetworkConnection;
import DataBaseSystem.Restaurant;
import javafx.application.Application;
import javafx.stage.Stage;

public class RestaurantClient extends Application {
    private Stage stage;
    private NetworkConnection network;

    private Restaurant connectedRestaurant;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
