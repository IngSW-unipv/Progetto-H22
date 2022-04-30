package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.persistence.CachedFlights;
import model.persistence.entity.Flight;
import util.ControllerMethods;
import view.Factory;
import view.ScreensController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SearchController implements Initializable, IControlledScreen {


    Factory factory = Factory.getInstance();
    ScreensController myController;
    CachedFlights searchResult = CachedFlights.getInstance();
    ControllerMethods methods = new ControllerMethods();
//    SearchManager searchManager = new SearchManager();

    @FXML private ComboBox<String> cb1;
    @FXML private ComboBox<String> cb2;

    @FXML private DatePicker date1;
    @FXML private DatePicker date2;

    @FXML private RadioButton oneway;
    @FXML private RadioButton ar;

    @FXML private Label errLabel;

    List<Flight> results = searchResult.findAll();
    List<String> listDepart = new ArrayList<>();
    List<String> listReturn = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Se seleziono Solo Andata non posso inserire Data Ritorno
        ar.setDisable(true);
        date2.disableProperty().bind(oneway.selectedProperty().or(date1.valueProperty().isNull()));
        date1.setDayCellFactory(methods.bookingRange(LocalDate.now()));

        Thread t1 = new Thread(()->{
            listDepart = results.stream()
                                .map(o->o.getRouteByFlightRouteId().getDeparture().get(0).getAirportName())
                                .collect(Collectors.toList());
            //poi rimuovere secondo get(0)

//            List<String> codes =
//                    orders.stream()
//                            .filter(o -> o.getStatus().equals("NEW"))
//                            .map(Order::getCode)
//                            .collect(Collectors.toList());
//                    results.get(0).getRouteByFlightRouteId().getDeparture().get(0).getAirportName();

            cb1.setItems(FXCollections.observableArrayList(listDepart));
            methods.selectOptionOnKey(cb1, listDepart);
        });
        t1.start();
    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void findArrivals (ActionEvent event){
        Thread t2 = new Thread(()->{
            //controlla che venga resettato la combobox
            cb2.getSelectionModel().clearSelection();
            cb2.autosize();

            //OVERRIDE EQUALS AIRPORT TO STRING
            listReturn = results.stream()
                        .filter(o -> o.getRouteByFlightRouteId().getDeparture().equals(cb1.getValue()))
                        .map(o->o.getRouteByFlightRouteId().getArrival().get(0).getAirportName())
                        .collect(Collectors.toList());;
            cb2.setItems(FXCollections.observableArrayList(listReturn));
            methods.selectOptionOnKey(cb2, listReturn);

//            Platform.runLater(()->{
//                cb2.setItems(FXCollections.observableArrayList(listReturn));
//                methods.selectOptionOnKey(cb2, listReturn);
//            });
        });
        t2.start();
    }

    @FXML
    private void checkRoute(ActionEvent event){
        errLabel.setText("");
        if (methods.checkRoute(cb2.getValue(),cb1.getValue())){
            ar.setDisable(false);
        } else {
            ar.setDisable(true);
            errLabel.setText("Andata e Ritorno NON DISPONIBILE");
        }
    }

    //Gestisco date2>>date1
    @FXML
    private void returnDate (ActionEvent event){
        date2.setDayCellFactory(methods.bookingRange(date1.getValue()));
    }

    @FXML
    private void goToResult(ActionEvent event) throws IOException {
        if (validateFields()) {
            factory.getSession().setOneway(oneway.isSelected());
            factory.getSession().getInfo().clear();
            factory.getSession().addInfo(cb1.getSelectionModel().getSelectedItem());
            factory.getSession().addInfo(cb2.getSelectionModel().getSelectedItem());
            factory.getSession().addInfo(date1.getValue().toString());
            if (!(factory.getSession().isOneway())) {
                factory.getSession().addInfo(date2.getValue().toString());
            }
            myController.setScreen(Factory.getResult());
        }
    }

    //System.out.println(date1.getValue() == null);   può dare bug perchè se cancello la data, rimane not null
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
