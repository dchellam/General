package com.bde.keepassnew;

public class Entries {
    private int _id;
    private String _entryName;
    private String _userName;
    private String _password;

    //Added this empty constructor if we ever want to create the object and assign it later.
    public Entries(){

    }
    public Entries(String entryName, String userName, String password) {
        this._entryName = entryName;
        this._userName = userName;
        this._password = password;
    }

    public int get_id() {
        return _id;
    }

    public String get_entryName() {
        return _entryName;
    }

    public String get_userName() {
        return _userName;
    }

    public String get_password() {
        return _password;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_entryName(String _entryName) {
        this._entryName = _entryName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
