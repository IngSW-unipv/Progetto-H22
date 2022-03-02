package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import util.Session;
import view.Factory;
import view.ScreensController;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable, IControlledScreen {

    ScreensController myController;
    private Factory factory = Factory.getInstance();


    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private DatePicker date1;
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField carName;
    @FXML
    private DatePicker date2;
    @FXML
    private TextField cvv;
    @FXML
    private JFXRadioButton classe;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

}
