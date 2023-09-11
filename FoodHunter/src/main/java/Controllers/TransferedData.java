package Controllers;

import Comunications.LoginDataTransferObject;

public class TransferedData {

    private LoginDataTransferObject LoginDTO;

    public  LoginDataTransferObject getLoginDTO()
    {
        return LoginDTO;
    }

    TransferedData(LoginDataTransferObject DTO)
    {
        this.LoginDTO = DTO;
    }
}
