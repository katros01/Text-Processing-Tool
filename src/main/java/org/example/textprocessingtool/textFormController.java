package org.example.textprocessingtool;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class textFormController {

    @FXML
    private BorderPane container;

    private collectionsController collectionsController;

    @FXML
    void initialize() {
        loadPage("/org/example/textprocessingtool/textProcessingFXML.fxml");
    }



    @FXML
    void openDataProcessing(MouseEvent event) {
        loadPage("/org/example/textprocessingtool/collectionsFXML.fxml");

    }

    @FXML
    void openTextProcessing(MouseEvent event) {
        loadPage("/org/example/textprocessingtool/textProcessingFXML.fxml");

    }

    public void loadPage(String page){

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        container.setCenter(root);

    }

}
