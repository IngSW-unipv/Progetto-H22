package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;
import view.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements Initializable, IControlledScreen {

    ScreensController myController;
    private Factory factory = Factory.getInstance();

    @FXML
    private TextField textField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void logAccount(ActionEvent event){
        if(validateFields(textField.getText())) {
            myController.setScreen(factory.getManage());
        }
    }

    private final String pattern = "^[a-zA-Z]";

    public boolean validateFields(String input){
        if(Pattern.compile(pattern).matcher(input).matches()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Per favore inserire tutti i campi prima di procedere");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }
}