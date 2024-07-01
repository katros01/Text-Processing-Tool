
package org.example.textprocessingtool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.*;

public class collectionsController {

    @FXML
    private ComboBox<String> allcreatedCollections;

    @FXML
    private TextField collectionName;

    @FXML
    private ComboBox<String> collections;

    @FXML
    private ComboBox<String> collectionsType;

    @FXML
    private TextArea resultText;

    @FXML
    private Text text;

    @FXML
    private TextField newItem;


    private Map<String, List<String>> collectionMap = new HashMap<>();
    private Map<String, List<String>> collectionArrayMap = new HashMap<>();
    List<String> arrayListCollections = new ArrayList<>();
    List<String> hashSetCollections = new ArrayList<>();
    List<String> hashMapCollections = new ArrayList<>();

    @FXML
    void initialize() {

        List<String> todo = new ArrayList<>();
        todo.add("login");
        todo.add("logout");
        todo.add("registration");
        arrayListCollections.add("todo");
        collectionMap.put("ArrayListCollection", arrayListCollections);
        collectionArrayMap.put("todo", todo);

        Set<String> task = new HashSet<>();
        task.add("login");
        task.add("logout");
        task.add("registration");
        hashSetCollections.add("task");
        collectionMap.put("HashSetCollection", hashSetCollections);
        collectionArrayMap.put("task", new ArrayList<>(task));

        Map<String, String> tickets = new HashMap<>();
        tickets.put("task1", "login");
        tickets.put("task2", "logout");
        tickets.put("task3", "registration");
        hashMapCollections.add("tickets");
        collectionMap.put("HashMapCollection", new ArrayList<>(hashMapCollections));
        collectionArrayMap.put("tickets", new ArrayList<>(tickets.values()));
        collectionsType.getItems().addAll("ArrayList", "HashSet", "HashMap");
        collections.getItems().addAll("ArrayList", "HashSet", "HashMap");
    }

    @FXML
    void collectionsTypeChanged(ActionEvent event) {
        String selectedType = collectionsType.getValue();
        if (selectedType == null || selectedType.isEmpty()) {
            return;
        }
        List<String> toDo = new ArrayList<>();
        toDo.add("toDo");
        toDo.add("tickets");
        toDo.add("task");

        if(selectedType.equals("ArrayList")) {
            List<String> collection = collectionMap.get("ArrayListCollection");
            allcreatedCollections.getItems().clear();
            allcreatedCollections.getItems().addAll(collection);
            allcreatedCollections.getSelectionModel().selectFirst();
        } else if(selectedType.equals("HashSet")) {
            List<String> collection = collectionMap.get("HashSetCollection");
            allcreatedCollections.getItems().clear();
            allcreatedCollections.getItems().addAll(collection);
            allcreatedCollections.getSelectionModel().selectFirst();
        } else if(selectedType.equals("HashMap")) {
            List<String> collection = collectionMap.get("HashMapCollection");
            allcreatedCollections.getItems().clear();
            allcreatedCollections.getItems().addAll(collection);
            allcreatedCollections.getSelectionModel().selectFirst();
        }
    }

    @FXML
    void addItem(ActionEvent event) {
        String collectionName = allcreatedCollections.getValue();
        if (collectionName == null || collectionName.isEmpty()) {
            showAlert("Please select a collection.");
            return;
        }

        List<String> collection = collectionArrayMap.get(collectionName);

        if (collection == null) {
            showAlert("Selected collection does not exist.");
            return;
        }

        collection.add(newItem.getText().trim());

        displayCollectionItems(collectionName);
    }

    @FXML
    void clearCollectionButton(ActionEvent event) {
        String collectionName = allcreatedCollections.getValue();
        if (collectionName == null || collectionName.isEmpty()) {
            showAlert("Please select a collection.");
            return;
        }

        List<String> collection = collectionArrayMap.get(collectionName);

        if (collection == null) {
            showAlert("Selected collection does not exist.");
            return;
        }

        collection.clear();

        resultText.setText("Collection cleared: " + collectionName);
    }

    @FXML
    void createCollection(ActionEvent event) {
        String newCollectionName = collectionName.getText().trim();

        if (newCollectionName.isEmpty()) {
            showAlert("Please enter a collection name.");
            return;
        }

        if (collectionMap.containsKey(newCollectionName)) {
            showAlert("Collection with this name already exists.");
            return;
        }

        String selectedType = collections.getValue();
        createNewCollection(selectedType, newCollectionName);

        showAlert("Collection created: " + newCollectionName);
    }

    @FXML
    void deleteButton(ActionEvent event) {
        String collectionName = allcreatedCollections.getValue();
        if (collectionName == null || collectionName.isEmpty()) {
            showAlert("Please select a collection to delete.");
            return;
        }

        collectionArrayMap.remove(collectionName);

        allcreatedCollections.getItems().remove(collectionName);
        collections.getItems().remove(collectionName);

        showAlert("Collection deleted: " + collectionName);
    }

    @FXML
    void displayItems(ActionEvent event) {
        String collectionName = allcreatedCollections.getValue();
        if (collectionName == null || collectionName.isEmpty()) {
            showAlert("Please select a collection to display items.");
            return;
        }

        displayCollectionItems(collectionName);
    }

    @FXML
    void editButton(ActionEvent event) {

        showAlert("Edit functionality not implemented.");
    }

    private void createNewCollection(String selectedType, String newCollectionName) {
        System.out.println(newCollectionName);
        if (selectedType.equals("ArrayList")) {
            List<String> newArray = new ArrayList<>();
            arrayListCollections.add(newCollectionName);
            collectionArrayMap.put(newCollectionName, newArray);
        } else if (selectedType.equals("HashSet")) {
            Set<String> newHash = new HashSet<>();
            hashSetCollections.add(newCollectionName);
            collectionArrayMap.put(newCollectionName, new ArrayList<>(newHash));
        } else if (selectedType.equals("HashMap")) {
            Map<String, String> newHashMap = new HashMap<>();
            hashMapCollections.add(newCollectionName);
            collectionArrayMap.put(newCollectionName, new ArrayList<>(newHashMap.values()));
        }else
            showAlert("Collection not found");

    }

    private void displayCollectionItems(String collectionName) {

        List<String> collection = collectionArrayMap.get(collectionName);
        if (collectionName != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Items in ").append(collectionName).append(":\n");
            for (String item : collection) {
                sb.append("- ").append(item).append("\n");
            }
            resultText.setText(sb.toString());
        } else {
            resultText.setText("Selected collection does not exist.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
