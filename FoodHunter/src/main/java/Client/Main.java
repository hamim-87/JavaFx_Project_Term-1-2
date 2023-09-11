package Client;

import Comunications.LoginDataTransferObject;
import Comunications.NetworkConnection;
import Controllers.HomePageController;
import Controllers.LoginController;
import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Stage stage;
    private NetworkConnection network;


    private List<Restaurant> ExtractedRestaurnt;

    public void SetExtractedRestaurant(List<Restaurant> ExtractedRestaurant)
    {
        this.ExtractedRestaurnt = ExtractedRestaurant;
    }

    public List<Restaurant> getExtractedRestaurnt() {
        return ExtractedRestaurnt;
    }

    private List<Food> ExtractedFoodList;

    public void setExtractedFoodList(List<Food> ExtractedFoodList)
    {
        this.ExtractedFoodList = ExtractedFoodList;
    }

    public List<Food> getExtractedFoodList() {
        return ExtractedFoodList;
    }

    public static void main(String[] args) {
        launch(args);
    }

    //network
    public NetworkConnection getNetwork()
    {
        return network;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        stage = primaryStage;
        System.out.println("Stage is set...");

        ConnectServer();
        System.out.println("Client Connected..");

        ShowLoginPage();
        System.out.println("login page loaded.");
//
//        ShowHomePage();
//        System.out.println("Home page loaded..");








    }

    private void ConnectServer() throws IOException {
        System.out.println("Client started..");

        Socket socket = new Socket("127.0.0.1", 80);

        network = new NetworkConnection(socket);

        new ReaderThreadOfClient(this,network);


    }
    private void ShowLoginPage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FxmlFiles/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        //controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        //image add
        //controller.init();

        stage.setScene(scene);
        stage.setTitle("Log in page...");
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

    public  void ShowHomePage(LoginDataTransferObject logInObj) throws IOException {



        System.out.println("1");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FxmlFiles/HomePage.fxml"));
        System.out.println("2");


        Parent root = loader.load();



        HomePageController controller = loader.getController();
        System.out.println("going home");
        System.out.println(logInObj.getRestaurantList().get(0).getName());

        controller.setLogInDTO(logInObj);
        controller.setMain(this);
        controller.setUsername(logInObj.getUserName());

        controller.init();








        /////


        //new ReaderThreadOfClient(this,network);


        stage.setTitle("Home in page...");
        stage.setScene(new Scene(root));
        stage.show();
    }


}
