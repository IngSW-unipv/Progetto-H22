package controller;

import controller.util.IControlledScreen;
import javafx.fxml.Initializable;
import view.ScreenContainer;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable, IControlledScreen {

    ScreenContainer myContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setScreenParent(ScreenContainer screenParent){
        myContainer = screenParent;
    }

/*    @FXML
    private void goToAccount(ActionEvent event){
        myContainer.setScreen(MainApplication.account);
    }*/
}