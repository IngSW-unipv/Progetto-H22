package controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.persistence.CachedFlights;
import model.util.exception.NetworkException;
import model.Factory;
import view.ScreenContainer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class LoadController implements Initializable, IControlledScreen {

    ScreenContainer myContainer;
    CachedFlights searchResult = CachedFlights.getInstance();

    @FXML private Label loading;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Task<Void> task = new Task<Void>() {

            @Override
            public Void call() throws InterruptedException, NetworkException {
                try {

                    updateMessage("Loading flights...");
                    searchResult.findAll();

                } catch (NetworkException e) {
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
                myContainer.setScreen(Factory.getHome());
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
    public void setScreenParent(ScreenContainer screenParent) {
        myContainer = screenParent;
    }
}
