package controller;

import javafx.beans.property.*;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Factory;
import model.booking.passenger.ClassType;
import model.booking.payment.AeroPay;
import model.booking.payment.PaymentStrategy;
import model.persistence.entity.Flight;
import model.util.Session;
import model.util.manager.ResultManager;
import view.ScreenContainer;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.sql.Date;
import java.util.ResourceBundle;

public class ResultController implements Initializable, IControlledScreen {

    ScreenContainer myContainer;
    Session session = Factory.getInstance().getSession();
    ResultManager methods = new ResultManager();

    //controllare
    private final PaymentStrategy paymentStrategy = new AeroPay();

    @FXML private Label depLabel;
    @FXML private Label retLabel;

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
    public void initialize(URL url, ResourceBundle rb) {
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

        flightNumber1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFlightNumber()));
        flightNumber2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFlightNumber()));
        scheduledTime1.setCellValueFactory(new PropertyValueFactory<>("scheduledTime"));
        scheduledTime2.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        arrivalTime1.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        arrivalTime2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getArrivalTime().getHours() + ":" + c.getValue().getArrivalTime().getMinutes()));
        price1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPrice() + " €"));
        price2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPrice() + " €"));

        table1.setPlaceholder(new Label("Volo non disponibile in questa data!"));
        table1.setItems(methods.getFlights(dep,ret,dateDep));
        table1.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener<Integer>) change -> costLabel.setText(price()));
        depLabel.setText(String.format(format, dep, ret, dateDep));
        if(session.isOneway()) {
            retLabel.setVisible(false);
            table2.setPlaceholder(new Label("Ritorno non selezionato!"));
        } else {
            table2.setPlaceholder(new Label("Volo non disponibile in questa data!"));
            table2.setItems(methods.getFlights(ret,dep,dateRet));
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
    private void ageCheck(ActionEvent event) {
        if (methods.isMinor(birthDate.getValue())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reminder");
            alert.setHeaderText(null);
            alert.setContentText("Le ricordiamo che i passeggeri di età inferiore ai 16 anni devono viaggiare accompagnati");
            alert.showAndWait();
        }
    }

    @FXML
    private void checkout(ActionEvent event) throws IOException {
        if (session.isLogged()) {
            if(Integer.parseInt(price()) > 0 &
                name.getText().isEmpty() & surname.getText().isEmpty() & birthDate.getProperties().isEmpty()){
                System.out.println("ok");
            }
            System.out.println("non ok");





        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Utente non loggato!\nPrima di poter procedere effettuare il Login\nSe non si è registrato, procedere alla Registrazione");
            alert.showAndWait();
        }
//
//
//        Service<Void> service = new Service<Void>() {
//            @Override
//            protected Task<Void> createTask() {
//                return new Task<Void>() {
//                    @Override
//                    protected Void call() throws Exception {
//                        //Do Long running work here<<<
//                        System.out.println("In task 1");
//                        Parent root1 = null;
//                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Customer.fxml"));
//                        try {
//                            root1 = (Parent) fxmlLoader.load();
//                        } catch (IOException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        Stage stage = new Stage();
//                        stage.initModality(Modality.APPLICATION_MODAL);
//                        stage.initStyle(StageStyle.UNDECORATED);
//                        stage.setTitle("ABC");
//                        System.out.println("In task 2");
//                        stage.setScene(new Scene(root1));
//                        stage.show();
//                        System.out.println("In task 3");
//
//                        return null;
//                    }
//                };
//            }
//            @Override
//            protected void succeeded() {
//                //Called when finished without exception
//                System.out.println("OnSucceeded");
//                TextFileReadWrite getCustomer = new TextFileReadWrite();
//                String cusName = "";
//                try {
//                    cusName = getCustomer.readFromFile("C:\\gasoum\\YannaKSIA\\ForitiTimologisi\\tempFiles\\tempCustomer.txt");
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                System.out.println("Col:" + col + " Row: "+ row);
//                checkCusFile = true;
//                oList.get(row).cusEponymia = cusName;
//                table.refresh();
//
//
//            }
//        };
//        service.start(); // starts Thread
//
//
//
//        myContainer.setScreen(Factory.getHome());
//
//
    }


}
