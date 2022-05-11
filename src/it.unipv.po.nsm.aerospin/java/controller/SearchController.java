package controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Factory;
import model.util.Session;
import model.util.manager.SearchManager;
import view.ScreenContainer;
import org.controlsfx.control.SearchableComboBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchController implements Initializable, IControlledScreen {

    ScreenContainer myContainer;
    Session session = Factory.getInstance().getSession();
    SearchManager methods = new SearchManager();

    @FXML private SearchableComboBox<String> scbDep;
    @FXML private SearchableComboBox<String> scbRet;

    @FXML private DatePicker date1;
    @FXML private DatePicker date2;

    @FXML private RadioButton oneway;
    @FXML private RadioButton ar;

    @FXML private Label errLabel;

    private SimpleBooleanProperty error = new SimpleBooleanProperty(false);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Se seleziono Solo Andata non posso inserire Data Ritorno
        date1.setDayCellFactory(methods.bookingRange(LocalDate.now()));
        date2.disableProperty().bind(oneway.selectedProperty().or(date1.valueProperty().isNull()));

        scbDep.setItems(methods.getDepartures());
        errLabel.visibleProperty().bind(error);
    }

    public void setScreenParent(ScreenContainer screenParent) {
        myContainer = screenParent;
    }

    @FXML
    private void findArrivals (ActionEvent event){
            //controlla che venga resettato la combobox
        scbRet.getSelectionModel().clearSelection();
        scbRet.setItems(methods.getArrivals(scbDep.getValue()));
        oneway.setSelected(true);
        error.set(false);
    }

    @FXML
    private void checkRoute(ActionEvent event){
        if (methods.checkRoute(scbRet.getValue(), scbDep.getValue())){
            ar.setDisable(false);
            error.set(false);
        } else {
            oneway.setSelected(true);
            ar.setDisable(true);
            error.set(true);
        }
    }

    //Gestisco date2>>date1
    @FXML
    private void returnDate (ActionEvent event){
        date2.setValue(null);
        date2.setDayCellFactory(methods.bookingRange(date1.getValue()));
    }

    @FXML
    private void goToResult(ActionEvent event) throws IOException {
        if (validateFields()) {
            session.setOneway(oneway.isSelected());
            session.getInfo().clear();
            session.addInfo(scbDep.getSelectionModel().getSelectedItem());
            session.addInfo(scbRet.getSelectionModel().getSelectedItem());
            session.addInfo(date1.getValue().toString());
            if (!(session.isOneway())) {
                session.addInfo(date2.getValue().toString());
            }
            myContainer.setScreen(Factory.getResult());
        }
    }

    public boolean validateFields(){
        if( scbRet.getSelectionModel().isEmpty() | date1.getValue() == null | (date2.getValue() == null && ar.isSelected())){
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
