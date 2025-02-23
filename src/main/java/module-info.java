module edu.farmingdale.csc_311_mod3_group_assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens edu.farmingdale.csc_311_mod3_group_assignment to javafx.fxml;
    exports edu.farmingdale.csc_311_mod3_group_assignment;
}