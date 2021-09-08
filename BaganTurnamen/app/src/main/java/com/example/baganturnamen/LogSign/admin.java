package com.example.baganturnamen.LogSign;

public class admin {

    public admin(String nama, String email, String password, Integer manag, String UID, String ID) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        Manag = manag;
        this.UID = UID;
        this.ID = ID;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getManag() {
        return Manag;
    }

    public void setManag(Integer manag) {
        Manag = manag;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    String nama;
    String email;
    String password;
    Integer Manag;
    private String UID;
    private String ID;
    public admin(){

    }


}
