package controller;

import controller.util.IControlledScreen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.persistence.entity.Order;
import org.controlsfx.control.MasterDetailPane;
import org.controlsfx.control.PropertySheet;
import view.ScreenContainer;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccountController implements Initializable, IControlledScreen {

    ScreenContainer myContainer;

    @FXML private MasterDetailPane pane;
    @FXML private TableView<Order> table;
    @FXML private PropertySheet property;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pane.setMasterNode(table);
        pane.setDetailNode(property);

    }

    public void setScreenParent(ScreenContainer screenParent){
        myContainer = screenParent;
    }

    @FXML
    private void support() throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("util/Support.fxml")));
        Stage childStage = new Stage();
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.initStyle(StageStyle.TRANSPARENT);
        childStage.setScene(new Scene(root1));
        childStage.showAndWait();
    }

}