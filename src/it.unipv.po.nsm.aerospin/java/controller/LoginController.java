package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;
import model.persistence.entity.User;
import model.persistence.service.UserService;
import view.*;

import java.io.IOException;
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
    private void logAccount(ActionEvent event) throws IOException {
        if(checkExpression(textField.getText())) {
            if(isRegistered(textField.getText())){
                myController.setScreen(factory.getManage());
            }

        }
    }

    public boolean checkExpression(String input){
        if(!validate(input)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Email non valida");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

//    public boolean isAdmin(String input){
//
//    }

    public boolean isRegistered(String input){
        UserService userService = new UserService();

        try{
            String s = userService.findByEmail(input).get(0).getEmail();
            return true;
        }catch (IndexOutOfBoundsException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Utente non registrato");
            alert.showAndWait();
            return false;
        }

    }





    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }



}