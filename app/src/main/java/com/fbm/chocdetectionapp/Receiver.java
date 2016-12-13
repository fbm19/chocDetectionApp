package com.fbm.chocdetectionapp;

/**
 * Created by tahta on 03/05/2016.
 */
public class Receiver {
    private int _id;
    private String _number;
    private String _sms;

    public Receiver() {

    }

    @Override
    public String toString() {
        return "Receiver{" +
                "_id=" + _id +
                ", _number='" + _number + '\'' +
                ", _sms='" + _sms + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_number() {
        return _number;
    }

    public void set_number(String _number) {
        this._number = _number;
    }

    public String get_sms() {
        return _sms;
    }

    public void set_sms(String _sms) {
        this._sms = _sms;
    }

    public Receiver(int _id, String _sms,String _number) {
        this._id = _id;
        this._number = _number;
        this._sms = _sms;
    }

    public Receiver(String _number, String _sms) {
        this._number = _number;
        this._sms = _sms;
    }
}
