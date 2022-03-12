package controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.booking.payment.AeroPay;
import model.booking.payment.PaymentStrategy;
import model.management.ResultManager;
import model.persistence.entity.Flight;
import util.ControllerMethods;
import util.Session;
import view.Factory;
import view.ScreensController;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ResultController implements Initializable, IControlledScreen {

    ScreensController myController;
    private final Factory factory = Factory.getInstance();
    private final Session session = factory.getSession();
    private final ControllerMethods methods = new ControllerMethods();
    private final ResultManager resultManager = new ResultManager();
    private final PaymentStrategy paymentStrategy = new AeroPay();

    @FXML private Label depLabel;
    @FXML private Label retLabel;

    @FXML private TableView<Flight> table1;
    @FXML private TableView<Flight> table2;

    @FXML private Label costLabel;

    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private DatePicker date;

    @FXML private SubScene cover;

    @FXML private TextField cardNumber;
    @FXML private TextField cardName;
    @FXML private TextField expDate;
    @FXML private TextField cvv;

    @FXML private TableColumn<Flight,String> flightNumber1;
    @FXML private TableColumn<Flight, Time> scheduledTime1;
    @FXML private TableColumn<Flight, Time> arrivalTime1;
    @FXML private TableColumn<Flight, Double> price1;

    @FXML private TableColumn<Flight,String> flightNumber2;
    @FXML private TableColumn<Flight, Time> scheduledTime2;
    @FXML private TableColumn<Flight, Time> arrivalTime2;
    @FXML private TableColumn<Flight, Double> price2;

    private ObservableList<Flight> list1;
    private ObservableList<Flight> list2;

    private String dateRet;
    private String format = "Da %s\nA %s il %s";
    private final DoubleProperty cost1 = new SimpleDoubleProperty(0);
    private final DoubleProperty cost2 = new SimpleDoubleProperty(0);
    private final DoubleProperty priceClass = new SimpleDoubleProperty(1);
    private double price;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        flightNumber1.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightNumber"));
        scheduledTime1.setCellValueFactory(new PropertyValueFactory<Flight,Time>("scheduledTime"));
        arrivalTime1.setCellValueFactory(new PropertyValueFactory<Flight,Time>("arrivalTime"));
        price1.setCellValueFactory(new PropertyValueFactory<Flight,Double>("price"));
        flightNumber2 = flightNumber1;
        scheduledTime2 = scheduledTime1;
        arrivalTime2 = arrivalTime1;
        price2 = price1;

//      Listener per il CostLabel
        table1.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener<Integer>) change -> cost1.setValue(
                        table1.getSelectionModel().getSelectedItem().getPrice()));
        cost1.addListener((observableValue, number, t1) -> {
            price = (cost1.get() + cost2.get()) * priceClass.get();
            costLabel.setText(String.valueOf(price));
        });
        priceClass.addListener((observableValue, number, t1) -> {
            price = (cost1.get() + cost2.get()) * priceClass.get();
            costLabel.setText(String.valueOf(price));
        });

        String dep = session.getInfo().get(0);
        String ret = session.getInfo().get(1);
        String dateDep = session.getInfo().get(2);
        depLabel.setText(String.format(format, dep, ret, dateDep));
        if(session.isOneway()){
            retLabel.setVisible(false);
            table2.setVisible(false);
        } else {
            table2.getSelectionModel().getSelectedIndices().addListener(
                    (ListChangeListener<Integer>) change -> cost2.setValue(
                            table2.getSelectionModel().getSelectedItem().getPrice()));
            cost2.addListener((observableValue, number, t1) -> {
                price = (cost1.get() + cost2.get()) * priceClass.get();
                costLabel.setText(String.valueOf(price));
            });
            dateRet = session.getInfo().get(3);
            retLabel.setText(String.format(format, ret, dep,dateRet));
        }

        date.setDayCellFactory(methods.ageRange());

        cover.visibleProperty().bind(name.textProperty().isEmpty().or(
                surname.textProperty().isEmpty()).or(
                date.valueProperty().isNull()));

        //THREADDD
        list1 = FXCollections.observableArrayList(resultManager.getFlightsByDepArr(dep, ret, dateDep));
        table1.setItems(list1);
        if(!(session.isOneway())) {
            list2 = FXCollections.observableArrayList(resultManager.getFlightsByDepArr(ret, dep,dateRet));
            table2.setItems(list2);
        }
        //THREADDD


    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void economyClass(ActionEvent event){
        priceClass.set(1);
    }

    @FXML
    private void businessClass(ActionEvent event){
        priceClass.set(2);
    }

    @FXML
    private void firstClass(ActionEvent event){
        priceClass.set(5);
    }

    //SISTEMARE
    @FXML
    private void logged(ActionEvent event) throws IOException {
        System.out.println(session.isLogged());
        if (!session.isLogged()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Non ha effettuato il Login!\nPrima di poter procedere effettuare il Login\nSe non si è registrato, procedere alla Registrazione");
            alert.showAndWait();
            myController.setScreen(Factory.getLogin());
        }
    }

    @FXML
    private void minors(ActionEvent event) {
        if (methods.isMinor(date.getValue())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reminder");
            alert.setHeaderText(null);
            alert.setContentText("Le ricordiamo che i passeggeri con età inferiore ai 16 anni devono viaggiare accompagnati");
            alert.showAndWait();
        }
    }

    @FXML
    private void checkout(ActionEvent event) throws IOException {

        myController.setScreen(Factory.getHome());
        //paymentStrategy.pay();

    }


}
