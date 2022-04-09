module org.fis.cinemaregistrationapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.fis.cinemaregistrationapplication to javafx.fxml;
    exports org.fis.cinemaregistrationapplication;
    exports org.fis.cinemaregistrationapplication.controllers;
    opens org.fis.cinemaregistrationapplication.controllers to javafx.fxml;
}