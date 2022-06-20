package controller;

import com.jfoenix.controls.JFXToggleButton;
import controller.util.IControlledScreen;
import controller.util.manager.ResultManager;
import controller.util.manager.SearchManager;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.Factory;
import model.exception.NoMatchException;
import org.controlsfx.control.SearchableComboBox;
import view.ScreenContainer;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Classe Controller, relativa al Pattern MVC, che si occupa di gestire la logica dell'applicativo e le richieste del cliente.
 * Classe contenente l'interazione con JavaFX.
 *
 * @author GruppoNoSuchMethod
 */
public class SearchController implements Initializable, IControlledScreen {
    private ScreenContainer myContainer;
    private final SearchManager methods = new SearchManager();
    private final ResultManager results = new ResultManager();
    private final Info info = Factory.getInstance().getSession().getInfo();

    @FXML private SearchableComboBox<String> scbDep;
    @FXML private SearchableComboBox<String> scbRet;
    @FXML private DatePicker date1;
    @FXML private DatePicker date2;
    @FXML private JFXToggleButton selectToggle;
    @FXML private Label errLabel;

    private final SimpleBooleanProperty error = new SimpleBooleanProperty(false);

    /**
     * Metodo che si occupa di gestire le operazioni dell'interfaccia grafica di selezione della ricerca solo andata o andata/ritorno.
     *
     * @param url URL della risorsa.
     * @param rb Oggetto locale.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date1.setDayCellFactory(methods.bookingRange(LocalDate.now()));
        // Se seleziono Solo Andata non posso inserire Data Ritorno
        date2.disableProperty().bind(
                selectToggle.selectedProperty().not().or(date1.valueProperty().isNull()));
        scbDep.setItems(methods.getDepartures());

        errLabel.visibleProperty().bind(error);
        selectToggle.selectedProperty().addListener((o, o1, o2) -> {
            if (selectToggle.isSelected()){
                    selectToggle.setText("Andata e Ritorno");
            } else {
                    selectToggle.setText("Solo Andata");
                    date2.setValue(null);
            }
        });
    }

    public void setScreenParent(ScreenContainer screenParent) {
        myContainer = screenParent;
    }

    /**
     * Metodo che si occupa di verificare la presenza di un aeroporto di arrivo.
     */
    @FXML
    private void findArrivals() {
        scbRet.getSelectionModel().clearSelection();
        scbRet.setItems(methods.getArrivals(scbDep.getValue()));
        selectToggle.setSelected(false);
        selectToggle.setDisable(true);
        error.set(false);
    }

    /**
     * Metodo che si occupa di verificare la presenza di una rotta fra gli aeroporti selezionati.
     */
    @FXML
    private void checkRoute() {
        selectToggle.setSelected(false);
        if (methods.checkRoute(scbRet.getValue(), scbDep.getValue())){
            error.set(false);
            selectToggle.setDisable(false);
        } else {
            selectToggle.setDisable(true);
            error.set(true);
        }
    }

    //Gestisco date2>>date1
    /**
     * Metodo che si occupa di gestire date di ritorno successive a quelle di andata.
     */
    @FXML
    private void returnDate() {
        date2.setValue(null);
        date2.setDayCellFactory(methods.bookingRange(date1.getValue().plusDays(1)));
    }

    /**
     * Metodo che si occupa di gestire le operazioni dell'interfaccia grafica di ricerca del volo.
     */
    @FXML
    private void goToResult() {
        try {
                validateFields();
                info.setOneway(!selectToggle.isSelected());
                info.setDep(scbDep.getSelectionModel().getSelectedItem());
                info.setRet(scbRet.getSelectionModel().getSelectedItem());
                info.setDateDep(date1.getValue());
                if(selectToggle.isSelected()) {
                    info.setDateRet(date2.getValue());
                }
                results.getList(info.getDep(), info.getRet(), info.getDateDep());
                myContainer.setScreen(Factory.getResult());
        } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setContentText("Inserire tutti i campi prima di procedere!");
                alert.showAndWait();
        } catch (NoMatchException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Match");
                alert.setContentText("Nessun volo di Andata disponibile in questa data");
                alert.showAndWait();
        }
    }

    /**
     * Metodo che si occupa di verificare la validità dei campi inseriti.
     *
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O.
     */
    public void validateFields() throws IOException {
        if( scbRet.getSelectionModel().isEmpty() || date1.getValue() == null
            || (date2.getValue() == null && selectToggle.isSelected())) {
                throw new IOException();
        }
    }
}
