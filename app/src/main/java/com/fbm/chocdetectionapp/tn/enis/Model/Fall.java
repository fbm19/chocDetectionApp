package com.fbm.chocdetectionapp.tn.enis.model;

/**
 * Created by Asus on 05/12/2016.
 */

public class Fall {
    private String date;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fall(int id, String date) {
        this.date = date;
        this.id=id;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Fall(){}
}
