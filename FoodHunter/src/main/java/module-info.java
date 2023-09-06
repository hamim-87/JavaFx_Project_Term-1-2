module com.example.foodhunter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.foodhunter to javafx.fxml;
    //exports com.example.foodhunter;
}