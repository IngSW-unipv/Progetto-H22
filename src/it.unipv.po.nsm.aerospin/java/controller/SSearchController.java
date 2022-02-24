package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private RadioButton ar;

    @FXML
    private RadioButton oneway;

    @FXML
    private ToggleGroup group = new ToggleGroup();

    @FXML
    private Button cerca = new Button();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<String> strings = new ArrayList<>();
        strings.add("Test1");
        strings.add("Test2");
        cb1.setItems((FXCollections.observableArrayList(strings)));
        cb2.setItems((FXCollections.observableArrayList(strings)));
        //gestire cb1 == cb2!!!!

//       Seleziona dalla combobox in base alla lettera selezionata??
//        cb1.textProperty().addListener((observable, oldValue, newValue) -> {
//            for (int i = 0; i < comboBoxItems.size(); i++) {
//                if (comboBoxItems.get(i).toLowerCase().contains(textField.getText().toLowerCase())) {
//                    myComboBox.getSelectionModel().select(i);
//                    myComboBox.show();
//                }
//            }
//        });
        
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

        ar.setToggleGroup(group);
        ar.setSelected(true);
        oneway.setToggleGroup(group);
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

    @FXML
    private void goToHome(ActionEvent event){
        myController.setScreen(MainApplication.home);
    }

    @FXML
    private void goToLogin(ActionEvent event){
        myController.setScreen(MainApplication.login);
    }
}
