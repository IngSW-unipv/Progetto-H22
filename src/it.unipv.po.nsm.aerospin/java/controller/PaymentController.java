package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Factory;
import model.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    Session session = Factory.getInstance().getSession();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void execute(){
        Stage stage = (Stage) Stage.getWindows().get(1);
//        try{payment}catch(ERRR){cancel()}
        session.setPaid(true);
        stage.close();
    }

    @FXML
    private void cancel(){
        Stage stage = (Stage) Stage.getWindows().get(1);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Order");
        alert.setContentText("Potrà tornare alla Home oppure concludere l'ordine");
        alert.showAndWait();
        stage.close();
    }
}
