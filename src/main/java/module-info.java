module org.example.textprocessingtool {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.textprocessingtool to javafx.fxml;
    exports org.example.textprocessingtool;
}