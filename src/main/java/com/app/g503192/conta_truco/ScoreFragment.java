package com.app.g503192.conta_truco;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import static com.app.g503192.conta_truco.R.id.Team1;
import static com.app.g503192.conta_truco.R.id.Team2;
import static com.app.g503192.conta_truco.R.id.adView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {

    SettingsOptions mCallback;

    private int mPlacarUm = 0;
    private int mPlacarDois = 0;
    private int mVitoriasUm = 0;
    private int mVitoriasDois = 0;
    private int rodada = 1;
    private int mMaxRounOpt = 0;
    private int mRoundIndex = 0;
    private String mTeam1 = "Nós";
    private String mTeam2 = "Eles";
    private static ScoreFragment ins;
    private AdView mAdView;

    ArrayList<Integer> RoundHistoryA = new ArrayList<Integer>() {{add(mPlacarUm);}};
    ArrayList<Integer> RoundHistoryB = new ArrayList<Integer>() {{add(mPlacarDois);}};
    ArrayList<Rodada> round = new ArrayList<Rodada>();
    ArrayList<String> roundListed = new ArrayList<String>() {{
        add("(" + mRoundIndex  + ")    " + mTeam1 + ": " + mPlacarUm + "  -  " + mPlacarDois  + " :" + mTeam2);}};

    public ScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null){
            mPlacarUm = savedInstanceState.getInt("mPlacarUm");
            mPlacarDois = savedInstanceState.getInt("mPlacarDois");
            mVitoriasUm = savedInstanceState.getInt("mVitoriasUm");
            mVitoriasDois = savedInstanceState.getInt("mVitoriasDois");
            rodada = savedInstanceState.getInt("rodada");
            mMaxRounOpt = savedInstanceState.getInt("mMaxRounOpt");
            mRoundIndex = savedInstanceState.getInt("mRoundIndex");
            mTeam1 = savedInstanceState.getString("mTeam1");
            mTeam2 = savedInstanceState.getString("mTeam2");
            RoundHistoryA = savedInstanceState.getIntegerArrayList("RoundHistoryA");
            RoundHistoryB = savedInstanceState.getIntegerArrayList("RoundHistoryB");
            round = savedInstanceState.getParcelableArrayList("round");
            roundListed = savedInstanceState.getStringArrayList("roundListed");

        }

        ins = this;
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.score_fragment, container, false);

        // Instantiate all buttons
        final Button button_um1 = (Button) rootView.findViewById(R.id.um1);
        final Button button_um2 = (Button) rootView.findViewById(R.id.um2);
        final Button button_tres1 = (Button) rootView.findViewById(R.id.tres1);
        final Button button_tres2 = (Button) rootView.findViewById(R.id.tres2);
        final Button button_seis1 = (Button) rootView.findViewById(R.id.seis1);
        final Button button_seis2 = (Button) rootView.findViewById(R.id.seis2);
        final Button button_nove1 = (Button) rootView.findViewById(R.id.nove1);
        final Button button_nove2 = (Button) rootView.findViewById(R.id.nove2);
        final Button button_doze1 = (Button) rootView.findViewById(R.id.doze1);
        final Button button_doze2 = (Button) rootView.findViewById(R.id.doze2);

        // Instantiate Score and Victories Views
        final TextView placar_Um = (TextView) rootView.findViewById(R.id.placar1);
        final TextView placar_Dois = (TextView) rootView.findViewById(R.id.placar2);
        final TextView team1Name = (TextView) rootView.findViewById(Team1);
        final TextView team2Name = (TextView) rootView.findViewById(Team2);
        final TextView victoryTxtView1 = (TextView) rootView.findViewById(R.id.vitorias1);
        final TextView victoryTxtView2 = (TextView) rootView.findViewById(R.id.vitorias2);

        // Instantiate Animations for Score and Victories Views
        final Animation anim1 = AnimationUtils.loadAnimation(getContext(), R.anim.slide_on_top);
        final Animation anim2 = AnimationUtils.loadAnimation(getContext(), R.anim.slide_on_top);
//        final Animation anim3 = AnimationUtils.loadAnimation(getContext(), R.anim.team_names);
//        final Animation anim4 = AnimationUtils.loadAnimation(getContext(), R.anim.team_names);
        final Animation animbutton = AnimationUtils.loadAnimation(getContext(), R.anim.anim_button);

        placar_Um.setText("" + mPlacarUm);
        placar_Dois.setText("" + mPlacarDois);

        victoryTxtView1.setText("" + mVitoriasUm);
        victoryTxtView2.setText("" + mVitoriasDois);

        if (((SettingsOptions) getActivity()).getNameTeam1() != null){
            refreshLogFragment();
        }
        else {
            team1Name.setText(mTeam1);
            //Animation on txt team names if they are too long
            team1Name.setSelected(true);
            team1Name.setMarqueeRepeatLimit(-1);
        }


        if (((SettingsOptions) getActivity()).getNameTeam2() != null){
            refreshLogFragment();
        }
        else {
            team2Name.setText(mTeam2);
            //Animation on txt team names if they are too long
            team2Name.setSelected(true);
            team2Name.setMarqueeRepeatLimit(-1);
        }


        team1Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Edite os nomes das equipes na aba Opções", Toast.LENGTH_SHORT).show();
            }
        });

        team2Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Edite os nomes das equipes na aba Opções", Toast.LENGTH_SHORT).show();
            }
        });


        //Listener for all buttons that increases points
        button_um1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlacarUm = mPlacarUm + 1;
                placar_Um.setText("" + checkScore(mPlacarUm));
                placar_Um.startAnimation(anim1);
                button_um1.startAnimation(animbutton);
                updatePoints(mPlacarUm, mPlacarDois);
            }
        });

        button_tres1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlacarUm = mPlacarUm + 3;
                placar_Um.setText("" + checkScore(mPlacarUm));
                placar_Um.startAnimation(anim1);
                button_tres1.startAnimation(animbutton);
                updatePoints(mPlacarUm, mPlacarDois);
            }
        });

        button_seis1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlacarUm = mPlacarUm + 6;
                placar_Um.setText("" + checkScore(mPlacarUm));
                placar_Um.startAnimation(anim1);
                button_seis1.startAnimation(animbutton);
                updatePoints(mPlacarUm, mPlacarDois);

            }
        });

        button_nove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlacarUm = mPlacarUm + 9;
                placar_Um.setText("" + checkScore(mPlacarUm));
                placar_Um.startAnimation(anim1);
                button_nove1.startAnimation(animbutton);
                updatePoints(mPlacarUm, mPlacarDois);
            }
        });

        button_doze1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlacarUm = mPlacarUm + 12;
                placar_Um.setText("" + checkScore(mPlacarUm));
                button_doze1.startAnimation(animbutton);
                updatePoints(mPlacarUm, mPlacarDois);

            }
        });

        button_um2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlacarDois = mPlacarDois + 1;
                placar_Dois.setText("" + checkScore(mPlacarDois));
                placar_Dois.startAnimation(anim2);
                button_um2.startAnimation(animbutton);
                updatePoints(mPlacarUm, mPlacarDois);

            }
        });

        button_tres2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlacarDois = mPlacarDois + 3;
                placar_Dois.setText("" + checkScore(mPlacarDois));
                placar_Dois.startAnimation(anim2);
                button_tres2.startAnimation(animbutton);
                updatePoints(mPlacarUm, mPlacarDois);
            }
        });

        button_seis2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlacarDois = mPlacarDois + 6;
                placar_Dois.setText("" + checkScore(mPlacarDois));
                placar_Dois.startAnimation(anim2);
                button_seis2.startAnimation(animbutton);
                updatePoints(mPlacarUm, mPlacarDois);
            }
        });

        button_nove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlacarDois = mPlacarDois + 9;
                placar_Dois.setText("" + checkScore(mPlacarDois));
                placar_Dois.startAnimation(anim2);
                button_nove2.startAnimation(animbutton);
                updatePoints(mPlacarUm, mPlacarDois);
            }
        });

        button_doze2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlacarDois = mPlacarDois + 12;
                placar_Dois.setText("" + checkScore(mPlacarDois));
                placar_Dois.startAnimation(anim2);
                button_doze2.startAnimation(animbutton);
                updatePoints(mPlacarUm, mPlacarDois);
            }
        });


        /*Bottom Navigation Menu: Nova Rodada, Voltar Mão, Novo Jogo
        reset_round: restart Score
        last_round: remove last item in the arraylist and update score with new last item
        reset_game: restart Score, victories and clear Score array list (history)
         */
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                rootView.findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {



                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



//                        item.getActionView().setLongClickable(true);
//                        item.getActionView().setOnLongClickListener(new View.OnLongClickListener() {
//                            @Override
//                            public boolean onLongClick(View v) {
//
//                                Toast.makeText(getActivity(), "log press ok", Toast.LENGTH_SHORT).show();
//
//                                return false;
//                            }
//                        });

                        switch (item.getItemId()) {
                            case R.id.reset_round: //consultar jogadas



                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);


                                builder.setTitle("Registro das Jogadas");
                                builder.setItems(roundListed.toArray(new String[roundListed.size()]), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int item) {



                                        //String selectedText = roundListed[item].toString();  //Selected item in listview
                                    }
                                });
                                //Create alert dialog object via builder

                                AlertDialog alertDialogObject = builder.create();
                                //Show the dialog
                                alertDialogObject.show();


                                //     roundListed.toArray(new String[roundListed.size()]


                                break;

                            case R.id.last_round:

                                if (mRoundIndex == 0){

                                    Toast.makeText(getActivity(), "Não é possível voltar mais jogadas", Toast.LENGTH_SHORT).show();

                                }
                                else {


                                    final CheckBox checkBox = new CheckBox(getContext());

                                    checkBox.setText("Marque para zerar placar.\nNão impacta no número de vitórias.");
                                    checkBox.setChecked(false);

                                final AlertDialog.Builder voltarRodadaDialog = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
                                voltarRodadaDialog
                                        .setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            //Positive
                                                if (mRoundIndex > 0) {

                                                    mRoundIndex--;

                                                    for (int i = RoundHistoryA.size() - 1; i > mRoundIndex; i--) {

                                                        RoundHistoryA.remove(i);
                                                        RoundHistoryB.remove(i);
                                                        roundListed.remove(i);

                                                        mPlacarUm = RoundHistoryA.get((RoundHistoryA.size()-1));
                                                        mPlacarDois = RoundHistoryB.get((RoundHistoryA.size()-1));
                                                    }
                                                }
                                                else {
                                                    mPlacarUm = 0;
                                                    mPlacarDois = 0;

                                                    RoundHistoryA.clear();
                                                    RoundHistoryB.clear();
                                                    roundListed.clear();

                                                    RoundHistoryA.add(mPlacarUm);
                                                    RoundHistoryB.add(mPlacarDois);

                                                    checkNullTeamNameonList(mRoundIndex, mTeam1, mPlacarUm, mPlacarDois, mTeam2);

                                                }

                                                Log.i("ROUNDINDEX", "" + mRoundIndex);


                                                if (checkBox.isChecked()){
                                                    //reset round
                                                    mPlacarUm = 0;
                                                    mPlacarDois = 0;
                                                    mRoundIndex = 0;
                                                    RoundHistoryA.clear();
                                                    RoundHistoryB.clear();
                                                    roundListed.clear();
                                                    placar_Um.setText("" + mPlacarUm);
                                                    placar_Dois.setText("" + mPlacarDois);
                                                    placar_Um.startAnimation(anim1);
                                                    placar_Dois.startAnimation(anim2);

                                                    checkNullTeamNameonList(rodada, mTeam1, mPlacarUm, mPlacarDois, mTeam2);

                                                    RoundHistoryA.add(mPlacarUm);
                                                    RoundHistoryB.add(mPlacarDois);

                                                    Toast.makeText(getContext(), "Nova Rodada Iniciada",Toast.LENGTH_SHORT).show();
                                                }



                                                placar_Um.setText("" + RoundHistoryA.get(mRoundIndex));
                                                placar_Dois.setText("" + RoundHistoryB.get(mRoundIndex));





                                            }

                                        })
                                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();


                                            }
                                        });
                                    voltarRodadaDialog.setView(checkBox);
                                AlertDialog alert = voltarRodadaDialog.create();
                                alert.setTitle("Desfazer Última Jogada?");
                                alert.show();


                                }

                                break;

                            case R.id.reset_game:
                                AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
                                a_builder.setMessage("Iniciar Novo Jogo?")
                                        .setCancelable(false)
                                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                newGame();

                                                placar_Um.setText("" + mPlacarUm);
                                                placar_Dois.setText("" + mPlacarDois);
                                                placar_Um.startAnimation(anim1);
                                                placar_Dois.startAnimation(anim2);

                                            }
                                        })
                                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();


                                            }
                                        });
                                AlertDialog alert2 = a_builder.create();
                                //alert.setTitle("Iniciar Novo Jogo?");
                                alert2.show();

                                break;
                        }
                        return false;
                    }
                });



        //ADS
        // Place correct AD unit IDs for debug and realease versions

        //        myScoreFragmentBannerId: ca-app-pub-4711925247199151/7758793848
        //        testBannerId: ca-app-pub-3940256099942544/6300978111


        mAdView = new AdView(getActivity());

        if (BuildConfig.DEBUG) {
            // test ads for a debug build
            mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
            mAdView.setAdSize(AdSize.SMART_BANNER);

            AdRequest request = new AdRequest.Builder()
                    .addTestDevice("33BE2250B43518CCDA7DE426D04EE232")
                    .build();

            if(mAdView.getAdSize() != null || mAdView.getAdUnitId() != null)
                mAdView.loadAd(request);
            // else Log state of adsize/adunit
            ((LinearLayout) rootView.findViewById(R.id.adView)).addView(mAdView);

        }
        else {
            // real ads for a release build
            mAdView.setAdUnitId("ca-app-pub-4711925247199151/7758793848");
            mAdView.setAdSize(AdSize.SMART_BANNER);

            AdRequest request = new AdRequest.Builder().build();

            if(mAdView.getAdSize() != null || mAdView.getAdUnitId() != null)
                mAdView.loadAd(request);
            // else Log state of adsize/adunit
            ((LinearLayout) rootView.findViewById(R.id.adView)).addView(mAdView);
        }




        return rootView;

    }



    /* Management when going back from pop-up.
        It is needed to instantiate the textView fields inside the main content layout.
        this funcion gets instance from MainActivity OnCreate in which content main layout is loaded
        This way it is possible to take actions and update this layout after selecting any options of AlertDialog (end of match pop-up)
     */
    public static ScoreFragment getInstace() {
        return ins;
    }

    public void updateTheTextView() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                TextView textV1 = (TextView) getActivity().findViewById(R.id.placar1);
                TextView textV2 = (TextView) getActivity().findViewById(R.id.placar2);
                TextView textV3 = (TextView) getActivity().findViewById(R.id.vitorias1);
                TextView textV4 = (TextView) getActivity().findViewById(R.id.vitorias2);


                textV1.setText("" + mPlacarUm);
                textV2.setText("" + mPlacarDois);
                textV3.setText("" + mVitoriasUm);
                textV4.setText("" + mVitoriasDois);


            }
        });
    }


    /* check Score
        limits max Score of the match
        if it goes over 12, this method adjust max value to 12
     */
    private int checkScore(int points) {
        if (points < 12) {
            return points;
        } else {
            points = 12;

    /* Show AlerDialog Pop-up if any team reaches min of 12 points
        Possible results:
            YES: Increase victory counting and update views
                 reset Score and update views
                 clear ArrayList of round history
                 Increase round numer
            NO:  decrase Score to last hand before
                 remove last round from ArrayList
                 update Views
     */
            AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
            a_builder.setMessage("\nSIM: Nova rodada \n\nNÃO: Voltar jogada")
                    .setCancelable(false)
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mPlacarUm >= 12) {
                                mVitoriasUm++;
                            }
                            if (mPlacarDois >= 12) {
                                mVitoriasDois++;
                            }


                            if ((mTeam1 == null)||(mTeam2 == null)){
                                mTeam1 = "Nós";
                                mTeam2 = "Eles";
                            }

                            round.add(new Rodada(rodada, mPlacarUm, mPlacarDois, mVitoriasUm, mVitoriasDois, mTeam1, mTeam2));
                            ((SettingsOptions) getActivity()).sendRounds(rodada, mPlacarUm, mPlacarDois, mVitoriasUm, mVitoriasDois, mTeam1, mTeam2);

                            updateList();

                            RoundHistoryA.clear();
                            RoundHistoryB.clear();
                            roundListed.clear();
                            mRoundIndex = 0;

                            //Fim da partida. Reset.
                            mPlacarUm = 0;
                            mPlacarDois = 0;

                            try {
                                ScoreFragment.getInstace().updateTheTextView();
                            } catch (Exception e) {

                            }

                            RoundHistoryA.add(mPlacarUm);
                            RoundHistoryB.add(mPlacarDois);

                            checkNullTeamNameonList(mRoundIndex, mTeam1, mPlacarUm, mPlacarDois, mTeam2);

                            endOfMach();

                            rodada++;

                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                            RoundHistoryA.remove(mRoundIndex);
                            RoundHistoryB.remove(mRoundIndex);
                            roundListed.remove(mRoundIndex);

                            mRoundIndex--;

                            if (mPlacarUm >= 12) {
                                mPlacarUm = RoundHistoryA.get(mRoundIndex);

                            }
                            if (mPlacarDois >= 12) {
                                mPlacarDois = RoundHistoryB.get(mRoundIndex);
                            }

                            try {
                                ScoreFragment.getInstace().updateTheTextView();
                            } catch (Exception e) {

                            }

                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Confirma final da rodada?");
            alert.show();




            return points;
        }

    }

        /* Add points to the the vector that stores the score for each round
        Check if max points was reached (12). In this case it limits the max to 12
        Also calls method to check null name.
     */

    public void updatePoints(int Team1, int Team2){

        if (mPlacarUm >= 12) {
            mPlacarUm = 12;
            RoundHistoryA.add(mPlacarUm);
        }
        else {
            RoundHistoryA.add(Team1);
        }
        if (mPlacarDois >= 12) {
            mPlacarDois = 12;
            RoundHistoryB.add(mPlacarDois);
        }
        else {
            RoundHistoryB.add(Team2);
        }

        mRoundIndex++;

        checkNullTeamNameonList(mRoundIndex, mTeam1, mPlacarUm, mPlacarDois, mTeam2);



    }

    /* At the end of the round, it updates the history of the game in the Historico Tab.
       Calls the adapter in the other fragment inside this one. This way is not needed to send
       data to the other Fragment in order to update the history list.
     */

    public void updateList (){
        RodadaAdapter adapter = new RodadaAdapter(getActivity(), round);



        ListView listView =(ListView)((Activity)getContext()).findViewById(R.id.list);


        listView.setAdapter(adapter);

        TextView textView = (TextView) ((Activity)getContext()).findViewById(R.id.emptyElement);
        ImageView imageView = (ImageView) ((Activity)getContext()).findViewById(R.id.alertImg);

        textView.setVisibility(TextView.GONE);
        imageView.setVisibility(ImageView.GONE);

        adapter.notifyDataSetChanged();

        return;
    }


    /* Action when Novo Jogo is selected
       Restart scores and applies configurations from the Settings Fragment
     */
    public void newGame (){

        mPlacarUm = 0;
        mPlacarDois = 0;
        mVitoriasUm = 0;
        mVitoriasDois = 0;
        mRoundIndex = 0;
        RoundHistoryA.clear();
        RoundHistoryB.clear();
        roundListed.clear();


        //delete all list elements in LogFragment (history view)
        round.clear();
        ((SettingsOptions) getActivity()).deleteRounds();
        //reset_round
        rodada = 1;

        try {
            ScoreFragment.getInstace().updateTheTextView();
        } catch (Exception e) {

        }

        RodadaAdapter adapter = new RodadaAdapter(getActivity(), round);

        ListView listView =(ListView)((Activity)getContext()).findViewById(R.id.list);

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


        TextView textView = (TextView) ((Activity)getContext()).findViewById(R.id.emptyElement);
        ImageView imageView = (ImageView) ((Activity)getContext()).findViewById(R.id.alertImg);

        textView.setVisibility(TextView.VISIBLE);
        imageView.setVisibility(ImageView.VISIBLE);


        mTeam1 = ((SettingsOptions) getActivity()).getNameTeam1();
        mTeam2 = ((SettingsOptions) getActivity()).getNameTeam2();

        checkNullTeamNameonList(rodada, mTeam1, mPlacarUm, mPlacarDois, mTeam2);

        TextView txtEq1 = (TextView) ((Activity)getContext()).findViewById(Team1);
        TextView txtEq2 = (TextView) ((Activity)getContext()).findViewById(Team2);
        TextView victoryTxtView1 = (TextView) ((Activity)getContext()).findViewById(R.id.vitorias1);
        TextView victoryTxtView2 = (TextView) ((Activity)getContext()).findViewById(R.id.vitorias2);

        txtEq1.setText(mTeam1);
        txtEq2.setText(mTeam2);

        victoryTxtView1.setText("" + mVitoriasUm);
        victoryTxtView2.setText("" + mVitoriasDois);

        txtEq1.setSelected(true);
        txtEq1.setMarqueeRepeatLimit(-1);
        txtEq2.setSelected(true);
        txtEq2.setMarqueeRepeatLimit(-1);

        RoundHistoryA.add(mPlacarUm);
        RoundHistoryB.add(mPlacarDois);

        Toast.makeText(getContext(), "Novo Jogo Iniciado",Toast.LENGTH_SHORT).show();

        checkNullTeamNameonList(mRoundIndex, mTeam1, mPlacarUm, mPlacarDois, mTeam2);
    }


    /* Check max rounds defined inside Settings Options
        Best of 3, 6, 7 or no limit (999 actually)
     */
    public int definekMaxRounds(){

        int maxRound = ((SettingsOptions) getActivity()).getMaxRounds();

        switch (maxRound) {

            case 0:
                mMaxRounOpt = 999;
                break;

            case 1:
                mMaxRounOpt = 2;
                break;

            case 2:
                mMaxRounOpt = 3;
                break;

            case 3:
                mMaxRounOpt = 4;
                break;

        }

        return mMaxRounOpt;

    }


    /* Pop-up at the End of Match
       Shows which team won the game
       Starts new game
     */
    public void endOfMach (){


            int max = definekMaxRounds();

            if ((mVitoriasUm >= max) ) {

                AlertDialog.Builder victory_builder = new AlertDialog.Builder(getActivity());
                victory_builder.setMessage("Quem ganhou?\n" + mTeam1 + " de " + mVitoriasUm + " a " + mVitoriasDois)
                        .setCancelable(false)
                        .setPositiveButton("Finaliza Jogo", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                newGame();

                            }
                        });

                AlertDialog alert2 = victory_builder.create();
                alert2.setTitle("Fim de Partida!");
                alert2.getWindow().setBackgroundDrawableResource(R.color.primary_light);
                alert2.show();

            }

        if ((mVitoriasDois >= max) ) {


            AlertDialog.Builder victory_builder = new AlertDialog.Builder(getActivity());
            victory_builder.setMessage("Quem ganhou?\n" + mTeam2 + " de " + mVitoriasDois + " a " + mVitoriasUm)
                    .setCancelable(false)
                    .setPositiveButton("Finaliza Jogo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            newGame();

                        }
                    });

            AlertDialog alert2 = victory_builder.create();
            alert2.setTitle("Fim de Partida!");
            alert2.getWindow().setBackgroundDrawableResource(R.color.primary_light);
            alert2.show();

        }
    }


    /* Check if Team Name is null (not defined in the settings options).
       Also limits the max size of the team name displayed in the list of of hands (Voltar Mão)
       So we don't have more than one line (it may happen if user uses CAPS letters)
     */
    public void checkNullTeamNameonList(int roundIndex, String Team1, int placarUm, int PlacarDois, String Team2){
        Team1 = mTeam1;
        Team2 = mTeam2;


        if ((mTeam1 == null) || (mTeam2 == null)){

                if (mTeam1 == null){
                    mTeam1 = "Nós";
                    Team1 = mTeam1;
                }
                if (mTeam1.length() > 9){
                    Team1 = mTeam1.substring(0, 8);
                }
                if (mTeam2 == null){
                    mTeam2 = "Eles";
                    Team2 = mTeam2;
                }
                if (mTeam2.length() > 9){
                    Team2 = mTeam2.substring(0, 8);
                }

                roundListed.add("(" + roundIndex  + ")    " + Team1 + ": " + placarUm + "  -  " + PlacarDois + " :" + Team2);

            }
        else {

            if (mTeam1.length() > 9){
                Team1 = mTeam1.substring(0, 8);
            }

            if (mTeam2.length() > 9){
                Team2 = mTeam2.substring(0, 8);
            };

            roundListed.add("(" + roundIndex  + ")    " + Team1 + ": " + placarUm + "  -  " + PlacarDois + " :" + Team2);
        }
    }


    public void refreshLogFragment (){

        mTeam1 = ((SettingsOptions) getActivity()).getNameTeam1();
        mTeam2 = ((SettingsOptions) getActivity()).getNameTeam2();

        if (mTeam1 == null){
            mTeam1 = "Nós";
        }
        if (mTeam2 == null){
            mTeam2 = "Eles";
        }

        roundListed.clear();

            for (int index = 0; index < rodada-1;  index++ ){
                Rodada rodada = round.get(index);
                rodada.setTeam1(mTeam1);
                rodada.setTeam2(mTeam2);

            RodadaAdapter adapter = new RodadaAdapter(getActivity(), round);

            ListView listView =(ListView)((Activity)getContext()).findViewById(R.id.list);

            listView.setAdapter(adapter);

            adapter.notifyDataSetChanged();


                }
        //roundListed.add("(" + 0  + ")    " + mTeam1 + " " + 0 + "  -  " + 0 + " " + mTeam2); //initial score 0 x 0 on mRoundIndex: 0

        for (int RoundIndex = 0; RoundIndex < mRoundIndex + 1; RoundIndex++ ){


            int a = RoundHistoryA.get(RoundIndex);
            int b = RoundHistoryB.get(RoundIndex);

            checkNullTeamNameonList(RoundIndex, mTeam1, a, b, mTeam2 );
        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SettingsOptions) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataCommunication");
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("mPlacarUm", mPlacarUm);
        outState.putInt("mPlacarDois", mPlacarDois);
        outState.putInt("mVitoriasUm", mVitoriasUm);
        outState.putInt("mVitoriasDois", mVitoriasDois);
        outState.putInt("rodada", rodada);
        outState.putInt("mMaxRounOpt", mMaxRounOpt);
        outState.putInt("mRoundIndex", mRoundIndex);
        outState.putString("mTeam1", mTeam1);
        outState.putString("mTeam2", mTeam2);
        outState.putIntegerArrayList("RoundHistoryA", RoundHistoryA);
        outState.putIntegerArrayList("RoundHistoryB", RoundHistoryB);
        outState.putParcelableArrayList("round",  round);
        outState.putStringArrayList("roundListed", roundListed);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null){
            mPlacarUm = savedInstanceState.getInt("mPlacarUm");
            mPlacarDois = savedInstanceState.getInt("mPlacarDois");
            mVitoriasUm = savedInstanceState.getInt("mVitoriasUm");
            mVitoriasDois = savedInstanceState.getInt("mVitoriasDois");
            rodada = savedInstanceState.getInt("rodada");
            mMaxRounOpt = savedInstanceState.getInt("mMaxRounOpt");
            mRoundIndex = savedInstanceState.getInt("mRoundIndex");
            mTeam1 = savedInstanceState.getString("mTeam1");
            mTeam2 = savedInstanceState.getString("mTeam2");
            RoundHistoryA = savedInstanceState.getIntegerArrayList("RoundHistoryA");
            RoundHistoryB = savedInstanceState.getIntegerArrayList("RoundHistoryB");
            round = savedInstanceState.getParcelableArrayList("round");
            roundListed = savedInstanceState.getStringArrayList("roundListed");
        }

        //Update Team names on the main screen
        TextView txtEq1 = (TextView) ((Activity)getContext()).findViewById(Team1);
        TextView txtEq2 = (TextView) ((Activity)getContext()).findViewById(Team2);

        if (((SettingsOptions) getActivity()).getNameTeam1() != null){

            mTeam1 = (((SettingsOptions) getActivity()).getNameTeam1());

            txtEq1.setText(mTeam1);
            txtEq1.setSelected(true);
            txtEq1.setMarqueeRepeatLimit(-1);

        }
        else {
            txtEq1.setText(mTeam1);
            //Animation on txt team names if they are too long
            txtEq1.setSelected(true);
            txtEq1.setMarqueeRepeatLimit(-1);
        }


        if (((SettingsOptions) getActivity()).getNameTeam2() != null){
            mTeam2 = (((SettingsOptions) getActivity()).getNameTeam2());
            txtEq2.setText(mTeam2);
            txtEq2.setSelected(true);
            txtEq2.setMarqueeRepeatLimit(-1);

        }
        else {
            txtEq2.setText(mTeam2);
            //Animation on txt team names if they are too long
            txtEq2.setSelected(true);
            txtEq2.setMarqueeRepeatLimit(-1);
        }

    }

}
