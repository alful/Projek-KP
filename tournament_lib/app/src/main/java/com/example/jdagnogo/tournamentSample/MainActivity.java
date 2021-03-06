package com.example.jdagnogo.tournamentSample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jdagnogo.mytournament.enums.TournamentRound;
import com.example.jdagnogo.mytournament.model.Match;
import com.example.jdagnogo.mytournament.model.Team;
import com.example.jdagnogo.mytournament.model.Tournament;
import com.example.jdagnogo.mytournament.TournamentView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TournamentView tournamentView;
    Team arsenal, real, milan, psg, arth, mahes, ace, sabo, kosong;
    List<Team> allTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        init();
    }

    private void init() {
        setContentView(R.layout.activity_main);

        findViewById(R.id.set_teams).setOnClickListener(this);
        findViewById(R.id.reset).setOnClickListener(this);
        findViewById(R.id.simulate).setOnClickListener(this);

        // Create all teams
        arsenal = new Team("Arsenal");
        real = new Team("Real Madrid");
        milan = new Team("Milan AC");
        psg = new Team("PSG");
        arth = new Team("arth");
        mahes = new Team("mahes");
        ace = new Team("ace");
        sabo = new Team("sabo");

        //Add team into the tournament
        allTeam = new ArrayList<>();
        allTeam.add(arsenal);
        allTeam.add(real);
        allTeam.add(milan);
        allTeam.add(psg);
//        allTeam.add(arth);
//        allTeam.add(mahes);
//        allTeam.add(ace);
        if (allTeam.size() <6)
        {
//            if (allTeam.size() %2 != 0)
//            {
//
//                kosong = new Team("");
//                allTeam.add(kosong);
//            }

            while (allTeam.size()<=7)
            {
                kosong = new Team("");
                allTeam.add(kosong);
            }

        }
//        allTeam.add(sabo);

        tournamentView = (TournamentView) findViewById(R.id.tournament_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
