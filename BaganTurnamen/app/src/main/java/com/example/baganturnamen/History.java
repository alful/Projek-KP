package com.example.baganturnamen;

public class History {
    private Integer Babak1;
    private Integer Babak2;
    private Integer Babak3;

    public History (){

    }

    public History(Integer babak1, Integer babak2, Integer babak3) {
        Babak1 = babak1;
        Babak2 = babak2;
        Babak3 = babak3;
    }

    public Integer getBabak1() {
        return Babak1;
    }

    public void setBabak1(Integer babak1) {
        Babak1 = babak1;
    }

    public Integer getBabak2() {
        return Babak2;
    }

    public void setBabak2(Integer babak2) {
        Babak2 = babak2;
    }

    public Integer getBabak3() {
        return Babak3;
    }

    public void setBabak3(Integer babak3) {
        Babak3 = babak3;
    }
}
