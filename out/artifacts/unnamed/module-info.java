module com.example.tsp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tsp to javafx.fxml;
    exports com.example.tsp;
}