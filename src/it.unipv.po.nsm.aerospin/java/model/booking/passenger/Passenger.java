package model.booking.passenger;

//import model.person.AbPerson;
//
//public class Passenger extends AbPerson {
//
//    int id;
//    ClassType classType;
//    AgeGroup ageGroup;
//    short age;
//
//    public int getId() {
//        return id;
//    }
//    public ClassType getClassType() {
//        return classType;
//    }
//
//    public void setClassType(ClassType classType) {
//        this.classType = classType;
//    }
//
//    public AgeGroup getAgeGroup() {
//        return ageGroup;
//    }
//
//    private void setAgeGroup(int eta) {
//        if (eta <= AgeGroup.INFANTS.getMaxAge())
//            ageGroup = AgeGroup.INFANTS;
//        else if (eta <= AgeGroup.CHILDREN.getMaxAge())
//            ageGroup = AgeGroup.CHILDREN;
//        else if (eta <= AgeGroup.ADULTS.getMaxAge())
//            ageGroup = AgeGroup.CHILDREN;
//
//    }
//
//    public Passenger(int id, String name, String surname, short age) {
//        super(name, surname);
//        this.age = age;
//        this.id = id;
//        setAgeGroup(age);
//    }
//}
