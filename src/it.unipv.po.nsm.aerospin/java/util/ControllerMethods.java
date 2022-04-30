package util;

import javafx.scene.control.*;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;
import model.persistence.CachedFlights;
import model.persistence.entity.Flight;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class ControllerMethods {

    CachedFlights searchResult = CachedFlights.getInstance();
    List<Flight> results = searchResult.findAll();

//    metodo goToScreen!!!

    /*  Quando premo una lettera mentre uso un ComboBox
     *  questo metodo seleziona e sposta il cursore sul primo item
     *  che inizia con la input letter
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
                        cbSelectionModel.select(i);
                        ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) cb.getSkin();
                        ListView<?> list = (ListView<?>) skin.getPopupContent();
                        list.scrollTo(i);
                        return;
                    }
                    else {
                        cbSelectionModel.selectNext();
                    }
                }
            }
        });
    }

    /*  Mantengo le date selezionabili tra
     *  Oggi e i prossimi 4 Mesi
     */
    private static final int maxMonth = 4;
    public Callback<DatePicker, DateCell> bookingRange(LocalDate from) {
        return new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate today = from;
                        LocalDate next = from.plusMonths(maxMonth);
                        setDisable(empty || date.compareTo(today) < 0 || date.compareTo(next) > 0);
                    }
                };
            }
        };
    }

    /*  Controllo se l'età selezionata è > 16
     *  e permetto di selezionare solo date passate
     *  come data di nascita
     */
    public boolean isMinor(LocalDate birthDate){
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        long years = age.getYears();
        return years < 16;
    }
    public Callback<DatePicker, DateCell> ageRange() {
        return new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate today = LocalDate.now().minusDays(1);
                        setDisable(empty || date.compareTo(today) > 0);
                    }
                };
            }
        };
    }

    /*  Controllo se posso selezionare a/r
     *  in base agli aereoporti selezionati
     */
    public boolean checkRoute(String dep, String ret) {
//        Flight a = results.

        if (true){
            return true;
        } else {
            return false;
        }


    }
}
