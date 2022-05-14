package com.fyp.locale_lite.Model;

public class ServiceProviderModel {
    String catagories,fname,lname,emailid,phonenumber,city,document_Id,lat,longi;

    public ServiceProviderModel(String catagories, String fname, String lname, String emailid, String phonenumber, String city, String document_Id, String lat, String longi) {
        this.catagories = catagories;
        this.fname = fname;
        this.lname = lname;
        this.emailid = emailid;
        this.phonenumber = phonenumber;
        this.city = city;
        this.document_Id = document_Id;
        this.lat = lat;
        this.longi = longi;
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

    public String getDocument_Id() {
        return document_Id;
    }

    public void setDocument_Id(String document_Id) {
        this.document_Id = document_Id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }
}
