package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import controller.util.IControlledScreen;
import controller.util.manager.ResultManager;
import controller.util.manager.RouteManager;
import controller.util.manager.SearchManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.exception.NoMatchException;
import model.persistence.entity.Flight;
import javafx.scene.control.TableView;
import model.persistence.entity.Route;
import model.persistence.service.FlightService;
import model.persistence.service.RouteService;
import view.ScreenContainer;
import javafx.scene.control.TableColumn;
import javafx.scene.control.DatePicker;
import java.util.regex.Matcher;
import javafx.scene.control.Button;

public class FlightManController implements Initializable, IControlledScreen {

    private final SearchManager methods = new SearchManager();
    private final RouteManager routeMan = new RouteManager();
    private final FlightService flightService = new FlightService();
    private final ResultManager result = new ResultManager();
    private Boolean alreadyAdded = false;

    private static final Pattern VALID_NUM2_REGEX =
            Pattern.compile("^[0-9]{2}$");
    private static final Pattern VALID_NUM_REGEX =
            Pattern.compile("^[0-9]{1,5}$");
    ObservableList<Flight> oCachedTable = FXCollections.observableArrayList();

    @FXML
    private Button addButton;

    @FXML
    private TableView<Flight> table;

    @FXML
    private TableColumn<?, ?> arrivalTimeColumn;

    @FXML
    private TableColumn<?, ?> flightNumberColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> scheduledTimeColumn;

    @FXML
    private TableColumn<?, ?> seatsColumn;

    @FXML
    private ComboBox<String> cbDes;

    @FXML
    private ComboBox<String> cbDep;

    @FXML
    private TextField centPrice;

    @FXML
    private DatePicker date;

    @FXML
    private TextField hArr;

    @FXML
    private TextField hDep;

    @FXML
    private TextField mArr;

    @FXML
    private TextField mDep;

    @FXML
    private TextField seats;

    @FXML
    private TextField unPrice;

    @FXML
    private Button refreshButton;

    @FXML
    private TextField flightNumber;

    @FXML
    void clickAddButton(ActionEvent event) {
        try{
            validationFields();
            Time scheduledTime = new Time(Integer.parseInt(hDep.getText()),Integer.parseInt(mDep.getText()),0);
            Time arrivalTime = new Time(Integer.parseInt(hArr.getText()),Integer.parseInt(mArr.getText()),0);
            Double price = Double.parseDouble(unPrice.getText()) + (Double.parseDouble(centPrice.getText())) * 0.01;

            Flight newFlight = new Flight();
            newFlight.setRouteById(routeMan.getRouteByDepArr(cbDep.getValue(),cbDes.getValue()));
            newFlight.setScheduledDate(Date.valueOf(date.getValue()));
            newFlight.setScheduledTime(scheduledTime);
            newFlight.setArrivalTime(arrivalTime);
            newFlight.setPrice(price);
            newFlight.setSeats(Integer.parseInt(seats.getText()));
            newFlight.setFlightNumber("AES" + flightNumber.getText());
            flightService.persist(newFlight);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New Flight Confirmation");
            alert.setHeaderText("Nuovo volo aggiunto correttamente!");
            alert.setContentText("Attendere qualche secondo per l'aggiornamento dei voli");
            alert.showAndWait();
            alreadyAdded = true;
            loadTable();
        } catch (NoMatchException e ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setContentText("Controllare l'inserimento corretto dei parametri!");
            alert.showAndWait();
        } catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setContentText("Non lasciare campi vuoti!");
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        date.setDayCellFactory(methods.bookingRange(LocalDate.now()));
        refreshButton.setDisable(true);
        addButton.setDisable(true);
        cbDep.setItems(routeMan.getDepartures());
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        scheduledTimeColumn.setCellValueFactory(new PropertyValueFactory<>("scheduledTime"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
        table.setItems(oCachedTable);
    }
    @Override
    public void setScreenParent(ScreenContainer screenParent) {
        ScreenContainer myContainer = screenParent;
    }

    @FXML
    void findArrivals(ActionEvent event) {
        cbDes.getSelectionModel().clearSelection();
        cbDes.setItems(routeMan.getArrival(cbDep.getValue()));
    }

    @FXML
    void enableButton(ActionEvent event) {
        refreshButton.setDisable(false);
        addButton.setDisable(false);
    }

    @FXML
    void loadTable() {
        try{
            if(alreadyAdded){
                result.reloadFlight();
            }
            oCachedTable.clear();
            String dep = cbDep.getSelectionModel().getSelectedItem();
            String des = cbDes.getSelectionModel().getSelectedItem();
            oCachedTable.addAll(result.getList(dep, des, Date.valueOf(date.getValue())));

        }
        catch(NoMatchException e){
            table.setPlaceholder(new Label("Ancora nessun volo previsto!"));
        }

    }

    private void validationFields() throws NoMatchException,NumberFormatException{
            Matcher matcher = VALID_NUM2_REGEX.matcher(hDep.getText());
            if (!matcher.find() && Integer.parseInt(hDep.getText()) <= 22 && Integer.parseInt(hDep.getText()) >= 6) {
                throw new NoMatchException("Not Matched filed 1!\n");
            }
            matcher = VALID_NUM2_REGEX.matcher(mDep.getText());
            if (!matcher.find() && Integer.parseInt(mDep.getText()) <= 59) {
                throw new NoMatchException("Not Matched filed 2!\n");
            }
            matcher = VALID_NUM2_REGEX.matcher(hArr.getText());
            if (!matcher.find() && Integer.parseInt(hArr.getText()) <= 22 && Integer.parseInt(hArr.getText()) >= 6) {
                throw new NoMatchException("Not Matched filed 3!\n");
            }
            matcher = VALID_NUM2_REGEX.matcher(mArr.getText());
            if (!matcher.find() && Integer.parseInt(mArr.getText()) <= 59) {
                throw new NoMatchException("Not Matched filed 4!\n");
            }
            matcher = VALID_NUM_REGEX.matcher(unPrice.getText());
            if (!matcher.find() && Integer.parseInt(unPrice.getText()) <= 10000) {
                throw new NoMatchException("Not Matched filed 5!\n");
            }
            matcher = VALID_NUM2_REGEX.matcher(centPrice.getText());
            if (!matcher.find() && Integer.parseInt(centPrice.getText()) <= 99) {
                throw new NoMatchException("Not Matched filed 6!\n");
            }
            matcher = VALID_NUM_REGEX.matcher(seats.getText());
            if (!matcher.find() && Integer.parseInt(seats.getText()) <= 10000) {
                throw new NoMatchException("Not Matched filed 7!\n");
            }
            matcher = VALID_NUM_REGEX.matcher(flightNumber.getText());
            if (!matcher.find() && Integer.parseInt(seats.getText()) <= 9999) {
                throw new NoMatchException("Not Matched filed 7!\n");
            }
    }
}