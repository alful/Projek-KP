package com.example.baganturnamen;

public class Pertandingan {
    private String pemenangB1;
    private String pemenangB2;
    private String pemenangB3;
//    private Integer skorpemenangB1;

    public Pertandingan(){

    }

    public Pertandingan(String pemenangB1, String pemenangB2, String pemenangB3) {
        this.pemenangB1 = pemenangB1;
        this.pemenangB2 = pemenangB2;
        this.pemenangB3 = pemenangB3;
    }

    public String getPemenangB1() {
        return pemenangB1;
    }

    public void setPemenangB1(String pemenangB1) {
        this.pemenangB1 = pemenangB1;
    }

    public String getPemenangB2() {
        return pemenangB2;
    }

    public void setPemenangB2(String pemenangB2) {
        this.pemenangB2 = pemenangB2;
    }

    public String getPemenangB3() {
        return pemenangB3;
    }

    public void setPemenangB3(String pemenangB3) {
        this.pemenangB3 = pemenangB3;
    }
}
