package model.person.employee;

import java.util.Date;

public class Employee {
    private String ID;
    private Date dataAssunzione;
    private double Stipendio;

    public Employee(String ID, Date dataAssunzione, double stipendio) {
        this.ID = ID;
        this.dataAssunzione = dataAssunzione;
        Stipendio = stipendio;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getDataAssunzione() {
        return dataAssunzione;
    }

    public void setDataAssunzione(Date dataAssunzione) {
        this.dataAssunzione = dataAssunzione;
    }

    public double getStipendio() {
        return Stipendio;
    }

    public void setStipendio(double stipendio) {
        Stipendio = stipendio;
    }
}
