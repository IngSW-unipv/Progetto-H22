package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import model.persistence.service.AirportService;
import util.ControllerMethods;
import view.MainApplication;
import view.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class SSearchController implements Initializable, IControlledScreen {

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

    AirportService airportService = new AirportService();
    ControllerMethods methods = new ControllerMethods();
    private final ObservableList<String> options = FXCollections.observableArrayList(
            "Aab",
            "Aer",
            "Aeq",
            "Arx",
            "Byad",
            "Csca",
            "Csee",
            "Cfefe",
            "Cead",
            "Defea",
            "Dqeqe",
            "Fefaf",
            "Gert",
            "Wqad",
            "Xsad",
            "Zzz"
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        List<String> strings = airportService.findByParam("airportName");
//        cb1.setItems(FXCollections.observableArrayList(strings));
//        cb2.setItems(FXCollections.observableArrayList(strings));
        //gestire cb1 == cb2!!!!

        cb1.setItems(options);
        cb2.setItems(options);
        methods.selectOptionOnKey(cb1, options);
        methods.selectOptionOnKey(cb2, options);

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
    private void goToHome(ActionEvent event){
        myController.setScreen(MainApplication.home);
    }

    @FXML
    private void goToLogin(ActionEvent event){
        myController.setScreen(MainApplication.login);
    }
}
