package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controller.util.IControlledScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import controller.util.manager.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Session;
import model.exception.NoMatchException;
import model.persistence.Encryption;
import model.persistence.entity.User;
import model.persistence.service.UserService;
import view.ScreenContainer;

public class AdminController implements Initializable, IControlledScreen {

    private ScreenContainer myContainer;
    private final Session session = Session.getInstance();
    private final UserService service = new UserService();
    private final UserManager manager = new UserManager();
    private final Encryption encryption = new Encryption();
    private List<User> cachedUsers;
    ObservableList<User> oUsersLoaded = FXCollections.observableArrayList();


    @FXML
    private ToggleGroup AccountType;

    @FXML
    private TextField passwordTextBox;

    @FXML
    private RadioButton radio1;

    @FXML
    private RadioButton radio2;

    @FXML
    private TableView<User> table;

    @FXML
    private TextField emailTextBox;

    @FXML
    private Label errLabel;

    @FXML
    private TableColumn<User, String> userType;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TableColumn<User, String> password;

    @Override
    public void setScreenParent(ScreenContainer screenParent) {
        myContainer = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }
    private void initTable() {
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        username.setCellValueFactory(new PropertyValueFactory<>("email"));
        password.setCellValueFactory(new PropertyValueFactory<>("pwd"));
        userType.setCellValueFactory(new PropertyValueFactory<>("userType"));
        cachedUsers = manager.getEmployees(service.findAll());
        oUsersLoaded = FXCollections.observableArrayList(cachedUsers);
        table.setItems(oUsersLoaded);
    }
    @FXML
    void clickAddButton(ActionEvent event) {
        errLabel.setText("");
        try {
            UserManager.checkMail(emailTextBox.getText());
            try {
                UserManager.checkPwd(passwordTextBox.getText());
                try{
                    User oldUser = service.findByEmail(emailTextBox.getText());
                    if (oldUser.getUserType() > 1){
                        oldUser.setPwd(encryption.encrypt(passwordTextBox.getText()));
                        if (AccountType.getSelectedToggle().equals(radio1))
                            oldUser.setUserType(2);
                        else if(AccountType.getSelectedToggle().equals(radio2))
                            oldUser.setUserType(3);
                        service.update(oldUser);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Register Modification");
                        alert.setHeaderText("Utente modificato");
                        alert.setContentText("Utente modificato");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Register Error");
                        alert.setContentText("Non è ancora possibile aggiornare la password dell'admin");
                        alert.showAndWait();
                    }

                } catch (NoMatchException e){
                    User newUser = new User();
                    newUser.setEmail(emailTextBox.getText());
                    newUser.setPwd(encryption.encrypt(passwordTextBox.getText()));

                    if (AccountType.getSelectedToggle().equals(radio1))
                        newUser.setUserType(2);
                    else if(AccountType.getSelectedToggle().equals(radio2))
                        newUser.setUserType(3);

                    service.persist(newUser);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Register Confirmation");
                    alert.setHeaderText("Utente nuovo aggiunto");
                    alert.setContentText("Utente nuovo aggiunto");
                    alert.showAndWait();
                }
                refreshTable();
            }
            catch (NoMatchException e){
                passwordTextBox.clear();
                errLabel.setText("Inserire Password da 8-20 caratteri");
            }
        } catch (NoMatchException e) {
            passwordTextBox.clear();
            errLabel.setText("Formato email non valido!");
        }
    }
    @FXML
    void clickRemoveButton(ActionEvent event) {
        if(table.getSelectionModel().getSelectedItem().getUserType() > 1){
            service.delete(table.getSelectionModel().getSelectedItem());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removal Confirmation");
            alert.setHeaderText("Utente rimosso correttamente");
            alert.setContentText("Utente rimosso correttamente");
            alert.showAndWait();
            refreshTable();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Removal Error");
            alert.setContentText("L'utente admin non può essere rimosso");
            alert.showAndWait();
        }


    }
    private void refreshTable(){
        cachedUsers = manager.getEmployees(service.findAll());
        oUsersLoaded.clear();
        oUsersLoaded.addAll(cachedUsers);
    }
}
