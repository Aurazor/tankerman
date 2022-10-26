module com.nikhilaukhaj.tankerman {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.nikhilaukhaj.tankerman to javafx.fxml;
    exports com.nikhilaukhaj.tankerman;
}