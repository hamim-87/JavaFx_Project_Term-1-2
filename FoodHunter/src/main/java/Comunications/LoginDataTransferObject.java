package Comunications;

import java.io.Serializable;
import java.security.PublicKey;

public class LoginDataTransferObject implements Serializable {
    private String userName;

    private String password;

    private boolean status = false;

    public void setUserName(String userName){ this.userName = userName;}

    public void setPassword(String password) { this.password = password;}

    public  void setStatus(Boolean status) {this.status = status;}

    public String getUserName() { return this.userName;}
    public String getPassword(){ return  this.password;}

    public Boolean getStatus() { return  this.status;}
}
