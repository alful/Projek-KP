package com.ventura.emilp.tournamentbrackets.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ventura.emilp.tournamentbrackets.Peserta;
import com.ventura.emilp.tournamentbrackets.R;
import com.ventura.emilp.tournamentbrackets.adapter.BracketsSectionAdapter;
import com.ventura.emilp.tournamentbrackets.customviews.WrapContentHeightViewPager;
import com.ventura.emilp.tournamentbrackets.model.ColomnData;
import com.ventura.emilp.tournamentbrackets.model.CompetitorData;
import com.ventura.emilp.tournamentbrackets.model.MatchData;
import com.ventura.emilp.tournamentbrackets.utility.BracketsUtility;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Emil on 21/10/17.
 */

public class BracketsFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private WrapContentHeightViewPager viewPager;
    private BracketsSectionAdapter sectionAdapter;
    private ArrayList<ColomnData> sectionList;
    private int mNextSelectedScreen;
    private int mCurrentPagerState;

    DatabaseReference databaseReference;
    ArrayList<String> ALNama;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_brackts, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        setData();
        intialiseViewPagerAdapter();
    }

    private void setData() {
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Peserta");
//
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ALNama = new ArrayList<String>();
//                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    String nama = dataSnapshot.child("nama").getValue(String.class);
//
//                    ALNama.add(nama);
//                }
//
//                Log.d("TAG", "setData: "+ALNama);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        ALNama = new ArrayList<String>();
        ALNama.add("Arthao");
        ALNama.add("Ace");
        ALNama.add("Luffy");
        ALNama.add("Sabo");
        ALNama.add("Mahes");
        ALNama.add("Roger");
        ALNama.add("Teach");
        ALNama.add("Zoro");
        ALNama.add("");

//        Intent i = getActivity().getIntent();
//        String na = i.getStringExtra("nama1");
        Peserta peserta=new Peserta();
        Log.d("TAG", "setData: "+peserta.getSNama());

        sectionList = new ArrayList<>();
        ArrayList<MatchData> Colomn1matchesList = new ArrayList<>();
        ArrayList<MatchData> colomn2MatchesList = new ArrayList<>();
        ArrayList<MatchData> colomn3MatchesList = new ArrayList<>();
        CompetitorData competitorOne = new CompetitorData(ALNama.get(0), "2");
        CompetitorData competitorTwo = new CompetitorData(ALNama.get(1), "1");
        CompetitorData competitorThree = new CompetitorData(ALNama.get(2), "2");
        CompetitorData competitorFour = new CompetitorData(ALNama.get(3), "1");
        CompetitorData competitorFive = new CompetitorData(ALNama.get(4), "2");
        CompetitorData competitorSix = new CompetitorData(ALNama.get(5), "4");
        CompetitorData competitorSeven = new CompetitorData(ALNama.get(6), "2");
        CompetitorData competitorEight = new CompetitorData(ALNama.get(7), "1");
        MatchData matchData1 = new MatchData(competitorOne,competitorTwo);
        MatchData matchData2 = new MatchData(competitorThree, competitorFour);
        MatchData matchData3 = new MatchData(competitorFive,competitorSix);
        MatchData matchData4 = new MatchData(competitorSeven, competitorEight);
        Colomn1matchesList.add(matchData1);
        Colomn1matchesList.add(matchData2);
        Colomn1matchesList.add(matchData3);
        Colomn1matchesList.add(matchData4);
        ColomnData colomnData1 = new ColomnData(Colomn1matchesList);
        sectionList.add(colomnData1);
        CompetitorData competitorNine = new CompetitorData(ALNama.get(8), "2");
        CompetitorData competitorTen = new CompetitorData(ALNama.get(8), "4");
        CompetitorData competitorEleven = new CompetitorData(ALNama.get(8), "2");
        CompetitorData competitorTwelve = new CompetitorData(ALNama.get(8), "1");
        MatchData matchData5 = new MatchData(competitorNine,competitorTen);
        MatchData matchData6 = new MatchData(competitorEleven, competitorTwelve);
        colomn2MatchesList.add(matchData5);
        colomn2MatchesList.add(matchData6);
        ColomnData colomnData2 = new ColomnData(colomn2MatchesList);
        sectionList.add(colomnData2);
        CompetitorData competitorThirteen = new CompetitorData(ALNama.get(8), "2");
        CompetitorData competitorForteen = new CompetitorData(ALNama.get(8), "1");
        MatchData matchData7 = new MatchData(competitorThirteen, competitorForteen);
        colomn3MatchesList.add(matchData7);
        ColomnData colomnData3 = new ColomnData(colomn3MatchesList);
        sectionList.add(colomnData3);

    }

    private void intialiseViewPagerAdapter() {

        sectionAdapter = new BracketsSectionAdapter(getChildFragmentManager(),this.sectionList);
        viewPager.setOffscreenPageLimit(10);
        viewPager.setAdapter(sectionAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setPageMargin(-200);
        viewPager.setHorizontalFadingEdgeEnabled(true);
        viewPager.setFadingEdgeLength(50);

        viewPager.addOnPageChangeListener(this);
    }

    private void initViews() {

        viewPager = (WrapContentHeightViewPager) getView().findViewById(R.id.container);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (mCurrentPagerState != ViewPager.SCROLL_STATE_SETTLING) {
            // We are moving to next screen on right side
            if (positionOffset > 0.5) {
                // Closer to next screen than to current
                if (position + 1 != mNextSelectedScreen) {
                    mNextSelectedScreen = position + 1;
                    //update view here
                    if (getBracketsFragment(position).getColomnList().get(0).getHeight()
                            != BracketsUtility.dpToPx(131))
                        getBracketsFragment(position).shrinkView(BracketsUtility.dpToPx(131));
                    if (getBracketsFragment(position + 1).getColomnList().get(0).getHeight()
                            != BracketsUtility.dpToPx(131))
                        getBracketsFragment(position + 1).shrinkView(BracketsUtility.dpToPx(131));
                }
            } else {
                // Closer to current screen than to next
                if (position != mNextSelectedScreen) {
                    mNextSelectedScreen = position;
                    //updateViewhere

                    if (getBracketsFragment(position + 1).getCurrentBracketSize() ==
                            getBracketsFragment(position + 1).getPreviousBracketSize()) {
                        getBracketsFragment(position + 1).shrinkView(BracketsUtility.dpToPx(131));
                        getBracketsFragment(position).shrinkView(BracketsUtility.dpToPx(131));
                    } else {
                        int currentFragmentSize = getBracketsFragment(position + 1).getCurrentBracketSize();
                        int previousFragmentSize = getBracketsFragment(position + 1).getPreviousBracketSize();
                        if (currentFragmentSize != previousFragmentSize) {
                            getBracketsFragment(position + 1).expandHeight(BracketsUtility.dpToPx(262));
                            getBracketsFragment(position).shrinkView(BracketsUtility.dpToPx(131));
                        }
                    }
                }
            }
        } else {
            // We are moving to next screen left side
            if (positionOffset > 0.5) {
                // Closer to current screen than to next
                if (position + 1 != mNextSelectedScreen) {
                    mNextSelectedScreen = position + 1;
                    //update view for screen

                }
            } else {
                // Closer to next screen than to current
                if (position != mNextSelectedScreen) {
                    mNextSelectedScreen = position;
                    //updateviewfor screem
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public BracketsColomnFragment getBracketsFragment(int position) {
        BracketsColomnFragment bracktsFrgmnt = null;
        if (getChildFragmentManager() != null) {
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    if (fragment instanceof BracketsColomnFragment) {
                        bracktsFrgmnt = (BracketsColomnFragment) fragment;
                        if (bracktsFrgmnt.getSectionNumber() == position)
                            break;
                    }
                }
            }
        }
        return bracktsFrgmnt;
    }
}
