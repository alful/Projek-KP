package com.example.baganturnamen;

public class Peserta {
    private String Nama;
    private String Club;
    private Integer Umur;
    private String Unggulan;

//    public History history;
//
//    public class History{
//
//    }



    public Peserta(){

    }

    public Peserta(String nama, String club, Integer umur, String unggulan) {
        this.Nama = nama;
        this.Club = club;
        this.Umur = umur;
        this.Unggulan = unggulan;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getClub() {
        return Club;
    }

    public void setClub(String club) {
        Club = club;
    }

    public Integer getUmur() {
        return Umur;
    }

    public void setUmur(Integer umur) {
        Umur = umur;
    }

    public String getUnggulan() {
        return Unggulan;
    }

    public void setUnggulan(String unggulan) {
        Unggulan = unggulan;
    }
}
