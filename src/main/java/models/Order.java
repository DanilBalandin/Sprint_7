package models;

public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;
    public void Order(){}
    public Order withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }
    public Order withLastName(String lastName){
        this.lastName = lastName;
        return this;
    }
    public Order withAddress(String address){
        this.address = address;
        return this;
    }
    public Order withMetroStation(String metroStation){
        this.metroStation = metroStation;
        return this;
    }
    public Order withPhone(String phone){
        this.phone = phone;
        return this;
    }
    public Order withRentTime(int rentTime){
        this.rentTime = rentTime;
        return this;
    }
    public Order withDeliveryDate(String deliveryDate){
        this.deliveryDate = deliveryDate;
        return this;
    }
    public Order withComment(String comment){
        this.comment = comment;
        return this;
    }
    public Order withColor(String[] color){
        this.color = color;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }
}