package com.fyp.locale_lite.Model;

public class ServiceProviderModel {
    String catagories,fname,lname,emailid,phonenumber,city;

    public ServiceProviderModel(String catagories, String fname, String lname, String emailid, String phonenumber, String city) {
        this.catagories = catagories;
        this.fname = fname;
        this.lname = lname;
        this.emailid = emailid;
        this.phonenumber = phonenumber;
        this.city = city;
    }

    public ServiceProviderModel() {
    }

    public String getCatagories() {
        return catagories;
    }

    public void setCatagories(String catagories) {
        this.catagories = catagories;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
