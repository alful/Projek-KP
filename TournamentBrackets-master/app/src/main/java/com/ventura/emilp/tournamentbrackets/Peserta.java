package com.ventura.emilp.tournamentbrackets;

public class Peserta {
    String SNama;

    public Peserta(){

    }

    public Peserta(String SNama) {
        this.SNama = SNama;
    }

    public String getSNama() {
        return SNama;
    }

    public void setSNama(String SNama) {
        this.SNama = SNama;
    }
}
