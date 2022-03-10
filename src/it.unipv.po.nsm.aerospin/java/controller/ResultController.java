package controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.booking.payment.AeroPay;
import model.booking.payment.PaymentStrategy;
import model.management.ResultManager;
import model.persistence.entity.Flight;
import util.Session;
import view.Factory;
import view.ScreensController;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

public class ResultController implements Initializable, IControlledScreen {

    ScreensController myController;
    private Factory factory = Factory.getInstance();
    private Session session = factory.getSession();
    private ResultManager resultManager = new ResultManager();

    @FXML
    private Label depLabel;

    @FXML
    private Label retLabel;

    @FXML
    private TableView<Flight> table1;

    @FXML
    private TableView<Flight> table2;

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

    @FXML
    private TableColumn<Flight,String> flightNumber1;

    @FXML
    private TableColumn<Flight, Time> scheduledTime1;

    @FXML
    private TableColumn<Flight, Time> arrivalTime1;

    @FXML
    private TableColumn<Flight, Double> price1;

    @FXML
    private TableColumn<Flight,String> flightNumber2;

    @FXML
    private TableColumn<Flight, Time> scheduledTime2;

    @FXML
    private TableColumn<Flight, Time> arrivalTime2;

    @FXML
    private TableColumn<Flight, Double> price2;


    private ObservableList<Flight> list1;
    private ObservableList<Flight> list2;
    PaymentStrategy paymentStrategy = new AeroPay();

    private String dep;
    private String ret;
    private String dateDep;
    private String dateRet;
    private String format = "Da %s\nA %s il %s";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dep = session.getInfo().get(0);
        ret = session.getInfo().get(1);
        dateDep = session.getInfo().get(2);

        depLabel.setText(String.format(format,dep,ret,dateDep));
        if(session.isOneway()){
            retLabel.setVisible(false);
            table2.setVisible(false);
        } else {
            retLabel.setText(String.format(format,ret,dep,dateRet));
        }

        list1 = FXCollections.observableArrayList(resultManager.getFlightsByDepArr(dep,ret,dateDep));
        flightNumber1.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightNumber"));
        scheduledTime1.setCellValueFactory(new PropertyValueFactory<Flight,Time>("scheduledTime"));
        arrivalTime1.setCellValueFactory(new PropertyValueFactory<Flight,Time>("arrivalTime"));
        price1.setCellValueFactory(new PropertyValueFactory<Flight,Double>("price"));

//        list2 = FXCollections.observableArrayList(resultManager.getFlightsByDepArr(ret,dep,dateDep));
//        flightNumber2.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightNumber"));
//        scheduledTime2.setCellValueFactory(new PropertyValueFactory<Flight,Time>("scheduledTime"));
//        arrivalTime2.setCellValueFactory(new PropertyValueFactory<Flight,Time>("arrivalTime"));
//        price2.setCellValueFactory(new PropertyValueFactory<Flight,Double>("price"));

        table1.setItems(list1);
//        table2.setItems(list2);

        table1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // just in case you didnt already set the selection model to multiple selection.
        table1.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>()
        {
            @Override
            public void onChanged(Change<? extends Integer> change){
            double i = table1.getSelectionModel().getSelectedItem().getPrice();
                cost.setText(String.valueOf(i));
            }

        });
   //     cost.setText(factory.getSession().getInfo(0)).bind();


    }

    @FXML
    private void checkout(ActionEvent event){
        paymentStrategy.pay();

    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }


    @FXML
    private void setProva (ActionEvent event){

    }
}
