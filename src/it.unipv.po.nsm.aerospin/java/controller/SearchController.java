package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.persistence.CachedFlights;
import model.persistence.entity.Flight;
import model.util.ControllerMethods;
import model.util.Session;
import model.Factory;
import view.ScreenContainer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SearchController implements Initializable, IControlledScreen {

    ScreenContainer myContainer;
    Session session = Factory.getInstance().getSession();
    CachedFlights searchResult = CachedFlights.getInstance();
    ControllerMethods methods = new ControllerMethods();

    @FXML private ComboBox<String> cbDep;
    @FXML private ComboBox<String> cbRet;

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
                                .map(o->o.getRouteByFlightRouteId().getAirportByDeparture().getAirportName()).sorted()
                                .distinct().collect(Collectors.toList());
            cbDep.setItems(FXCollections.observableArrayList(listDepart));
            methods.selectOptionOnKey(cbDep, listDepart);
        });
        t1.start();
    }

    public void setScreenParent(ScreenContainer screenParent) {
        myContainer = screenParent;
    }

    @FXML
    private void findArrivals (ActionEvent event){
        Thread t2 = new Thread(()->{
            //controlla che venga resettato la combobox
            cbRet.getSelectionModel().clearSelection();
            cbRet.autosize();

            //OVERRIDE EQUALS AIRPORT TO STRING !!FATTO!!
            listReturn = results.stream()
                        .filter(o -> o.getRouteByFlightRouteId().getAirportByDeparture().equalsString(cbDep.getValue()))
                        .map(o->o.getRouteByFlightRouteId().getAirportByArrival().getAirportName()).sorted()
                        .distinct().collect(Collectors.toList());
            cbRet.setItems(FXCollections.observableArrayList(listReturn));
            methods.selectOptionOnKey(cbRet, listReturn);

//            Platform.runLater(()->{
//                cbRet.setItems(FXCollections.observableArrayList(listReturn));
//                methods.selectOptionOnKey(cbRet, listReturn);
//            });
        });
        t2.start();
    }

    @FXML
    private void checkRoute(ActionEvent event){
        errLabel.setText("");
        if (methods.checkRoute(cbRet.getValue(), cbDep.getValue())){
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
            session.setOneway(oneway.isSelected());
            session.getInfo().clear();
            session.addInfo(cbDep.getSelectionModel().getSelectedItem());
            session.addInfo(cbRet.getSelectionModel().getSelectedItem());
            session.addInfo(date1.getValue().toString());
            if (!(session.isOneway())) {
                session.addInfo(date2.getValue().toString());
            }
            myContainer.setScreen(Factory.getResult());
        }
    }

    //System.out.println(date1.getValue() == null);   può dare bug perchè se cancello la data, rimane not null

    public boolean validateFields(){
        if( cbRet.getSelectionModel().isEmpty() | date1.getValue() == null | (date2.getValue() == null && ar.isSelected())){
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
