package Client;

import DataBaseSystem.Restaurant;

import java.io.IOException;

public interface MyListListener {
    public void onclickRestaurantListener(Restaurant restaurant) throws IOException;
}
