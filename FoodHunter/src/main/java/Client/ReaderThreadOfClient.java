package Client;

import Comunications.LoginDataTransferObject;
import Comunications.NetworkConnection;
import javafx.application.Platform;

import java.io.IOException;

public class ReaderThreadOfClient implements Runnable{


    private final Main main;

    private final Thread t;

    private NetworkConnection network;


    ReaderThreadOfClient(Main main, NetworkConnection network)
    {
        this.main = main;
        this.network = network;
        t = new Thread(this);



        t.start();
    }
    @Override
    public void run() {
        try {
            System.out.println("Waiting for Server to send something");


            while (true)
            {
                Object o = network.read();
                if(o instanceof LoginDataTransferObject)
                {
                    LoginDataTransferObject LoginDTO = new LoginDataTransferObject();
                    LoginDTO = (LoginDataTransferObject) o;
                    if(LoginDTO.getStatus())
                    {
                        System.out.println("Can log in..");
                        Platform.runLater(() -> {
                            try {
                                main.ShowHomePage();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                    else{
                        Platform.runLater(() -> main.ShowAlert("Incorrect","Incorrect","Username or Password wrong"));
                    }
                }
            }








        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
