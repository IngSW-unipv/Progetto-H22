package controller;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.persistence.entity.User;
import model.persistence.service.UserService;
import view.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements Initializable, IControlledScreen {

    ScreensController myController;
    private final Factory factory = Factory.getInstance();

    @FXML private TextField email;
    @FXML private TextField pwd;

    @FXML private Label errLabel;

    private List<User> users;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        errLabel.setText("Formato email non valido!");

        //poco sicuro?? efficienza?
        // metodo da Hamza per caricare tutta lista user
        // user = ..............;



    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    //formato email non corretto
    //email o pwd errati


    @FXML
    private void logAccount(ActionEvent event) throws IOException {
//        if(checkMail()) {
//            if(isRegistered()) {
//
//            }
//
//            if(isRegistered(email.getText())){
//                myController.setScreen(Factory.getManage());
//            }
//
//
//
//        }
    }

    public boolean checkMail(){
        if(validate(email.getText())){
            return true;
        } else {
            errLabel.setVisible(true);
            errLabel.visibleProperty().bind((ObservableValue<? extends Boolean>) email.getOnMouseClicked());
            return false;
        }
    }

//    public boolean isAdmin(String input){
//
//    }

    public boolean isRegistered(String input){
        UserService userService = new UserService();

        try{
            String s = userService.findByEmail(input).getEmail();
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

    public static boolean validate(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
