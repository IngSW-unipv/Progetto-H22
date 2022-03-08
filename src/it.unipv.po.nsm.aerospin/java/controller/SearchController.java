package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    @FXML
    private RadioButton ar;

    @FXML
    private Label errLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Se seleziono Solo Andata non posso inserire Data Ritorno
        ar.setDisable(true);
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
        if (searchManager.checkRoute(cb2.getValue(),cb1.getValue())){
            ar.setDisable(false);
        } else {
            ar.setDisable(true);
            errLabel.setText("Andata e Ritorno NON DISPONIBILE");
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
            factory.getSession().setOneway(oneway.isSelected());
            factory.getSession().getInfo().clear();
            factory.getSession().addInfo(cb1.getSelectionModel().getSelectedItem());
            factory.getSession().addInfo(cb2.getSelectionModel().getSelectedItem());
            factory.getSession().addInfo(date1.getValue().toString());
            if (!(factory.getSession().isOneway())) {
                factory.getSession().addInfo(date2.getValue().toString());
            }
            myController.setScreen(factory.getResult());
        }
    }

    //System.out.println(date1.getValue() == null);   può dare bug perchè se cancello la data rimane not null
    public boolean validateFields(){
        if( cb2.getSelectionModel().isEmpty() | date1.getValue() == null | (date2.getValue() == null && ar.isSelected())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Inserire tutti i campi prima di procedere!");
            alert.showAndWait();
            return false;
        }
        return true;
    }

}
