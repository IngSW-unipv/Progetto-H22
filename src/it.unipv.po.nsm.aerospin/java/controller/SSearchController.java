package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;
import view.MainApplication;
import view.ScreensController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SSearchController implements Initializable, IControlledScreen {

    @FXML
    private ComboBox<String> cb1;

    @FXML
    private ComboBox<String> cb2;

    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date2;

    @FXML
    private RadioButton oneway;

    @FXML
    private Button cerca = new Button();

    private final ObservableList<String> options = FXCollections.observableArrayList(
            "Aab",
            "Aer",
            "Aeq",
            "Arx",
            "Byad",
            "Csca",
            "Csee",
            "Cfefe",
            "Cead",
            "Defea",
            "Dqeqe",
            "Fefaf",
            "Gert",
            "Wqad",
            "Xsad",
            "Zzz"
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<String> strings = new ArrayList<>();
//        strings = AirportService.findAll();
        strings.add("Test1");
        strings.add("Test2");
        cb1.setItems(options);
        cb2.setItems(options);
        //gestire cb1 == cb2!!!!

        selectOptionOnKey(cb1, options);
        selectOptionOnKey(cb2, options);

        // Metodo per mantenere le date selezionabili tra Oggi e prossimi 4 Mesi
        Callback<DatePicker, DateCell> callDate = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate today = LocalDate.now();
                        LocalDate next = LocalDate.now().plusMonths(4);
                        setDisable(empty || date.compareTo(today) < 0 || date.compareTo(next) > 0);
                    }
                };
            }
        };
        date1.setDayCellFactory(callDate);
        date2.setDayCellFactory(callDate);
        //gestire date2 < date1!!!!


        // Se seleziono Solo Andata non posso inserire Data Ritorno
        date2.disableProperty().bind(oneway.selectedProperty());

//        BooleanBinding booleanBind =    cb1.valueProperty().isNull()
//                .or(cb2.valueProperty().isNull())
//                .or(date1.valueProperty().isNull())
//                .or(date2.valueProperty().isNull()
//                .and(ar.selectedProperty()));
//        cerca.disableProperty().bind(booleanBind);
    }

    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    /* When you press a letter key
     *  this method will search for an option(item) that starts with the input letter key
     *  and it selects the first occurrence in combo box
     */
    public void selectOptionOnKey(ComboBox<String> cb, List<String> strings) {
        cb.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();

            if (keyCode.isLetterKey()) {
                char key = keyCode.getName().charAt(0);

                SingleSelectionModel<String> cbSelectionModel = cb.getSelectionModel();

                cbSelectionModel.select(0);

                for (int i = 0; i < strings.size(); i++) {
                    if(cbSelectionModel.getSelectedItem().charAt(0) == key) {
                        // option which starts with the input letter found -> select it
//                        cbSelectionModel.select(i);
                        cbSelectionModel.select(i);
                        ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) cb.getSkin();
                        ListView<?> list = (ListView<?>) skin.getPopupContent();
                        list.scrollTo(i);
                        /* Before exiting the function it would be nice if after the selection,
                           the combo box would auto slide/jump to the option which is selected.
                           I don't know how to do that. */
                        return;
                    }
                    else
                        cbSelectionModel.selectNext();
                }
            }
        });
    }

    @FXML
    private void goToHome(ActionEvent event){
        myController.setScreen(MainApplication.home);
    }

    @FXML
    private void goToLogin(ActionEvent event){
        myController.setScreen(MainApplication.login);
    }
}
