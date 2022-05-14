package com.fyp.locale_lite.Model;

public class Order_model {
    String customer_email,mechanic_email,mechanic_city,mechanic_Phone;

    public Order_model(String customer_email, String mechanic_email, String mechanic_city, String mechanic_Phone) {
        this.customer_email = customer_email;
        this.mechanic_email = mechanic_email;
        this.mechanic_city = mechanic_city;
        this.mechanic_Phone = mechanic_Phone;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getMechanic_email() {
        return mechanic_email;
    }

    public void setMechanic_email(String mechanic_email) {
        this.mechanic_email = mechanic_email;
    }

    public String getMechanic_city() {
        return mechanic_city;
    }

    public void setMechanic_city(String mechanic_city) {
        this.mechanic_city = mechanic_city;
    }

    public String getMechanic_Phone() {
        return mechanic_Phone;
    }

    public void setMechanic_Phone(String mechanic_Phone) {
        this.mechanic_Phone = mechanic_Phone;
    }
}
