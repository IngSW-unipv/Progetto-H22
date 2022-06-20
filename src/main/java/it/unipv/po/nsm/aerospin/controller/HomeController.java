package controller;

import controller.util.IControlledScreen;
import javafx.fxml.Initializable;
import view.ScreenContainer;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe Controller, relativa al Pattern MVC, che si occupa di gestire la logica dell'applicativo.
 *
 * @author GruppoNoSuchMethod
 */
public class HomeController implements Initializable, IControlledScreen {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @Override
    public void setScreenParent(ScreenContainer screenParent) {}
}
