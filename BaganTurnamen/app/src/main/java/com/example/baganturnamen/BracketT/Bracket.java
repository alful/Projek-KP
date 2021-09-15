package com.example.baganturnamen.BracketT;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.baganturnamen.R;
import com.ventura.bracketslib.BracketsView;
import com.ventura.bracketslib.fragment.BracketsFragment;
import com.ventura.bracketslib.model.ColomnData;
import com.ventura.bracketslib.model.CompetitorData;
import com.ventura.bracketslib.model.MatchData;

import java.util.Arrays;

//import android.os.Bundle;
//import android.util.DisplayMetrics;
//
//import com.example.baganturnamen.BracketT.Fragment.BracketsFragment;
//import com.example.baganturnamen.BracketT.application.BracketsApplication;
//import com.example.baganturnamen.R;

public class Bracket extends AppCompatActivity {

    private BracketsFragment bracketsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bracket);
        BracketsView bracketsView = findViewById(R.id.bracket_view);
        CompetitorData afk=new CompetitorData("sasd","0");
        CompetitorData brazilSemiFinal = new CompetitorData("Brazil", "3");
        CompetitorData ssk = new CompetitorData("", "");
        CompetitorData englandSemiFinal = new CompetitorData("England", "1");
        CompetitorData argentinaSemiFinal = new CompetitorData("Argentina", "3");
        CompetitorData russiaSemiFinal = new CompetitorData("Russia", "2");
        CompetitorData brazilFinal = new CompetitorData("Brazil", "4");
        CompetitorData argentinaFinal = new CompetitorData("Argentina", "2");
        ssk.setName("asass");
        ssk.setScore("2");
        MatchData match1SemiFinal = new MatchData(brazilSemiFinal, englandSemiFinal);
        MatchData match2SemiFinal = new MatchData(argentinaSemiFinal, russiaSemiFinal);
        MatchData match3Final = new MatchData(brazilFinal, argentinaFinal);
        MatchData matchData=new MatchData(afk,ssk);


        ColomnData semiFinalColomn = new ColomnData(Arrays.asList(match1SemiFinal, match2SemiFinal));
        ColomnData colomnData = new ColomnData(Arrays.asList(matchData, match2SemiFinal));
        ColomnData finalColomn = new ColomnData(Arrays.asList(match3Final));
        bracketsView.setBracketsData(Arrays.asList(semiFinalColomn, finalColomn,colomnData));
        




    }
}