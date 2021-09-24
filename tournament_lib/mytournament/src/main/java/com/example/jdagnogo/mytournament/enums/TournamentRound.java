package com.example.jdagnogo.mytournament.enums;

public enum TournamentRound {
    SemiA(2),
    SemiB(0),
    SemiC(0),
//    SemiD(0),
    SemiFinal(2),
    Final(1);


    private int round;

    TournamentRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }
}
