package com.example.baganturnamen;

public class Peserta {
    private String Nama;
    private Integer Umur;
    private String Unggulan;

    public Peserta(){

    }

    public Peserta(String nama, Integer umur, String unggulan) {
        this.Nama = nama;
        this.Umur = umur;
        this.Unggulan = unggulan;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
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
