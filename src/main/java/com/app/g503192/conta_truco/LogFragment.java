package com.app.g503192.conta_truco;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogFragment extends Fragment {

   static ArrayList<Rodada> round = new ArrayList<Rodada>();
    private AdView mAdView;

    public LogFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         //Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.list_view, container, false);


        if (savedInstanceState != null){

            TextView textView = (TextView) rootView.findViewById(R.id.emptyElement);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.alertImg);

            textView.setVisibility(TextView.INVISIBLE);
            imageView.setVisibility(ImageView.INVISIBLE);

            round = savedInstanceState.getParcelableArrayList("round");

            RodadaAdapter adapter = new RodadaAdapter(getActivity(), round);

            ListView listView = (ListView) rootView.findViewById(R.id.list);

            listView.setAdapter(adapter);

        }


        mAdView = (AdView)  rootView.findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder()
                .addTestDevice("33BE2250B43518CCDA7DE426D04EE232")
                .build();

        mAdView.loadAd(request);

        return rootView;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        round = ((SettingsOptions) getActivity()).getRounds();
        outState.putParcelableArrayList("round", round);

    }


}
