package com.example.baganturnamen.BracketT;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.baganturnamen.R;
import com.example.jdagnogo.mytournament.TournamentPeserta;
import com.example.jdagnogo.mytournament.TournamentView;
import com.example.jdagnogo.mytournament.enums.TournamentRound;
import com.example.jdagnogo.mytournament.model.Match;
import com.example.jdagnogo.mytournament.model.Team;
import com.example.jdagnogo.mytournament.model.Tournament;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Bracket2 extends AppCompatActivity implements View.OnClickListener {

    private TournamentPeserta tournamentView;
    Team arsenal, real, milan, psg, arth, mahes, ace, sabo, kosong;
    List<Team> allTeam;
    ArrayList<String> ALNama = new ArrayList<String>();
    private static int SPLASH_SCREEN = 2000;

    //    Button set_team,reset,simulate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bracket2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        ALNama = new ArrayList<String>();

        ALNama=getIntent().getStringArrayListExtra("namas");

        init();
    }

    private void init() {
        setContentView(R.layout.activity_bracket2);

        findViewById(R.id.set_teams).setOnClickListener(this);
        findViewById(R.id.reset).setOnClickListener(this);
        findViewById(R.id.simulate).setOnClickListener(this);

        // Create all teams = jumlah team sesuai dengan jumlah arraylist
        allTeam = new ArrayList<>();

        for (int a=0;a<=ALNama.size()-1;a++)
        {
//            Log.d("TAG", "iniasdt: "+ALNama.get(a));
//
//            if (ALNama.get(a) != "")
//            {
                arsenal=new Team(ALNama.get(a));
                Log.d("TAG", "init: "+arsenal);
                Log.d("TAG", "iniasdt: "+ALNama.get(a));

                allTeam.add(arsenal);
//            }else
//            {
//                break;
//            }
        }
//        arsenal = new Team(ALNama.get(0));
//        real = new Team(ALNama.get(1));
//        milan = new Team(ALNama.get(2));
//        psg = new Team(ALNama.get(3));
//        arth = new Team(ALNama.get(4));
//        mahes = new Team(ALNama.get(5));
//        ace = new Team(ALNama.get(6));
//        sabo = new Team(ALNama.get(7));


//        real = new Team("Real Madrid");
//        milan = new Team("Milan AC");
//        psg = new Team("PSG");
//        arth = new Team("arth");
//        mahes = new Team("mahes");
//        ace = new Team("ace");
//        sabo = new Team("sabo");

        //Add team into the tournament
//        allTeam = new ArrayList<>();
//        allTeam.add(arsenal);
//        allTeam.add(real);
//        allTeam.add(milan);
//        allTeam.add(psg);
//        allTeam.add(arth);
//        allTeam.add(mahes);
//        allTeam.add(ace);
//        allTeam.add(sabo);

//        Log.d("TAG", "insadit: "+allTeam.size());
        if (allTeam.size() <8)
        {
//            if (allTeam.size() %2 != 0)
//            {
//
//                kosong = new Team("");
//                allTeam.add(kosong);
//            }

            while (allTeam.size()<8)
            {
                kosong = new Team("");
                allTeam.add(kosong);
            }

        }

        tournamentView = (TournamentPeserta) findViewById(R.id.tournament_view);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_teams:
                tournamentView.setTournament(new Tournament(allTeam));
                Log.d("TAG", "Klik Set Team "+allTeam.get(0).getName());
                tournamentView.startTournament();

                break;
            case R.id.simulate:
                Match semiA = new Match.MatchResultBuilder(arsenal, real).currentPosition(TournamentRound.SemiA).scoreA(0).scoreB(1).build();
                Match semiB = new Match.MatchResultBuilder(milan, psg).currentPosition(TournamentRound.SemiB).scoreA(1).scoreB(4).build();
                Match semiC = new Match.MatchResultBuilder(arth, mahes).currentPosition(TournamentRound.SemiA).scoreA(0).scoreB(1).build();
                Match semiD = new Match.MatchResultBuilder(ace, sabo).currentPosition(TournamentRound.SemiB).scoreA(1).scoreB(4).build();

                Match finalMatch = new Match.MatchResultBuilder(arsenal, milan).currentPosition(TournamentRound.Final).scoreA(4).scoreB(0).build();


                // Create a tournament
                Tournament championLeague = new Tournament.TournamentBuilder(allTeam).addMatch(semiA).addMatch(semiB).addMatch(semiC).addMatch(semiD).build();
                championLeague.addMatch(finalMatch);

                tournamentView.setTournament(championLeague);
                tournamentView.startTournament();
                tournamentView.simulate();

                break;
            case R.id.reset:
                init();
                break;

        }
    }



}