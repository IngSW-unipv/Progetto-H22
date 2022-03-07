package controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.Factory;
import view.ScreensController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ResultController implements Initializable, IControlledScreen {

    ScreensController myController;
    private Factory factory = Factory.getInstance();

    @FXML
    private JFXListView list;

    @FXML
    private JFXRadioButton econ;
    private JFXRadioButton business;
    private JFXRadioButton first;

    @FXML
    private Label cost;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private DatePicker date1;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField cardName;

    @FXML
    private TextField date2;

    @FXML
    private TextField cvv;

    private List<String> items = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items.add("bravo");
        items.add("alpha");
        items.add("bravo");
        items.add("bravo");
        items.add("sierra");
        items.add("hotel");
        items.add("paul");
        list.setItems(FXCollections.observableArrayList(items));

   //     cost.setText(factory.getSession().getInfo(0)).bind();


    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

}
