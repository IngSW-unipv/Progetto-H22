package controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
    private JFXListView listA;

    @FXML
    private JFXListView listB;

    @FXML
    private JFXRadioButton econ;

    @FXML
    private JFXRadioButton business;

    @FXML
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
        listA.setItems(FXCollections.observableArrayList(items));

//        listB.visibleProperty().bind(
//                Bindings.when(new SimpleBooleanProperty(factory.getSession().isOneway()))
//                        .then(xx);
//                        .otherwise(
//                                Bindings.selectInteger(model.databaseProperty(), "size").asString(
//                                        bundle.getString("status.ready")))
//        );
        listB.visibleProperty().bind(new SimpleBooleanProperty(factory.getSession().isOneway()));


   //     cost.setText(factory.getSession().getInfo(0)).bind();


    }

    @FXML
    private void setProva (ActionEvent event){
        System.out.println(factory.getSession().isOneway());
//        listB.resize(0,0);
//        listB.setVisible(false);
//        listA.resize(280,480);
    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

}
