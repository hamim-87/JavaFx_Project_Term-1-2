package Controllers;

import Client.Main;
import Comunications.LoginDataTransferObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    private  Main main;
    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;
    public void loginAction(ActionEvent actionEvent) throws IOException {
        String name = userText.getText();
        String password = passwordText.getText();

        LoginDataTransferObject LoginObj = new LoginDataTransferObject();
        LoginObj.setUserName(name);
        LoginObj.setPassword(password);

        main.getNetwork().write(LoginObj);
        System.out.println("User login info send to server...");

    }
    public  void resetAction(ActionEvent actionEvent)
    {

    }

    public void setMain(Main main)
    {
        this.main = main;
    }
}
