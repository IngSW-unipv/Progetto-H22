package model.person;

public class User implements IAccess{

    private String email;
    private String pwd;

    public User(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPwd() {
        return pwd;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
