package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import model.management.SearchManager;
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
    private final Factory factory = Factory.getInstance();
    ControllerMethods methods = new ControllerMethods();
    SearchManager searchManager = new SearchManager();
    List<String> strings1 = new ArrayList<>();
    List<String> strings2 = new ArrayList<>();


    String dep, arr;

    @FXML
    private
    ComboBox<String> cb1;

    @FXML
    private ComboBox<String> cb2;

    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date2;

    @FXML
    private RadioButton oneway;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Se seleziono Solo Andata non posso inserire Data Ritorno
        date2.disableProperty().bind(oneway.selectedProperty().or(date1.valueProperty().isNull()));
        date1.setDayCellFactory(methods.dateRange(LocalDate.now()));

        Thread t1 = new Thread(()->{
            strings1 = searchManager.getServedDepartures();
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
            strings2 = searchManager.getServedArrivals(cb1.getSelectionModel().getSelectedItem());

            Platform.runLater(()->{
                cb2.setItems(FXCollections.observableArrayList(strings2));
            });
            methods.selectOptionOnKey(cb2, strings2);
        });
        t2.start();

    }


    @FXML
    private void checkRoute(ActionEvent event){
        if (checkReturning()){
            System.out.println("okokokokokokokok");
        }
    }




    //Gestisco date2>>date1
    @FXML
    private void returnDate (ActionEvent event){
        date2.setDayCellFactory(methods.dateRange(date1.getValue()));
    }

    @FXML
    private void goToResult(ActionEvent event){
        if (validateFields()) {
            factory.getSession().getInfo().clear();
            factory.getSession().addInfo(cb1.getSelectionModel().getSelectedItem());
            factory.getSession().addInfo(cb2.getSelectionModel().getSelectedItem());
            factory.getSession().addInfo(date1.getValue().toString());
            //factory.getSession().addInfo(date2.getValue().toString());

            myController.setScreen(factory.getResult());
        }
    }

    public boolean validateFields(){
        if( cb2.getSelectionModel().isEmpty() | date1.getValue() == null | (date2.getValue() == null  & !oneway.isSelected())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Inserire tutti i campi prima di procedere!");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public boolean checkReturning(){
        if(!(searchManager.checkRoute(cb2.getValue(),cb1.getValue()))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Ritorno non disponibileeeeeeeeeeeeeeeeeeeeeeeee you brat!");
            alert.showAndWait();
            return false;
        }
        return true;
    }



}
