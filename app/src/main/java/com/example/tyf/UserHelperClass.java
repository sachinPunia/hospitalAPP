package com.example.tyf;

public class UserHelperClass {
    String Name,Bldg,Phone,Pincode,City,Time,DocTor,Date;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String bldg, String phone, String pincode, String city, String time, String doctor,String date) {
        this.Name = name;
        this.Bldg = bldg;
        this.Phone = phone;
        this.Pincode = pincode;
        this.City = city;
        this.Time= time;
        this.DocTor= doctor;
        this.Date=date;
    }

    public String getname() {
        return Name;
    }

    public void setname(String Name) {
        this.Name = Name;
    }

    public String getbldg() {
        return Bldg;
    }

    public void setbldg(String Bldg) {
        this.Bldg = Bldg;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getpincode() {
        return Pincode;
    }

    public void setpincode(String Pincode) {
        this.Pincode = Pincode;
    }

    public String getcity() {
        return City;
    }

    public void setcity(String City) {
        this.City = City;
    }


    public String gettime() {
        return Time;
    }

    public void settime(String Time) {
        this.Time = Time;
    }

    public String getdoctor() {
        return DocTor;
    }

    public void setdoctor(String Doctor) {
        this.DocTor = Doctor;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }



}
