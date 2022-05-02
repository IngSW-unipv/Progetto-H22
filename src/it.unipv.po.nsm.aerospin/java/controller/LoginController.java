package controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.persistence.CachedFlights;
import model.persistence.entity.User;
import model.persistence.service.UserService;
import util.ControllerMethods;
import util.Session;
import view.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LoginController implements Initializable, IControlledScreen {

    Factory factory = Factory.getInstance();
    ScreensController myController;
    Session session = factory.getSession();
    CachedFlights searchResult = CachedFlights.getInstance();
    ControllerMethods methods = new ControllerMethods();
    UserService userService = new UserService();

    @FXML private TextField email;
    @FXML private TextField pwd;

    @FXML private Label errLabel;

    private List<User> users;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //CONTROLLARE CHE SIA CACHED!!
        //POCO SICURO, CONTROLLO INPUT EMAIL
        Thread t1 = new Thread(()->{
            users = userService.findAll();
        });
        t1.start();

    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }


    //formato email non corretto
    //email o pwd errati

    @FXML
    private void login(ActionEvent event) throws IOException {
//        while(cond){
//            ...
//            ...
//            ...
//        }
//
//
//
//
//        //CONTROLLO FORMATO EMAIL
//        if(checkMail()) {
//            //CONTROLLO SE UTENTE REGISTRATO
//            if(isRegistered()) {
//
//
//
//
//            }
//
//            if(isRegistered(email.getText())){
//                myController.setScreen(Factory.getManage());
//            }
//
//
//
//        } else {
//            email.clear();
//            pwd.clear();
//        }
    }

    @FXML
    private void register(ActionEvent event) throws IOException {

    }

    public boolean checkMail(){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText());
        if(matcher.find()){
            return true;
        } else {
            errLabel.setText("Formato email non valido!");
//            errLabel.visibleProperty().bind((ObservableValue<? extends Boolean>) email.getOnMouseClicked());
            return false;
        }
    }

//    public boolean isAdmin(String input){
//
//    }

    public boolean isRegistered(String input){
        UserService userService = new UserService();

        //è cached??
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



}
