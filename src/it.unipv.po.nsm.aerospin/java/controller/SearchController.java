package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.management.Search;
import util.ControllerMethods;
import view.Factory;
import view.ScreensController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable, IControlledScreen {

    ScreensController myController;
    private Factory factory = Factory.getInstance();
    ControllerMethods methods = new ControllerMethods();
    Search search = new Search();
    List<String> strings1 = new ArrayList<>();
    List<String> strings2 = new ArrayList<>();

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

    @FXML
    private Label errLabel;

    @FXML
    private JFXButton jBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jBtn.visibleProperty().bind(new SimpleBooleanProperty(factory.getSession().isLogged()));
        // Se seleziono Solo Andata non posso inserire Data Ritorno
        date2.disableProperty().bind(oneway.selectedProperty().or(date1.valueProperty().isNull()));
        date1.setDayCellFactory(methods.dateRange(LocalDate.now()));



        Thread t1 = new Thread(()->{
            strings1 = search.getServedDepartures();
            cb1.setItems(FXCollections.observableArrayList(strings1));
            methods.selectOptionOnKey(cb1, strings1);
        });
        t1.start();
    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void findArrivals (ActionEvent event){
        Thread t2 = new Thread(()->{
            strings2 = search.getServedArrivals(cb1.getSelectionModel().getSelectedItem());
            Platform.runLater(()->{
                cb2.setItems(FXCollections.observableArrayList(strings2));
            });
            methods.selectOptionOnKey(cb2, strings2);
        });
        t2.start();
    }

    //Gestisco date2>>date1
    @FXML
    private void returnDate (ActionEvent event){
        date2.setDayCellFactory(methods.dateRange(date1.getValue()));
    }

    @FXML
    private void goToHome(ActionEvent event){
        myController.setScreen(factory.getHome());
    }

    @FXML
    private void goToLogin(ActionEvent event){
        myController.setScreen(factory.getLogin());
    }

    @FXML
    private void goToResult(ActionEvent event){
        if(cb2.getSelectionModel().)
        myController.setScreen(factory.getResult());




    }

    @FXML
    private void logout(ActionEvent event){
        //cambia stato come non loggato
        factory.getSession().setLogged(false);
        myController.setScreen(factory.getHome());
        jBtn.visibleProperty().bind(new SimpleBooleanProperty(factory.getSession().isLogged()));
    }
}


