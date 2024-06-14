module com.example.leaguechamps {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.leaguechamps to javafx.fxml;
    exports com.example.leaguechamps;
}