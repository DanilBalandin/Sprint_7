package models;

public class CourierCreds {
    private String login;
    private String password;
    public CourierCreds(String login, String password){
        this.login = login;
        this.password = password;
    }
    public static CourierCreds credsFrom(Courier courier){
        return new CourierCreds(courier.getLogin(), courier.getPassword());
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
}