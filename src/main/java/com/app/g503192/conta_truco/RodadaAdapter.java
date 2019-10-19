package com.app.g503192.conta_truco;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Thiago on 05/07/2017.
 */

public class RodadaAdapter extends ArrayAdapter<Rodada>  {


    public RodadaAdapter (Activity context, ArrayList<Rodada> round) {

        super(context, 0, round);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Rodada currentRodada = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView rodada = (TextView) listItemView.findViewById(R.id.rodada);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        rodada.setText("" + currentRodada.getRound());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView score1 = (TextView) listItemView.findViewById(R.id.placar1_list);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        score1.setText("" +currentRodada.getmScore1());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView score2 = (TextView) listItemView.findViewById(R.id.placar2_list);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        score2.setText("" +currentRodada.getmScore2());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView team1 = (TextView) listItemView.findViewById(R.id.vitorias1);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        team1.setText("(" +currentRodada.getVictory1() + ")");

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView team2 = (TextView) listItemView.findViewById(R.id.vitorias2);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        team2.setText("(" +currentRodada.getVictory2() + ")");

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView team1Name = (TextView) listItemView.findViewById(R.id.Team1);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        team1Name.setText("" +currentRodada.getTeam1());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView team2Name = (TextView) listItemView.findViewById(R.id.Team2);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        team2Name.setText("" +currentRodada.getTeam2());





        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
