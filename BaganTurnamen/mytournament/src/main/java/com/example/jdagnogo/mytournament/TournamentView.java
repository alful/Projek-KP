package com.example.jdagnogo.mytournament;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.jdagnogo.mytournament.anim.CustomAnim;
import com.example.jdagnogo.mytournament.enums.TournamentRound;
import com.example.jdagnogo.mytournament.model.BinderTeamTextView;
import com.example.jdagnogo.mytournament.model.Match;
import com.example.jdagnogo.mytournament.model.Team;
import com.example.jdagnogo.mytournament.model.Tournament;
import com.example.jdagnogo.mytournament.model.bundler.BinderTeamTextViewBundler;
import com.example.jdagnogo.mytournament.model.bundler.TournamentBundler;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import icepick.Icepick;
import icepick.State;


public class TournamentView extends LinearLayout {
    /*
    All Views
     */
    View rootView;
    LinearLayout mainLayout;
    //semi names
    TextView semiACard1Textview, semiACard2Textview,
            semiBCard1Textview, semiBCard2Textview,
            semiCCard1Textview, semiCCard2Textview,
            semiDCard1Textview, semiDCard2Textview;
    CardView semiACard1, semiACard2,
            semiBCard1, semiBCard2,
            semiCCard1, semiCCard2,
            semiDCard1, semiDCard2;
    View semiACard1Arrow1, semiACard1Arrow2,
            semiACard2Arrow1, semiACard2Arrow2,
            semiBCard1Arrow1, semiBCard1Arrow2,
            semiBCard2Arrow1, semiBCard2Arrow2,
            semiCCard1Arrow1, semiCCard1Arrow2,
            semiCCard2Arrow1, semiCCard2Arrow2,
            semiDCard1Arrow1, semiDCard1Arrow2,
            semiDCard2Arrow1, semiDCard2Arrow2;
    //semi score
    EditText semiACard1TextviewScore, semiACard2TextviewScore,
            semiBCard1TextviewScore, semiBCard2TextviewScore,
            semiCCard1TextviewScore, semiCCard2TextviewScore,
            semiDCard1TextviewScore, semiDCard2TextviewScore;
    //final names
    TextView finalCard1Textview, finalCard2Textview, finalCard3Textview, finalCard4Textview;
    CardView finalCard1, finalCard2, finalCard3, finalCard4, finalCard5, finalCard6;
    View winnerArrow0, winnerArrow1;
    TextView winnerTextView, winnerTextView2, winnerTextView3;
//    TextView winnerTextView2;
    View finalCard1Arrow1, finalCard1Arrow0,
            finalCard1Arrow2, finalCard2Arrow1,
            finalCard2Arrow0, finalCard2Arrow2,
            finalCard5Arrow2,finalCard5Arrow1,
            finalCard5Arrow3,finalCard7Arrow1;
    //final score
    EditText finalCard1TextviewScore, finalCard2TextviewScore,
            finalCard3TextviewScore, finalCard4TextviewScore,
            finalCard5TextviewScore, finalCard6TextviewScore;


    /*
    beans
     */
    @State(TournamentBundler.class)
    Tournament tournament;
    private List<TextView> semiTextViews;
    private List<CardView> semiCards;
    private List<TextView> finalTextViews;
    private List<CardView> finalCards;
    private List<TextView> semiTextViewsScore;
    private List<TextView> finalTextViewsScore;

    private List<TextView> semifinalTextViews;
    private List<CardView> semifinalCards;




//
//    private List<TextView> semiTextViews;
//    private List<CardView> semiCards;
//    private List<TextView> finalTextViews;
//    private List<CardView> finalCards;
//    private List<TextView> semiTextViewsScore;
//    private List<TextView> finalTextViewsScore;

    HashMap<Integer, BinderTeamTextView> binderSemi;
    HashMap<Integer, BinderTeamTextView> binderSemiFinal;

    HashMap<Integer, BinderTeamTextView> binderfinal;
    private Context context;

    public TournamentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setupLayout(context);
        initViews();
        updateElementsAccordingToAttributs(context, attrs);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return Icepick.saveInstanceState(this, super.onSaveInstanceState());
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(Icepick.restoreInstanceState(this, state));
        int toto = 0;
        startTournament();
    }


    private void initViews() {
        //init view
        mainLayout = (LinearLayout) rootView.findViewById(R.id.main);
        semiACard1Textview = (TextView) rootView.findViewById(R.id.semi_a_card_1_textview);
        semiACard2Textview = (TextView) rootView.findViewById(R.id.semi_a_card_2_textview);
        semiBCard1Textview = (TextView) rootView.findViewById(R.id.semi_b_card_1_textview);
        semiBCard2Textview = (TextView) rootView.findViewById(R.id.semi_b_card_2_textview);

        semiCCard1Textview = (TextView) rootView.findViewById(R.id.semi_c_card_1_textview);
        semiCCard2Textview = (TextView) rootView.findViewById(R.id.semi_c_card_2_textview);
        semiDCard1Textview = (TextView) rootView.findViewById(R.id.semi_d_card_1_textview);
        semiDCard2Textview = (TextView) rootView.findViewById(R.id.semi_d_card_2_textview);

        semiACard1Arrow1 = (View) rootView.findViewById(R.id.semi_a_card_1_arrow_1);
        semiACard1Arrow2 = (View) rootView.findViewById(R.id.semi_a_card_1_arrow_2);
        semiACard2Arrow1 = (View) rootView.findViewById(R.id.semi_a_card_2_arrow_1);
        semiACard2Arrow2 = (View) rootView.findViewById(R.id.semi_a_card_2_arrow_2);

        semiBCard1Arrow1 = (View) rootView.findViewById(R.id.semi_b_card_1_arrow_1);
        semiBCard1Arrow2 = (View) rootView.findViewById(R.id.semi_b_card_1_arrow_2);
        semiBCard2Arrow1 = (View) rootView.findViewById(R.id.semi_b_card_2_arrow_1);
        semiBCard2Arrow2 = (View) rootView.findViewById(R.id.semi_b_card_2_arrow_2);

        semiCCard1Arrow1 = (View) rootView.findViewById(R.id.semi_c_card_1_arrow_1);
        semiCCard1Arrow2 = (View) rootView.findViewById(R.id.semi_c_card_1_arrow_2);
        semiCCard2Arrow1 = (View) rootView.findViewById(R.id.semi_c_card_2_arrow_1);
        semiCCard2Arrow2 = (View) rootView.findViewById(R.id.semi_c_card_2_arrow_2);

        semiDCard1Arrow1 = (View) rootView.findViewById(R.id.semi_d_card_1_arrow_1);
        semiDCard1Arrow2 = (View) rootView.findViewById(R.id.semi_d_card_1_arrow_2);
        semiDCard2Arrow1 = (View) rootView.findViewById(R.id.semi_d_card_2_arrow_1);
        semiDCard2Arrow2 = (View) rootView.findViewById(R.id.semi_d_card_2_arrow_2);

        semiACard1 = (CardView) rootView.findViewById(R.id.semi_a_card_1);
        semiACard2 = (CardView) rootView.findViewById(R.id.semi_a_card_2);
        semiBCard1 = (CardView) rootView.findViewById(R.id.semi_b_card_1);
        semiBCard2 = (CardView) rootView.findViewById(R.id.semi_b_card_2);

        semiCCard1 = (CardView) rootView.findViewById(R.id.semi_c_card_1);
        semiCCard2 = (CardView) rootView.findViewById(R.id.semi_c_card_2);
        semiDCard1 = (CardView) rootView.findViewById(R.id.semi_d_card_1);
        semiDCard2 = (CardView) rootView.findViewById(R.id.semi_d_card_2);

        semiACard1TextviewScore = (EditText) rootView.findViewById(R.id.semi_a_card_1_textview_score);
        semiACard1TextviewScore.addTextChangedListener(watcherSemi1);
        semiACard2TextviewScore = (EditText) rootView.findViewById(R.id.semi_a_card_2_textview_score);
        semiACard2TextviewScore.addTextChangedListener(watcherSemi1);
        semiBCard1TextviewScore = (EditText) rootView.findViewById(R.id.semi_b_card_1_textview_score);
        semiBCard1TextviewScore.addTextChangedListener(watcherSemi2);
        semiBCard2TextviewScore = (EditText) rootView.findViewById(R.id.semi_b_card_2_textview_score);
        semiBCard2TextviewScore.addTextChangedListener(watcherSemi2);

        semiCCard1TextviewScore = (EditText) rootView.findViewById(R.id.semi_c_card_1_textview_score);
        semiCCard1TextviewScore.addTextChangedListener(watcherSemi3);
        semiCCard2TextviewScore = (EditText) rootView.findViewById(R.id.semi_c_card_2_textview_score);
        semiCCard2TextviewScore.addTextChangedListener(watcherSemi3);
        semiDCard1TextviewScore = (EditText) rootView.findViewById(R.id.semi_d_card_1_textview_score);
        semiDCard1TextviewScore.addTextChangedListener(watcherSemi4);
        semiDCard2TextviewScore = (EditText) rootView.findViewById(R.id.semi_d_card_2_textview_score);
        semiDCard2TextviewScore.addTextChangedListener(watcherSemi4);

        finalCard1Textview = (TextView) rootView.findViewById(R.id.final_card_1_textview);
        finalCard2Textview = (TextView) rootView.findViewById(R.id.final_card_2_textview);
        finalCard3Textview = (TextView) rootView.findViewById(R.id.final_card_3_textview);
        finalCard4Textview = (TextView) rootView.findViewById(R.id.final_card_4_textview);

        finalCard1Arrow0 = (View) rootView.findViewById(R.id.final_card_1_arrow_0);
        finalCard1Arrow1 = (View) rootView.findViewById(R.id.final_card_1_arrow_1);
        finalCard1Arrow2 = (View) rootView.findViewById(R.id.final_card_1_arrow_2);

        finalCard2Arrow0 = (View) rootView.findViewById(R.id.final_card_2_arrow_0);
        finalCard2Arrow1 = (View) rootView.findViewById(R.id.final_card_2_arrow_1);
        finalCard2Arrow2 = (View) rootView.findViewById(R.id.final_card_2_arrow_2);

        finalCard5Arrow1 = (View) rootView.findViewById(R.id.final_card_5_arrow_1);
        finalCard5Arrow2 = (View) rootView.findViewById(R.id.final_card_5_arrow_2);
        finalCard5Arrow3 = (View) rootView.findViewById(R.id.final_card_5_arrow_3);

        finalCard7Arrow1 = (View) rootView.findViewById(R.id.final_card_7_arrow_1);

        winnerArrow0 = (View) rootView.findViewById(R.id.winner_arrow_0);
        winnerArrow1 = (View) rootView.findViewById(R.id.winner_arrow_1);

        finalCard1 = (CardView) rootView.findViewById(R.id.final_card_1);
        finalCard2 = (CardView) rootView.findViewById(R.id.final_card_2);
        finalCard3 = (CardView) rootView.findViewById(R.id.final_card_3);
        finalCard4 = (CardView) rootView.findViewById(R.id.final_card_4);
        finalCard5 = (CardView) rootView.findViewById(R.id.final_card_5);
        finalCard6 = (CardView) rootView.findViewById(R.id.final_card_6);


        winnerTextView = (TextView) rootView.findViewById(R.id.winner_textview);
        winnerTextView2 = (TextView) rootView.findViewById(R.id.winner2_textview);
        winnerTextView3 = (TextView) rootView.findViewById(R.id.winner3_textview);

        finalCard1TextviewScore = (EditText) rootView.findViewById(R.id.final_card_1_textview_score);
        finalCard1TextviewScore.addTextChangedListener(watcherSemifinal1);
        finalCard2TextviewScore = (EditText) rootView.findViewById(R.id.final_card_2_textview_score);
        finalCard2TextviewScore.addTextChangedListener(watcherSemifinal1);

        finalCard3TextviewScore = (EditText) rootView.findViewById(R.id.final_card_3_textview_score);
        finalCard3TextviewScore.addTextChangedListener(watcherSemifinal2);
        finalCard4TextviewScore = (EditText) rootView.findViewById(R.id.final_card_4_textview_score);
        finalCard4TextviewScore.addTextChangedListener(watcherSemifinal2);

        finalCard5TextviewScore = (EditText) rootView.findViewById(R.id.final_card_5_textview_score);
        finalCard5TextviewScore.addTextChangedListener(watcherfinal);
        finalCard6TextviewScore = (EditText) rootView.findViewById(R.id.final_card_6_textview_score);
        finalCard6TextviewScore.addTextChangedListener(watcherfinal);


        // semi names
        semiTextViews = new ArrayList<>();
        semiTextViews.add(semiACard1Textview);
        semiTextViews.add(semiACard2Textview);
        semiTextViews.add(semiBCard1Textview);
        semiTextViews.add(semiBCard2Textview);

        semiTextViews.add(semiCCard1Textview);
        semiTextViews.add(semiCCard2Textview);
        semiTextViews.add(semiDCard1Textview);
        semiTextViews.add(semiDCard2Textview);

        // semi cards
        semiCards = new ArrayList<>();
        semiCards.add(semiACard1);
        semiCards.add(semiACard2);
        semiCards.add(semiBCard1);
        semiCards.add(semiBCard2);

        semiCards.add(semiCCard1);
        semiCards.add(semiCCard2);
        semiCards.add(semiDCard1);
        semiCards.add(semiDCard2);


        // semifinal names
        semifinalTextViews = new ArrayList<>();
        semifinalTextViews.add(finalCard1Textview);
        semifinalTextViews.add(finalCard2Textview);

        semifinalTextViews.add(finalCard3Textview);
        semifinalTextViews.add(finalCard4Textview);

        // semifinal cards
        semifinalCards = new ArrayList<>();
        semifinalCards.add(finalCard1);
        semifinalCards.add(finalCard2);

        semifinalCards.add(finalCard3);
        semifinalCards.add(finalCard4);

        // final names
        finalTextViews = new ArrayList<>();
        finalTextViews.add(winnerTextView);
        finalTextViews.add(winnerTextView2);

        // final cards
        finalCards = new ArrayList<>();
        finalCards.add(finalCard5);
        finalCards.add(finalCard6);

        // semi scnore
        semiTextViewsScore = new ArrayList<>();
        semiTextViewsScore.add(semiACard1TextviewScore);
        semiTextViewsScore.add(semiACard2TextviewScore);
        semiTextViewsScore.add(semiBCard1TextviewScore);
        semiTextViewsScore.add(semiBCard2TextviewScore);

        semiTextViewsScore.add(semiCCard1TextviewScore);
        semiTextViewsScore.add(semiCCard2TextviewScore);
        semiTextViewsScore.add(semiDCard1TextviewScore);
        semiTextViewsScore.add(semiDCard2TextviewScore);

        // semifinal scnore
        finalTextViewsScore = new ArrayList<>();
        finalTextViewsScore.add(finalCard1TextviewScore);
        finalTextViewsScore.add(finalCard2TextviewScore);

        finalTextViewsScore.add(finalCard3TextviewScore);
        finalTextViewsScore.add(finalCard4TextviewScore);

        // final scnore
        finalTextViewsScore = new ArrayList<>();
        finalTextViewsScore.add(finalCard5TextviewScore);
        finalTextViewsScore.add(finalCard6TextviewScore);



    }

    public void startTournament() {

        binderSemi = new HashMap<>();
        binderSemiFinal= new HashMap<>();
        binderfinal = new HashMap<>();
        if (null != tournament) {
            // init binder team <=> textviews semi final and set team name's
            for (int i = 0; i < tournament.getTeams().size(); i++) {
                BinderTeamTextView binder = new BinderTeamTextView(tournament.getTeams().get(i),
                        semiTextViews.get(i), semiTextViewsScore.get(i),
                        semiCards.get(i));
                // set teams names
                semiTextViews.get(i).setText(tournament.getTeams().get(i).getName());
                binderSemi.put(i, binder);
            }

        }
//        if (tournament.getTeams().size()<7){
//                semiCCard1.removeAllViews();
//        }
    }


    private void updateElementsAccordingToAttributs(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.TournamentView, 0, 0);


        // Attributs
        int mainColor = typedArray.getColor(R.styleable.TournamentView_main_bg_color,
                context.getResources().getColor(R.color.cardview_shadow_start_color));
        mainLayout.setBackgroundColor(mainColor);

    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    private void setColorsBgForLoserAndWinner(BinderTeamTextView winner, BinderTeamTextView loser) {
        winner.getCardView().setBackground(getResources().getDrawable(R.drawable.winner_green_bg));
        winner.getTextViewName().setTypeface(null, Typeface.BOLD);


        loser.getTextViewName().setTypeface(null, Typeface.NORMAL);
        loser.getCardView().setBackground(getResources().getDrawable(R.drawable.loser_red_bg));
    }

    private Team updateScoreandGetWinner(BinderTeamTextView semi1, BinderTeamTextView semi2, TournamentRound tournamentRound) {
        if (null != semi1 && null != semi2) {
            if (!semi1.getTextViewScore().getText().toString().isEmpty() && !semi2.getTextViewScore().getText().toString().isEmpty() && !semi1.getTextViewScore().getText().toString().isEmpty() && !semi2.getTextViewScore().getText().toString().isEmpty()) {
                Match match = new Match.MatchResultBuilder(semi1.getTeam(), semi2.getTeam())
                        .scoreA(Integer.valueOf(semi1.getTextViewScore().getText().toString()))
                        .scoreB(Integer.valueOf(semi2.getTextViewScore().getText().toString()))
                        .currentPosition(tournamentRound)
                        .build();
                Team winner = match.getWinner();
                tournament.addMatch(match);
                if (null != winner) {
                    if (winner.equals(semi1.getTeam())) {
                        setColorsBgForLoserAndWinner(semi1, semi2);
                    }
                    else {
                        setColorsBgForLoserAndWinner(semi2, semi1);
                    }
                    return winner;
                }
            }
        }
        return null;
    }

    private TextWatcher watcherSemi1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (null != binderSemi) {
                Team team = updateScoreandGetWinner(binderSemi.get(0), binderSemi.get(1),TournamentRound.SemiA);
                if (null != team) {
                    BinderTeamTextView binderTeamTextView = new BinderTeamTextView(team, finalCard1Textview,
                            finalCard1TextviewScore, finalCard1);
                    binderSemiFinal.put(0, binderTeamTextView);
                    finalCard1Textview.setText(team.getName());
                    CustomAnim.NextRoundAnim(finalCard1);
                }
                updateSemiFinal();
            }

        }
    };
    private TextWatcher watcherSemi2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (null != binderSemi) {
                Team team = updateScoreandGetWinner(binderSemi.get(2),binderSemi.get(3), TournamentRound.SemiB);
                if (null != team) {
                    BinderTeamTextView binderTeamTextView = new BinderTeamTextView(team,
                            finalCard2Textview, finalCard2TextviewScore, finalCard2);
                    binderSemiFinal.put(1, binderTeamTextView);
                    finalCard2Textview.setText(team.getName());
                    CustomAnim.NextRoundAnim(finalCard2);
                }
                updateSemiFinal();
            }
        }
    };

//     // menambahkan match 5-8 harus ditambah textwatcher
    private TextWatcher watcherSemi3 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (null != binderSemi) {
                Team team = updateScoreandGetWinner(binderSemi.get(4),binderSemi.get(5), TournamentRound.SemiA);
                if (null != team) {
                    BinderTeamTextView binderTeamTextView = new BinderTeamTextView(team,
                            finalCard3Textview, finalCard3TextviewScore, finalCard3);
                    binderSemiFinal.put(2, binderTeamTextView);
                    finalCard3Textview.setText(team.getName());
                    CustomAnim.NextRoundAnim(finalCard3);
                }
                updateSemiFinal();
            }
        }
    };

    private TextWatcher watcherSemi4 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (null != binderSemi) {
                Team team = updateScoreandGetWinner(binderSemi.get(6),binderSemi.get(7), TournamentRound.SemiB);
                if (null != team) {
                    BinderTeamTextView binderTeamTextView = new BinderTeamTextView(team,
                            finalCard4Textview, finalCard4TextviewScore, finalCard4);
                    binderSemiFinal.put(3, binderTeamTextView);
                    finalCard4Textview.setText(team.getName());
                    CustomAnim.NextRoundAnim(finalCard4);
                }
                updateSemiFinal();
            }
        }
    };
//asasa
    private TextWatcher watcherSemifinal1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (null != binderSemiFinal) {
                Team team = updateScoreandGetWinner(binderSemiFinal.get(0),binderSemiFinal.get(1), TournamentRound.SemiC);
                if (null != team) {
                    BinderTeamTextView binderTeamTextView = new BinderTeamTextView(team,
                            winnerTextView, finalCard5TextviewScore, finalCard5);
                    binderfinal.put(0, binderTeamTextView);
                    winnerTextView.setText(team.getName());
                    CustomAnim.NextRoundAnim(finalCard5);
                }
                updateFinal();
            }
        }
    };

    private TextWatcher watcherSemifinal2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (null != binderSemiFinal) {
                Team team = updateScoreandGetWinner(binderSemiFinal.get(2),binderSemiFinal.get(3), TournamentRound.SemiC);
                if (null != team) {
                    BinderTeamTextView binderTeamTextView = new BinderTeamTextView(team,
                            winnerTextView2, finalCard6TextviewScore, finalCard6);
                    binderfinal.put(1, binderTeamTextView);
                    winnerTextView2.setText(team.getName());
                    CustomAnim.NextRoundAnim(finalCard6);
                }
                updateFinal();
            }
        }
    };

    private void updateSemiFinal() {
        if (null != binderSemiFinal) {
            Team team = updateScoreandGetWinner(binderSemiFinal.get(0), binderSemiFinal.get(1), TournamentRound.SemiFinal);
            Team team1 = updateScoreandGetWinner(binderSemiFinal.get(2), binderSemiFinal.get(3), TournamentRound.SemiFinal);
            if (null != team) {
                winnerTextView.setText(team.getName());
                winnerTextView.setBackgroundColor(context.getResources().getColor(R.color.winner));
                CustomAnim.NextRoundAnim(winnerTextView);

//                // klo dijadikan 1 nanti harus keisi semua dulu baru jalan
//                winnerTextView1.setText(team1.getName());
//                winnerTextView1.setBackgroundColor(context.getResources().getColor(R.color.winner));
//                CustomAnim.NextRoundAnim(winnerTextView1);

            }

//            if (null != team && null!=team1) {
//                winnerTextView.setText(team.getName());
//                winnerTextView.setBackgroundColor(context.getResources().getColor(R.color.winner));
//                CustomAnim.NextRoundAnim(winnerTextView);
//
//                // klo dijadikan 1 nanti harus keisi semua dulu baru jalan
//                winnerTextView1.setText(team1.getName());
//                winnerTextView1.setBackgroundColor(context.getResources().getColor(R.color.winner));
//                CustomAnim.NextRoundAnim(winnerTextView1);
//
//            }
            if (null!=team1)
            {
                winnerTextView2.setText(team1.getName());
                winnerTextView2.setBackgroundColor(context.getResources().getColor(R.color.winner));
                CustomAnim.NextRoundAnim(winnerTextView2);

            }

        }
    }

    private void updateFinal() {
        if (null != binderfinal) {
            Team team = updateScoreandGetWinner(binderfinal.get(0), binderfinal.get(1), TournamentRound.Final);
            if (null != team) {
                winnerTextView3.setText(team.getName());
                winnerTextView3.setBackgroundColor(context.getResources().getColor(R.color.winner));
                CustomAnim.NextRoundAnim(winnerTextView3);

//                // klo dijadikan 1 nanti harus keisi semua dulu baru jalan
//                winnerTextView1.setText(team1.getName());
//                winnerTextView1.setBackgroundColor(context.getResources().getColor(R.color.winner));
//                CustomAnim.NextRoundAnim(winnerTextView1);

            }

//            if (null != team && null!=team1) {
//                winnerTextView.setText(team.getName());
//                winnerTextView.setBackgroundColor(context.getResources().getColor(R.color.winner));
//                CustomAnim.NextRoundAnim(winnerTextView);
//
//                // klo dijadikan 1 nanti harus keisi semua dulu baru jalan
//                winnerTextView1.setText(team1.getName());
//                winnerTextView1.setBackgroundColor(context.getResources().getColor(R.color.winner));
//                CustomAnim.NextRoundAnim(winnerTextView1);
//
//            }


        }
    }


    private TextWatcher watcherfinal = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            updateFinal();
        }
    };

    public HashMap<TournamentRound, Match> tournamentResult() {
        return tournament.tournamentResult();
    }

    public void simulate() {
        Match semiA = tournament.getSemiAMatch();
        semiACard1TextviewScore.setText(String.valueOf(semiA.getScoreA()));
        semiACard2TextviewScore.setText(String.valueOf(semiA.getScoreB()));

        Match semiB = tournament.getSemiBMatch();
        semiBCard1TextviewScore.setText(String.valueOf(semiB.getScoreA()));
        semiBCard2TextviewScore.setText(String.valueOf(semiB.getScoreB()));

        Match finalMatch = tournament.getFinalMatch();
        finalCard1TextviewScore.setText(String.valueOf(finalMatch.getScoreA()));
        finalCard2TextviewScore.setText(String.valueOf(finalMatch.getScoreB()));
    }

    public void setupLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rootView = inflater.inflate(R.layout.main_layout, this);

    }
}
