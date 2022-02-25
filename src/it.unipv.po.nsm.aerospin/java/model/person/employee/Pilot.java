package model.person.employee;

import java.util.Date;

public class Pilot {
    private double oreVolo;
    private Date licExp;

    public Pilot(double oreVolo, Date licExp) {
        this.oreVolo = oreVolo;
        this.licExp = licExp;
    }

    public double getOreVolo() {
        return oreVolo;
    }

    public void setOreVolo(double oreVolo) {
        this.oreVolo = oreVolo;
    }

    public Date getLicExp() {
        return licExp;
    }

    public void setLicExp(Date licExp) {
        this.licExp = licExp;
    }
}
