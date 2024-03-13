module org.example.a7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.a7 to javafx.fxml;
    opens DTOs to javafx.base;
    exports org.example.a7;
    exports PorgramState;
    opens PorgramState to javafx.fxml;
}