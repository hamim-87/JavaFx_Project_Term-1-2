package Client;

import DataBaseSystem.ClientFood;

import java.io.IOException;

public interface OrderCompleted {
    public void CompletedClientFood(ClientFood food) throws IOException;
}
