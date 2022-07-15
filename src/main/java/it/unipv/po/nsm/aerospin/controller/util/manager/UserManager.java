package controller.util.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.exception.NoMatchException;
import model.persistence.CachedFlights;
import model.persistence.entity.Flight;
import model.persistence.entity.User;
import model.persistence.service.UserService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserManager {
    UserService service = new UserService();
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$");
    private static final Pattern VALID_PWD_REGEX =
            Pattern.compile("^[0-9a-zA-Z!@#&–:;',?/*~$^+=<>]{8,20}$");

    public List<User> getEmployees(List<User> users){
        List<User> results = users.stream()
                .filter(o -> o.getUserType()>0)
                .collect(Collectors.toList());

        return results;
    }
    /**
     * Metodo che verifica il formato dell'e-mail inserita
     *
     * @throws NoMatchException Segnala se il confronto non è andato a buon fine
     */
    public static void checkMail(String text) throws NoMatchException {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(text);
        if(!matcher.find()){
            throw new NoMatchException("Not Matched email!\n");
        }
    }

    /**
     * Metodo che verifica il formato della password inserita
     *
     * @throws NoMatchException Segnala se il confronto non è andato a buon fine
     */
    public static void checkPwd(String text) throws NoMatchException {
        Matcher matcher = VALID_PWD_REGEX.matcher(text);
        if(!matcher.find()){
            throw new NoMatchException("Not Matched pwd!\n");
        }
    }
}
