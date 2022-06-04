package controller;

import controller.util.IControlledScreen;
import controller.util.manager.AccountManager;
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
import model.persistence.entity.Flight;
import model.persistence.entity.Orders;
import org.controlsfx.control.MasterDetailPane;
import view.ScreenContainer;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccountController implements Initializable, IControlledScreen {

    ScreenContainer myContainer;
    AccountManager methods = new AccountManager();

    @FXML private MasterDetailPane pane;
    @FXML private TextArea detail;

    @FXML private TableView<Orders> table;
    @FXML private TableColumn<Orders,String> number;
    @FXML private TableColumn<Orders, Time> date;
    @FXML private TableColumn<Orders, String> id;
    @FXML private TableColumn<Flight, String> price;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pane.setMasterNode(table);
        pane.setDetailNode(detail);

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setPlaceholder(new Label("Sto effettuando la ricerca, attendere"));

        number.setCellValueFactory(c -> new ReadOnlyStringWrapper(table.getItems().indexOf(c.getValue()) + ""));
//        number.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
//            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> p) {
//                return new ReadOnlyObjectWrapper(table.getItems().indexOf(p.getValue()) + "");
//            }
//        });
        date.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        price.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPrice() + " €"));
        //thread
        try {
            table.setItems(methods.getOrders());
        } catch (NoMatchException e) {
            table.setPlaceholder(new Label("Nessun ordine trovato"));
        }

        table.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener<Integer>) change -> detail.setText(detailText()));

    }

    private String detailText() {
        return "Get busy living"
//                + newLine
                + "or"
//                + newLine
                + "get busy dying."
//                + newLine
                + "--Stephen King";
    }

    public void setScreenParent(ScreenContainer screenParent){
        myContainer = screenParent;
    }

    @FXML
    private void support() throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("util/subscreen/Support.fxml")));
        Stage childStage = new Stage();
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.initStyle(StageStyle.TRANSPARENT);
        childStage.setScene(new Scene(root1));
        childStage.showAndWait();
    }

}