module edu.rit.wellness_manager {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.rit.wellness_manager to javafx.fxml;
    exports edu.rit.wellness_manager;
}