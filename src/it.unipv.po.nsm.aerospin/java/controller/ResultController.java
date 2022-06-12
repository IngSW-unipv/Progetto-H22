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
import model.booking.*;
import model.exception.NoMatchException;
import model.persistence.entity.Flight;
import model.persistence.entity.Orders;
import model.persistence.entity.Passenger;
import model.persistence.service.OrdersService;
import model.persistence.service.PassengerService;
import net.sf.ehcache.search.expression.Or;
import view.ScreenContainer;
import java.text.DecimalFormat;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultController implements Initializable, IControlledScreen {
    private ScreenContainer myContainer;
    private final Session session = Factory.getInstance().getSession();
    private final ResultManager methods = new ResultManager();
    private final Info info = Factory.getInstance().getSession().getInfo();

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
    @FXML private TableColumn<Flight, Time> arrivalTime2;
    @FXML private TableColumn<Flight, String> price2;
    @FXML private ToggleGroup group;
    @FXML private Label costLabel;
    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private DatePicker birthDate;

    private Double price = 0.0;
    private double multiplier = Fares.STANDARD.getPriceM();

    private final OrdersService ordersService = new OrdersService();
    private final Passenger passenger = new Passenger();
    private final PassengerService passengerService = new PassengerService();
    private final String dep = session.getInfo().getDep();
    private final String ret = session.getInfo().getRet();
    private final Date dateDep = session.getInfo().getDateDep();
    private final Date dateRet = session.getInfo().getDateRet();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        birthDate.setDayCellFactory(methods.ageRange());

        group.selectedToggleProperty().addListener((ov, oldT, newT) -> {
            if(newT == null) {
                group.selectToggle(group.getToggles().get(2));
            }
            multiplier = ((Fares) group.selectedToggleProperty().get().getUserData()).getPriceM();
            price();
        });
    }

    public void setScreenParent(ScreenContainer screenParent) {
        myContainer = screenParent;
    }

    private void initTable() {
        table1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        flightNumber1.setCellValueFactory(
                c -> new SimpleStringProperty(c.getValue().getFlightNumber()));
        flightNumber2.setCellValueFactory(
                c -> new SimpleStringProperty(c.getValue().getFlightNumber()));
        scheduledTime1.setCellValueFactory(new PropertyValueFactory<>("scheduledTime"));
        scheduledTime2.setCellValueFactory(new PropertyValueFactory<>("scheduledTime"));
        arrivalTime1.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        arrivalTime2.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        price1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPrice() + " €"));
        price2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPrice() + " €"));
        table1.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener<Integer>) change -> price());
        table2.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener<Integer>) change -> price());

        String format = "Da %s\nA %s il %s";
        depLabel.setText(String.format(format, dep, ret, dateDep));
        table2.setPlaceholder(new Label("Volo non disponibile in questa data!"));
        try {
                table1.setItems(methods.getList(dep, ret, dateDep));
        } catch (NoMatchException ignored) {
        }
        if(info.isOneway()) {
                retLabel.setVisible(false);
                table2.setPlaceholder(new Label("Ritorno non selezionato!"));
        } else {
                try {
                        table2.setItems(methods.getList(ret, dep, dateRet));
                } catch (NoMatchException ignored){
                }
                retLabel.setText(String.format(format, ret, dep,dateRet));
        }
    }

    private void price() {
        double tot = 0;
        if(!table1.getSelectionModel().isEmpty()){
            tot += table1.getSelectionModel().getSelectedItem().getPrice();
        }
        if(!table2.getSelectionModel().isEmpty()){
            tot += table2.getSelectionModel().getSelectedItem().getPrice();
        }
        price = tot * multiplier;
        costLabel.setText(new DecimalFormat("0.00").format(price));
    }

    @FXML
    private void ageCheck() {
        if (methods.isMinor(birthDate.getValue())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reminder");
            alert.setHeaderText(null);
            alert.setContentText("Le ricordiamo che i passeggeri di età inferiore ai 16 anni " +
                    "devono viaggiare accompagnati");
            alert.showAndWait();
        }
    }

    @FXML
    private void checkout() throws IOException, WriterException {
        Orders order1 = new Orders();
        Orders order2 = new Orders();

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


                if(info.isPaid()) {
                    passenger.setUserId(session.getUser().getId());
                    passenger.setName(name.getText());
                    passenger.setSurname(surname.getText());
                    passengerService.persist(passenger);

                    order1.setPassengerId(passenger.getId());
                    order1.setFlightId(table1.getSelectionModel().getSelectedItem().getId());
                    order1.setFare((Fares) group.getSelectedToggle().getUserData());
                    order1.setCardDetails(Integer.parseInt(session.getInfo().getCardNumber().substring(12,15)));
                    order1.setOrderDate(new Date(System.currentTimeMillis()));
                    order1.setPrice(price);
                    //orders.setId("ASO" + );
                    ordersService.persist(order1);


                    if (!table2.getSelectionModel().isEmpty()) {
                        order2.setPassengerId(passenger.getId());
                        order2.setFlightId(table2.getSelectionModel().getSelectedItem().getId());
                        order2.setFare((Fares) group.getSelectedToggle().getUserData());
                        order2.setCardDetails(Integer.parseInt(session.getInfo().getCardNumber().substring(13,16)));
                        order2.setOrderDate(new Date(System.currentTimeMillis()));
                        order2.setPrice(price);
                        ordersService.persist(order2);
                    }

                    methods.sendTicket(order1);
                    methods.bookSeat(table1.getSelectionModel().getSelectedItem());
                    if(!table2.getSelectionModel().isEmpty()) {
                            methods.sendTicket(order1);
                        methods.bookSeat(table2.getSelectionModel().getSelectedItem());
                    }
//TODO REFRESHCACHE
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
