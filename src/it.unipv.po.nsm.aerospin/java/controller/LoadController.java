package controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.persistence.CachedFlights;
import util.ConnectionDBException;
import view.Factory;
import view.ScreensController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class LoadController implements Initializable, IControlledScreen {

    ScreensController myController;
    CachedFlights searchResult = CachedFlights.getInstance();
//    MainController mainController = new MainController();
    Timer timer = new Timer();

    @FXML private Label loading;

    public LoadController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Task<Void> task = new Task<Void>() {

            @Override
            public Void call() throws InterruptedException, ConnectionDBException {
                try {

                    updateMessage("Loading flights...");
                    searchResult.findAll();

                } catch (ConnectionDBException e) {
                    loading.setStyle("-fx-text-fill: #d70000");
                    updateMessage("Errore nel Caricamento");
                    TimeUnit.SECONDS.sleep(5);
                    System.exit(1);
                }

                return null;
            }
        };

        task.setOnSucceeded(e -> {
            try {
                myController.setScreen(Factory.getHome());
                //mostra/nascondi bottoni
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        task.messageProperty().addListener((obs, oldVal, newVal) -> {
            loading.setText(newVal);
        });

        new Thread(task).start();

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
