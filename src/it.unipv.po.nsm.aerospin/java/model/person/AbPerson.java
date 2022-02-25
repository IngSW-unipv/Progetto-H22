package model.person;

public abstract class AbPerson implements IAccess {
    private String nome;
    private String cognome;
    private String email;
    private String pwd;

    public AbPerson(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public abstract void Create();
    public abstract void Edit();
    public abstract void Delete();
}