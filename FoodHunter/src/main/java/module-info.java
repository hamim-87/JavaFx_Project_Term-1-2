module MyjFx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    //opens Client to javafx.fxml;
    exports Client;

    exports Controllers;
    opens Controllers to javafx.fxml;
}