package controller;

import controller.util.IControlledScreen;
import controller.util.manager.AccountManager;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import model.exception.NoMatchException;
import model.persistence.entity.Booking;
import org.controlsfx.control.MasterDetailPane;
import view.ScreenContainer;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccountController implements Initializable, IControlledScreen {
    private final AccountManager methods = new AccountManager();

    @FXML private MasterDetailPane pane;
    @FXML private TextArea detail;
    @FXML private TableView<Booking> table;
    @FXML private TableColumn<Booking,String> number;
    @FXML private TableColumn<Booking, Time> date;
    @FXML private TableColumn<Booking, String> id;
    @FXML private TableColumn<Booking, String> price;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.setMasterNode(table);
        pane.setDetailNode(detail);

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setPlaceholder(new Label("Sto effettuando la ricerca, attendere"));
        table.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener<Integer>) change -> detail.setText(
                        methods.getDetailText(table.getSelectionModel().getSelectedItem())));
        number.setCellValueFactory(c -> new ReadOnlyStringWrapper(
                (table.getItems().indexOf(c.getValue()) + 1) + "°"));
        date.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        id.setCellValueFactory(c -> new SimpleStringProperty("APN" + c.getValue().getId()));
        price.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPrice() + " €"));
        Thread t1 = new Thread(() -> {
            try {
                    table.setItems(methods.getOrders());
            } catch (NoMatchException e) {
                    Platform.runLater(
                            () -> table.setPlaceholder(new Label("Nessun ordine trovato"))
                    );
            }
        });
        t1.start();
    }

    public void setScreenParent(ScreenContainer screenParent) {}

    @FXML
    private void support() throws IOException {
        Parent root1 = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("util/subscreen/Support.fxml")));
        Stage childStage = new Stage();
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.initStyle(StageStyle.TRANSPARENT);
        childStage.setScene(new Scene(root1));
        childStage.showAndWait();
    }
}