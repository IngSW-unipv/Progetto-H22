package controller;

import controller.util.IControlledScreen;
import controller.util.manager.ResultManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Factory;
import model.Session;
import model.booking.passenger.ClassType;
import model.exception.NoMatchException;
import model.persistence.entity.Flight;
import view.ScreenContainer;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultController implements Initializable, IControlledScreen {

    ScreenContainer myContainer;
    Session session = Factory.getInstance().getSession();
    ResultManager methods = new ResultManager();

    //controllare
   // private final PaymentStrategy paymentStrategy = new AeroPay();

    @FXML private Label depLabel;
    @FXML private Label retLabel;
    @FXML private Label errLabel;

    @FXML private TableView<Flight> table1;
    @FXML private TableColumn<Flight,String> flightNumber1;
    @FXML private TableColumn<Flight, Time> scheduledTime1;
    @FXML private TableColumn<Flight, Time> arrivalTime1;
    @FXML private TableColumn<Flight, String> price1;

    @FXML private TableView<Flight> table2;
    @FXML private TableColumn<Flight,String> flightNumber2;
    @FXML private TableColumn<Flight, Time> scheduledTime2;
    @FXML private TableColumn<Flight, String> arrivalTime2;
    @FXML private TableColumn<Flight, String> price2;

    @FXML private ToggleGroup group;

    @FXML private Label costLabel;

    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private DatePicker birthDate;

    private final String dep = session.getDep();
    private final String ret = session.getRet();
    private final Date dateDep = session.getDateDep();
    private final Date dateRet = session.getDateRet();
    private double multiplier = ClassType.ECONOMY.getPriceM();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        initTable();
        birthDate.setDayCellFactory(methods.ageRange());

        group.selectedToggleProperty().addListener((ov, oldT, newT) -> {
            if(newT == null) {
                group.selectToggle(group.getToggles().get(2));
            }
            multiplier = ((ClassType) group.selectedToggleProperty().get().getUserData()).getPriceM();
            costLabel.setText(price());
        });
    }

    public void setScreenParent(ScreenContainer screenParent){
        myContainer = screenParent;
    }

    private void initTable() {
        String format = "Da %s\nA %s il %s";

        table1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //DECIDERE
        flightNumber1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFlightNumber()));
        flightNumber2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFlightNumber()));
        scheduledTime1.setCellValueFactory(new PropertyValueFactory<>("scheduledTime"));
        scheduledTime2.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        arrivalTime1.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        arrivalTime2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getArrivalTime().getHours() + ":" + c.getValue().getArrivalTime().getMinutes()));
        price1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPrice() + " €"));
        price2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPrice() + " €"));

        table1.setPlaceholder(new Label("Volo non disponibile in questa data!"));
        try {
            table1.setItems(methods.getFlights(dep, ret, dateDep));
        } catch (NoMatchException ignored) {}
        table1.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener<Integer>) change -> costLabel.setText(price()));
        depLabel.setText(String.format(format, dep, ret, dateDep));
        if(session.isOneway()) {
            retLabel.setVisible(false);
            table2.setPlaceholder(new Label("Ritorno non selezionato!"));
        } else {
            table2.setPlaceholder(new Label("Volo non disponibile in questa data!"));
            try {
                table2.setItems(methods.getFlights(ret, dep, dateRet));
            }catch (NoMatchException ignored){}
            table2.getSelectionModel().getSelectedIndices().addListener(
                    (ListChangeListener<Integer>) change -> costLabel.setText(price()));
            retLabel.setText(String.format(format, ret, dep,dateRet));
        }
    }

    private String price() {
        double tot = 0;
        if(!table1.getSelectionModel().isEmpty()){
            tot += table1.getSelectionModel().getSelectedItem().getPrice();
        }
        if(!table2.getSelectionModel().isEmpty()){
            tot += table2.getSelectionModel().getSelectedItem().getPrice();
        }

        double price = tot * multiplier;
        return String.valueOf(price);
    }

    @FXML
    private void ageCheck() {
        if (methods.isMinor(birthDate.getValue())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reminder");
            alert.setHeaderText(null);
            alert.setContentText("Le ricordiamo che i passeggeri di età inferiore ai 16 anni devono viaggiare accompagnati");
            alert.showAndWait();
        }
    }

    @FXML
    private void checkout() throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("util/Payment.fxml")));
        Stage childStage = new Stage();
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.initStyle(StageStyle.TRANSPARENT);        //oppure UTILITY
        childStage.setScene(new Scene(root1));
        childStage.showAndWait();

        errLabel.setVisible(false);
        if (session.isLogged()) {
            if( Double.parseDouble(price()) > 0 &&
                methods.dataCheck(name.getText(),surname.getText()) &&
                birthDate.getValue() != null){


                System.out.println("ok");
//        myContainer.setScreen(Factory.getHome());
            } else {
                errLabel.setVisible(true);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Utente non loggato!\nPrima di poter procedere effettuare il Login\nSe non si è registrato, procedere alla Registrazione");
            alert.showAndWait();
        }

    }


}
