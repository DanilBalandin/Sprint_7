package models;

public class Courier {
    private String login;
    private String password;
    private String firstName;

    public void Courier(){
    }
    public Courier withLogin(String login){
        this.login = login;
        return this;
    }

    public Courier withPassword(String password){
        this.password = password;
        return this;
    }
    public Courier withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}