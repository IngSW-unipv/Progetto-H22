package controller;

import controller.util.IControlledScreen;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.persistence.CachedFlights;
import model.exception.DBException;
import model.Factory;
import view.ScreenContainer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class LoadController implements Initializable, IControlledScreen {
    private ScreenContainer myContainer;
    private final CachedFlights service = CachedFlights.getInstance();

    @FXML private Label loading;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Task<Void> task = new Task<>() {
            @Override
            public Void call() throws InterruptedException, DBException {
                try {
                        updateMessage("Loading flights...");
                        service.findAll();
                } catch (DBException e) {
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
            } catch (IOException exception) {
                    exception.printStackTrace();
            }
        });

        task.messageProperty().addListener((obs, oldVal, newVal) -> loading.setText(newVal));

        new Thread(task).start();
    }

    @Override
    public void setScreenParent(ScreenContainer screenParent) {
        myContainer = screenParent;
    }
}
