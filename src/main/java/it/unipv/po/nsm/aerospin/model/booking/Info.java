package model.booking;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Classe che si occupa di gestire le informazioni della prenotazione relativa al viaggio.
 *
 * @author GruppoNoSuchMethod
 */
public class Info {
    private boolean oneway = true;
    private boolean paid = false;
    private String dep;
    private String ret;
    private Date dateDep;
    private Date dateRet;
    private String cardNumber;

    public boolean isOneway() {
        return oneway;
    }

    public void setOneway(boolean oneway) {
        this.oneway = oneway;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public Date getDateDep() {
        return dateDep;
    }

    public void setDateDep(LocalDate dateDep) {
        this.dateDep = Date.valueOf(dateDep);
    }

    public Date getDateRet() {
        return dateRet;
    }

    public void setDateRet(LocalDate dateRet) {
        this.dateRet = Date.valueOf(dateRet);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
