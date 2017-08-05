package com.app.g503192.conta_truco;

import java.util.ArrayList;

/**
 * Created by Thiago on 28/07/2017.
 */

public interface SettingsOptions {

    void sendRounds (int Round, int Score1, int Score2, int Victory1, int Victory2, String Team1, String Team2 );
    ArrayList<Rodada> getRounds ();
    void deleteRounds();

    void setNameTeam1 (String team1);
    void setNameTeam2 (String team2);
    void setMaxRounds (int maxRounds);

    String getNameTeam1 ();
    String getNameTeam2 ();
    int getMaxRounds ();




}
