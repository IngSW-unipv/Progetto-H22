package controller;

import com.google.zxing.WriterException;
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
import model.booking.Ticket;
import model.booking.TicketMail;
import model.booking.passenger.ClassType;
import model.exception.NoMatchException;
import model.persistence.entity.Flight;
import model.persistence.entity.Orders;
import model.persistence.entity.Passenger;
import model.persistence.service.OrdersService;
import model.persistence.service.PassengerService;
import view.ScreenContainer;
import java.text.DecimalFormat;

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

    private Orders orders = new Orders();
    private OrdersService ordersService = new OrdersService();
    private Passenger passenger = new Passenger();
    private PassengerService passengerService = new PassengerService();
    private final String dep = session.getInfo().getDep();
    private final String ret = session.getInfo().getRet();
    private final Date dateDep = session.getInfo().getDateDep();
    private final Date dateRet = session.getInfo().getDateRet();
    private Double price = 0.0;
    private double multiplier = ClassType.ECONOMY.getPriceM();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private Ticket ticket_andata;
    private Ticket ticket_ritorno;
    TicketMail emailService = new TicketMail();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        initTable();
        birthDate.setDayCellFactory(methods.ageRange());

        group.selectedToggleProperty().addListener((ov, oldT, newT) -> {
            if(newT == null) {
                group.selectToggle(group.getToggles().get(2));
            }
//            if(table1.getSelectionModel().getSelectedItem().
            multiplier = ((ClassType) group.selectedToggleProperty().get().getUserData()).getPriceM();
            costLabel.setText(df.format(price()));


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
                (ListChangeListener<Integer>) change -> costLabel.setText(price().toString()));
        depLabel.setText(String.format(format, dep, ret, dateDep));
        if(session.getInfo().isOneway()) {
            retLabel.setVisible(false);
            table2.setPlaceholder(new Label("Ritorno non selezionato!"));
        } else {
            table2.setPlaceholder(new Label("Volo non disponibile in questa data!"));
            try {
                table2.setItems(methods.getFlights(ret, dep, dateRet));
            }catch (NoMatchException ignored){}
            table2.getSelectionModel().getSelectedIndices().addListener(
                    (ListChangeListener<Integer>) change -> costLabel.setText(price().toString()));
            retLabel.setText(String.format(format, ret, dep,dateRet));
        }
    }

    private Double price() {
        Double tot = 0.0;
        if(!table1.getSelectionModel().isEmpty()){
            tot += table1.getSelectionModel().getSelectedItem().getPrice();
        }
        if(!table2.getSelectionModel().isEmpty()){
            tot += table2.getSelectionModel().getSelectedItem().getPrice();
        }

        price = tot * multiplier;

        return price;
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
    private void checkout() throws IOException, WriterException {
        errLabel.setVisible(false);
        if (session.isLogged()) {
            if( price > 0 &&
                methods.dataCheck(name.getText(),surname.getText()) &&
                birthDate.getValue() != null){

                Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("util/subscreen/Payment.fxml")));
                Stage childStage = new Stage();
                childStage.initModality(Modality.APPLICATION_MODAL);
                childStage.initStyle(StageStyle.TRANSPARENT);
                childStage.setScene(new Scene(root1));
                childStage.showAndWait();


                if(session.getInfo().isPaid()) {
                    //devo controllare se passenger già esistente e NO CONSTRUCTOR
//                    order.setPassengerByPassengerId(new Passenger(session.getUser(), name.getText(), surname.getText()));
//                    if(!table1.getSelectionModel().isEmpty()){
//                        order.setFlightIdA(table1.getSelectionModel().getSelectedItem());
//                    }
//                    if(!table2.getSelectionModel().isEmpty()){
//                        order.setFlightIdR(table2.getSelectionModel().getSelectedItem());
//                    }
                    //SERVE LA CARD number?? NO
                    passenger.setUserId(session.getUser().getId());
                    passenger.setName(name.getText());
                    passenger.setSurname(surname.getText());
                    passengerService.persist(passenger);

                    orders.setPassengerId(passenger.getId());
                    orders.setFlightId(table1.getSelectionModel().getSelectedItem().getId());
                    orders.setFlightClass(group.getSelectedToggle().getUserData().toString());
                    orders.setCardDetails(Integer.parseInt(session.getInfo().getCardNumber().substring(12,15)));
                    orders.setOrderDate(new Date(System.currentTimeMillis()));
                    orders.setPrice(price);
                    ordersService.persist(orders);

                    if (!table2.getSelectionModel().isEmpty()) {
                        orders.setPassengerId(passenger.getId());
                        orders.setFlightId(table2.getSelectionModel().getSelectedItem().getId());
                        orders.setFlightClass(group.getSelectedToggle().getUserData().toString());
                        orders.setCardDetails(Integer.parseInt(session.getInfo().getCardNumber().substring(12,15)));
                        orders.setOrderDate(new Date(System.currentTimeMillis()));
                        orders.setPrice(price);
                        ordersService.persist(orders);
                    }



                    //salva order in db, o anche passenger?
                    //gestire posto in meno, necessario??

                    ticket_andata = new Ticket(name.getText(), surname.getText(),table1.getSelectionModel().getSelectedItem().getRouteByFlightRouteId().getAirportByDeparture().getIata(),
                            table1.getSelectionModel().getSelectedItem().getRouteByFlightRouteId().getAirportByArrival().getIata(),
                            table1.getSelectionModel().getSelectedItem().getFlightNumber(),table1.getSelectionModel().getSelectedItem().getScheduledDate().toString(),
                            table1.getSelectionModel().getSelectedItem().getScheduledTime().toString());

                    //ticket_andata = new Ticket("Pablo","Escobar","SUF","MXP","AES736","28/02/2022","8:00");
                    ticket_andata.generateTicket();





                    emailService.send("hamza17abbad@gmail.com", ticket_andata.getPath());








                    session.clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ordine Completato!");
                    alert.setContentText("Il suo acquisto è confermato, riceverà una mail con le info\nA presto!");
                    alert.showAndWait();
                    myContainer.setScreen(Factory.getHome());
                }
            } else {
                errLabel.setVisible(true);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login Error");
            alert.setContentText("Utente non loggato!\nPrima di poter procedere effettuare il Login\nSe non si è registrato, procedere alla Registrazione");
            alert.showAndWait();
        }

    }


}
