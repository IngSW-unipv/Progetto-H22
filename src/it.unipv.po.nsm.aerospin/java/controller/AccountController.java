package controller;

import controller.util.IControlledScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.persistence.entity.Order;
import org.controlsfx.control.MasterDetailPane;
import org.controlsfx.control.PropertySheet;
import view.ScreenContainer;

import java.net.URL;
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

}