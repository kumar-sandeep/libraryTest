package com.example.sandeep.test;

import java.util.Date;

public class UserModel {
    String email_id;
    String name;
    String mobile_no;
    String date;

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserModel() {
    }

    public UserModel(String email_id, String name, String mobile_no, String date) {
        this.email_id = email_id;
        this.name = name;
        this.mobile_no = mobile_no;
        this.date = date;
    }
}
