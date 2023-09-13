package Controllers;

import Client.Main;
import Client.MyFoodListener;
import Client.MyListListener;
import Comunications.FileOperation;
import Comunications.LoginDataTransferObject;
import DataBaseSystem.ClientFood;
import DataBaseSystem.Food;
import DataBaseSystem.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController {
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

    @FXML
    private GridPane Foodgrid;


    @FXML
    private TextField Search;

    private MyListListener myListListener;

    private Main main;

    private MyFoodListener foodListener;


    @FXML
    private AnchorPane RestaurantCard;
    private List<Restaurant> RestaurantList = new ArrayList<>();


    //FOOD
    private List<Food> FoodList = new ArrayList<>();

    private LoginDataTransferObject LogInDTO = new LoginDataTransferObject();

    public void setLogInDTO(LoginDataTransferObject logInDTO)
    {
        this.LogInDTO = logInDTO;
    }

    public void setUsername(String Name)
    {
        Username.setText(Name);
    }






    public void setChosenRestaurant(Restaurant restaurant) throws IOException {
        //System.out.println("hehe");
        Foodgrid.getChildren().clear();

        updateFoodList(restaurant.getFoodList());
        showFoods();
    }

    public void updateRestaurantList(List<Restaurant> restaurantList)
    {

        this.RestaurantList = restaurantList;
    }

    public void updateFoodList(List<Food> FoodList)
    {
        this.FoodList = FoodList;
    }

    public void ShowRestaurants()
    {
//        FileOperation Fo = new FileOperation();
//        try {
//            setRestaurantList(Fo.ReadFileForRestaurant());
//
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        //main part
        //RestaurantList = LogInDTO.getRestaurantList();



        System.out.println(RestaurantList);

        if(RestaurantList.size()>0)
        {
            myListListener = new MyListListener() {
                @Override
                public void onclickRestaurantListener(Restaurant restaurant) throws IOException {
                    setChosenRestaurant(restaurant);
                }
            };
        }

        try {
            int row = 0;
            int colom = 0;
            for (int i = 0;i<RestaurantList.size();i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/FxmlFiles/Restaurant.fxml"));


                AnchorPane anchorPane = fxmlLoader.load();


                RestaurantCardController restaurantCardController = fxmlLoader.getController();
                restaurantCardController.setData(RestaurantList.get(i),myListListener);

                AnchorPane dummy = new AnchorPane();
                dummy.setMaxWidth(400);
                dummy.setMaxHeight(10);


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

                GridPane.setMargin(anchorPane,new Insets(10));



            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ClientFood> OrderedFood = new ArrayList<>();
    public String RestaurantName(int id)
    {
        for(Restaurant r : LogInDTO.getRestaurantList())
        {
            if(r.getId() == id)
            {
                return r.getName();
            }
        }
        return "notFound";
    }
    public  void setChosenFood(Food food)
    {
        System.out.println(food.getName());

        //
        ClientFood Chosenfood = new ClientFood();
        Chosenfood.setFood(food);
        Chosenfood.setRestaurantName(RestaurantName(food.getRestaurantId()));
        Chosenfood.setUsername(Username.getText());
        OrderedFood.add(Chosenfood);


    }


    public void showFoods() throws IOException {
//        FileOperation fo = new FileOperation();
//
//        FoodList = fo.ReadFileForMenu();

        //FoodList = LogInDTO.getFoodList();

        if(FoodList.size()>0)
        {
            foodListener = new MyFoodListener() {
                @Override
                public void onclickFoodListener(Food food) {
                    setChosenFood(food);
                }
            };
        }


        int colom = 0,row = 0;
        System.out.println(FoodList);
        for (int i = 0;i<FoodList.size();i++)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FxmlFiles/Food.fxml"));


            AnchorPane anchorPane = fxmlLoader.load();

            FoodCardController foodCardController = fxmlLoader.getController();

            foodCardController.setDataForFood(FoodList.get(i),foodListener);
            //System.out.println(FoodList.get(i).getName());

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


    public void init() {
        //Test



        System.out.println("home page");
        ShowRestaurants();
        try {
            showFoods();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void setMain(Main main) {
        this.main = main;
    }



    //on action event

    public void RestaurantByName(ActionEvent actionEvent) throws IOException {
        String name = Search.getText();

        //sout
        System.out.println(name);
        if(name == "" || name == null) return;

        List<Restaurant> Temp = new ArrayList<>();
        for(int i=0;i<LogInDTO.getRestaurantList().size();i++)
        {
            System.out.println(LogInDTO.getRestaurantList().get(i).getName());
            if(LogInDTO.getRestaurantList().get(i).getName().toLowerCase().contains(name))
            {
                Temp.add(LogInDTO.getRestaurantList().get(i));
            }
        }


        updateRestaurantList(Temp);
        gridRestaurant.getChildren().clear();

        ShowRestaurants();
        updateFoodList(Temp.get(0).getFoodList());
        showFoods();
        Search.setText("");
    }

    public void RestaurantByScore(ActionEvent actionEvent) throws IOException {
        String line = Search.getText();
        String[] dig = line.split(",",-1);
        if(dig[0]=="" || dig[1] == "") {
            main.ShowAlert("inconvenience", "Incorrect way to Search", "Try to Search (Initial Range,Final Range");

        }else {

            double num1 = Double.parseDouble(dig[0]);
            double num2 = Double.parseDouble(dig[1]);

            List<Restaurant> list = new ArrayList<Restaurant>();

            for(Restaurant r : LogInDTO.getRestaurantList())
            {
                double score = r.getScore();
                if(num1<= score && num2>= score)
                {
                    list.add(r);
                }
            }

            updateRestaurantList(list);
            gridRestaurant.getChildren().clear();

            ShowRestaurants();
            updateFoodList(list.get(0).getFoodList());
            showFoods();
            Search.setText("");
        }

    }

    public void RestaurantByCategory(ActionEvent actionEvent) throws IOException {
        String cat = Search.getText();
        List<Restaurant> list = new ArrayList<Restaurant>();
        for(Restaurant r : LogInDTO.getRestaurantList())
        {
            for(int i = 0;i< r.getCategorySize();i++)
            {
                if(r.getCategoriese()[i].toLowerCase().contains(cat))
                {
                    list.add(r);
                }
            }
        }

        updateRestaurantList(list);
        gridRestaurant.getChildren().clear();

        ShowRestaurants();
        updateFoodList(list.get(0).getFoodList());
        showFoods();
        Search.setText("");
    }

    public void RestaurantByZipcode(ActionEvent actionEvent) throws IOException {

        String Zipcode = Search.getText();
        List<Restaurant> list = new ArrayList<Restaurant>();
        for(Restaurant r : LogInDTO.getRestaurantList())
        {

            if(r.getZipCode().toLowerCase().contains(Zipcode))
            {
                list.add(r);
            }
        }

        updateRestaurantList(list);
        gridRestaurant.getChildren().clear();

        ShowRestaurants();
        updateFoodList(list.get(0).getFoodList());
        showFoods();
        Search.setText("");


    }

    public void CatWiseRestaurant(ActionEvent actionEvent) {

    }

    public void FoodName(ActionEvent actionEvent) throws IOException {
        String name = Search.getText();

        List<Food> FList = new ArrayList<Food>();
        List<Restaurant> Rlist = new ArrayList<>();

        for(Restaurant r : LogInDTO.getRestaurantList())
        {
           for(Food f : r.getFoodList())
           {
               if(f.getName().toLowerCase().contains(name.toLowerCase()))
               {
                   //
                   System.out.println(f.getName());
                   FList.add(f);
                   if(!Rlist.contains(r))
                        Rlist.add(r);
               }
           }
        }
        updateRestaurantList(Rlist);
        gridRestaurant.getChildren().clear();

        ShowRestaurants();
        updateFoodList(FList);
        Foodgrid.getChildren().clear();
        showFoods();
        Search.setText("");


    }

    public void NameGivenRes(ActionEvent actionEvent) throws IOException {
        String line = Search.getText();
        String[] word = line.split(",",-1);
        if(word[1] == "" || word[0] =="")
        {
            main.ShowAlert("inconvenience","Incorrect way to Search","Try to Search (Restaurant Name,Food name)");
        }
        else{
            List<Food> flist = new ArrayList<Food>();
            List<Restaurant> rlist = new ArrayList<>();
            for(Restaurant r: LogInDTO.getRestaurantList())
            {
                if(r.getName().toLowerCase().contains(word[0].toLowerCase()))
                {
                    rlist.add(r);
                    for(Food f : LogInDTO.getFoodList())
                    {
                        if(f.getName().toLowerCase().contains(word[1].toLowerCase()))
                        {
                            flist.add(f);
                        }
                    }
                }
            }
            updateRestaurantList(rlist);
            gridRestaurant.getChildren().clear();

            ShowRestaurants();
            updateFoodList(flist);
            Foodgrid.getChildren().clear();
            showFoods();
            Search.setText("");

        }
    }

    public void foodCat(ActionEvent actionEvent) throws IOException {
        String category = Search.getText();

        if(category == "")
        {
            return;
        }else
        {
            List<Food> flist = new ArrayList<Food>();
            List<Restaurant> rlist = new ArrayList<>();
            for(Restaurant r : LogInDTO.getRestaurantList())
            {
                for(int i = 0;i<r.getFoodList().size();i++)
                {
                    if(r.getFoodList().get(i).getCategory().toLowerCase().contains(category.toLowerCase()))
                    {
                        flist.add(r.getFoodList().get(i));
                        if(!rlist.contains(r))
                            rlist.add(r);
                    }
                }
            }
            updateRestaurantList(rlist);
            gridRestaurant.getChildren().clear();

            ShowRestaurants();
            updateFoodList(flist);
            Foodgrid.getChildren().clear();
            showFoods();
            Search.setText("");


        }
    }

    public void CatInGivenRes(ActionEvent actionEvent) throws IOException {
        String line = Search.getText();
        String[] word = line.split(",",-1);
        if(word[1] == "" || word[0] =="")
        {
            main.ShowAlert("inconvenience","Incorrect way to Search","Try to Search (Restaurant Name,Catagory)");

        }else{
            List<Food> flist = new ArrayList<Food>();
            List<Restaurant> rlist = new ArrayList<>();
            for(Restaurant r : LogInDTO.getRestaurantList())
            {
               if(r.getName().toLowerCase().contains(word[0].toLowerCase()))
               {
                   for(int i = 0;i<r.getFoodList().size();i++)
                   {
                       if(r.getFoodList().get(i).getCategory().toLowerCase().contains(word[1].toLowerCase()))
                       {
                           flist.add(r.getFoodList().get(i));
                           if(!rlist.contains(r))
                               rlist.add(r);
                       }
                   }
               }
            }
            updateRestaurantList(rlist);
            gridRestaurant.getChildren().clear();

            ShowRestaurants();
            updateFoodList(flist);
            Foodgrid.getChildren().clear();
            showFoods();
            Search.setText("");
        }
    }

    public void PriceRangeInGivenRes(ActionEvent actionEvent) throws IOException {
        String line = Search.getText();
        String[] dig = line.split(",",-1);
        if(dig[1]=="" || dig[2] == "") {
            main.ShowAlert("inconvenience", "Incorrect way to Search", "Try to Search (Restaurant,Initial Range,Final Range");

        }else {
            double min = Double.parseDouble(dig[1]);
            double max = Double.parseDouble(dig[2]);

            List<Food> list = new ArrayList<Food>();
            List<Food> flist = new ArrayList<Food>();
            List<Restaurant> rlist = new ArrayList<>();
            for(Restaurant r : LogInDTO.getRestaurantList())
            {

                    if(r.getName().toLowerCase().contains(dig[0].toLowerCase()))
                    {
                        for(int i = 0;i<r.getFoodList().size();i++)
                        {
                            if(r.getFoodList().get(i).getPrice()>=min && r.getFoodList().get(i).getPrice() <= max)
                            {
                                flist.add(r.getFoodList().get(i));
                                if(!rlist.contains(r))
                                    rlist.add(r);
                            }
                        }
                    }

            }
            updateRestaurantList(rlist);
            gridRestaurant.getChildren().clear();

            ShowRestaurants();
            updateFoodList(flist);
            Foodgrid.getChildren().clear();
            showFoods();
            Search.setText("");
        }



    }

    public void PriceRangeFood(ActionEvent actionEvent) throws IOException {
        String line = Search.getText();
        String[] dig = line.split(",",-1);
        if(dig[1]=="" || dig[0] == "") {
            main.ShowAlert("inconvenience", "Incorrect way to Search", "Try to Search (Initial Range,Final Range");

        }else {
            double min = Double.parseDouble(dig[0]);
            double max = Double.parseDouble(dig[1]);

            List<Food> list = new ArrayList<Food>();
            List<Food> flist = new ArrayList<Food>();
            List<Restaurant> rlist = new ArrayList<>();
            for(Restaurant r : LogInDTO.getRestaurantList())
            {


                    for(int i = 0;i<r.getFoodList().size();i++)
                    {
                        if(r.getFoodList().get(i).getPrice()>=min && r.getFoodList().get(i).getPrice() <= max)
                        {
                            flist.add(r.getFoodList().get(i));
                            if(!rlist.contains(r))
                                rlist.add(r);
                        }
                    }


            }
            updateRestaurantList(rlist);
            gridRestaurant.getChildren().clear();

            ShowRestaurants();
            updateFoodList(flist);
            Foodgrid.getChildren().clear();
            showFoods();
            Search.setText("");
        }
    }

    public void TotalFoodAndRest(ActionEvent actionEvent) {
    }

    public void ShowOrders(MouseEvent mouseEvent) throws IOException {
        System.out.println("orderList");

        //System.out.println(OrderedFood);

        main.ShowOrders(OrderedFood );


    }
}
