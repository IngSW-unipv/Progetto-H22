package util;

import javafx.scene.control.*;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.List;

public class ControllerMethods {

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


    private static final int maxMonth = 4;

    // Mantengo le date selezionabili tra Oggi e prossimi 4 Mesi
    public Callback<DatePicker, DateCell> dateRange(LocalDate from) {
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


}
