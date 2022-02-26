package model.booking.passenger;




public class Passenger{

    int id;
    ClassType classType;
    AgeGroup ageGroup;
    short eta;

    public int getId() {
        return id;
    }
    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    private void setAgeGroup(int eta) {
        if (eta <= AgeGroup.INFANTS.getMaxAge())
            ageGroup = AgeGroup.INFANTS;
        else if (eta <= AgeGroup.CHILDREN.getMaxAge())
            ageGroup = AgeGroup.CHILDREN;
        else if (eta <= AgeGroup.ADULTS.getMaxAge())
            ageGroup = AgeGroup.CHILDREN;

    }

    public Passenger(int id, String nome, String cognome, short eta) {
        super(nome, cognome);
        this.eta = eta;
        this.id = id;
        setAgeGroup(eta);
    }
}
