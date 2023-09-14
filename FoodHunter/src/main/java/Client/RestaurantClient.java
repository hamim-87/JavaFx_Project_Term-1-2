package Client;

import Comunications.NetworkConnection;
import Comunications.RestaurantLoginInfo;
import Controllers.LoginController;
import Controllers.RestaurantHomePageController;
import Controllers.RestaurantLogInController;
import DataBaseSystem.ClientFood;
import DataBaseSystem.Restaurant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RestaurantClient extends Application {
    private Stage stage;
    private NetworkConnection network;

    private Restaurant connectedRestaurant;

    private RestaurantLoginInfo restaurantLoginInfo;

    public NetworkConnection getNetwork()
    {
        return network;
    }

    private RestaurantHomePageController homePageController;

    private List<ClientFood> ClientOrder = new ArrayList<>();

    public void setClientOrder(List<ClientFood> clientOrder) {
        ClientOrder = clientOrder;
    }

    public List<ClientFood> getClientOrder() {
        return ClientOrder;
    }

    public void OrderUpdated(List<ClientFood> list) throws IOException {

        System.out.println("Restaurant Main"+ list);

        setClientOrder(list);

        ShowRestaurantHomePage(restaurantLoginInfo);




    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        stage = primaryStage;
        System.out.println("Stage is set for Restaurant...");

        ConnectServer();
        System.out.println("Client Connected..");

        ShowLoginPage();
        System.out.println("login page loaded.");
    }

    private void ConnectServer() throws IOException {
        System.out.println("Client started..");

        Socket socket = new Socket("127.0.0.1", 80);

        network = new NetworkConnection(socket);

        new ReaderThreadOfRestaurant(this,network);


    }

    private void ShowLoginPage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FxmlFiles/RestaurantLogInPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        RestaurantLogInController resloginController = loader.getController();


        resloginController.setMain(this);




        stage.setScene(scene);
        stage.setTitle("LogIn page");
        stage.show();


    }

    public void ShowRestaurantHomePage(RestaurantLoginInfo restaurantLoginInfo) throws IOException {
        this.restaurantLoginInfo = restaurantLoginInfo;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FxmlFiles/RestaurantHomePage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        RestaurantHomePageController restaurantHomePageController = loader.getController();
        homePageController = restaurantHomePageController;

        restaurantHomePageController.setRestaurant(restaurantLoginInfo.getRestaurant());
        restaurantHomePageController.setMain(this);
        restaurantHomePageController.setUsername(restaurantLoginInfo.getRestaurant().getName());
        restaurantHomePageController.setClientFoodList(ClientOrder);
        System.out.println("SHowreshomepage.."+ClientOrder);
        restaurantHomePageController.init();


        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.show();
    }

    public void ShowAlert(String title, String contendtext,String headText)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(contendtext);
        alert.setHeaderText(headText);
        alert.showAndWait();

    }


}
