package org.example.textprocessingtool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessingFXML {

    @FXML
    private TextArea inputText;

    @FXML
    private TextField inputTextReplace;

    @FXML
    private TextField patternText;

    @FXML
    private TextArea resultText;



    @FXML
    void highlightButton(ActionEvent event) {

        String pattern = patternText.getText();
        String textToHighlight = inputText.getText();

        if (pattern.isEmpty()) {
            showAlert("Please enter a regex pattern.");
            return;
        }

        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(textToHighlight);

        StringBuffer highlightedText = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(highlightedText, "~$0~");
        }

        matcher.appendTail(highlightedText);

        resultText.setText(highlightedText.toString());

    }

    @FXML
    void replaceButton(ActionEvent event) {
        String pattern = patternText.getText();
        String replacement = inputTextReplace.getText();
        String textToProcess = inputText.getText();

        if (pattern.isEmpty()) {
            showAlert("Please enter a regex pattern.");
            return;
        }

        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(textToProcess);

        String replacedText = matcher.replaceAll(replacement);

        resultText.setText(replacedText);

    }

    @FXML
    void searchButton(ActionEvent event) {
        String pattern = patternText.getText();
        String textToSearch = inputText.getText();

        if (pattern.isEmpty()) {
            showAlert("Please enter a regex pattern.");
            return;
        }

        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(textToSearch);

        StringBuffer searchResults = new StringBuffer();

        while (matcher.find()) {
            searchResults.append("Word found at index ")
                    .append(matcher.start())
                    .append(" \t - ")
                    .append(matcher.group())
                    .append("\n");
        }

        resultText.setText(searchResults.toString());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
