package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import model.flight.route.Search;
import util.ControllerMethods;
import view.Factory;
import view.ScreensController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable, IControlledScreen {

    @FXML
    private ComboBox<String> cb1;

    @FXML
    private ComboBox<String> cb2;

    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date2;

    @FXML
    private RadioButton oneway;

    @FXML
    private Button cerca;

    ControllerMethods methods = new ControllerMethods();
    Search search = new Search();
    List<String> strings1 = new ArrayList<>();
    List<String> strings2 = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        strings1 = search.getServedDepartures();
        cb1.setItems(FXCollections.observableArrayList(strings1));
        methods.selectOptionOnKey(cb1, strings1);

        date1.setDayCellFactory(methods.dateRange());
        date2.setDayCellFactory(methods.dateRange());
        //gestire date2 < date1!!!!


        // Se seleziono Solo Andata non posso inserire Data Ritorno
        date2.disableProperty().bind(oneway.selectedProperty());

//      Gestire errori CERCA!!
    }

    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void findArrivals (ActionEvent event){
        strings2 = search.getServedArrivals(cb1.getSelectionModel().getSelectedItem());
        cb2.setItems(FXCollections.observableArrayList(strings2));
        methods.selectOptionOnKey(cb2, strings2);
    }


    @FXML
    private void goToHome(ActionEvent event){
        myController.setScreen(Factory.home);
    }

    @FXML
    private void goToLogin(ActionEvent event){
        myController.setScreen(Factory.login);
    }

    @FXML
    private void logout(ActionEvent event){
        //cambia stato come non loggato
        myController.setScreen(Factory.home);
    }
}
