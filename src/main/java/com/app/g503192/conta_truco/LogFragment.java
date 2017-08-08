package com.app.g503192.conta_truco;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
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

        //ADS
        // Place correct AD unit IDs for debug and realease versions

//        myLogFragmentBannerId: ca-app-pub-4711925247199151/5679425415
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
            mAdView.setAdUnitId("ca-app-pub-4711925247199151/5679425415");
            mAdView.setAdSize(AdSize.SMART_BANNER);

            AdRequest request = new AdRequest.Builder().build();

            if(mAdView.getAdSize() != null || mAdView.getAdUnitId() != null)
                mAdView.loadAd(request);
            // else Log state of adsize/adunit
            ((LinearLayout) rootView.findViewById(R.id.adView)).addView(mAdView);
        }

        return rootView;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        round = ((SettingsOptions) getActivity()).getRounds();
        outState.putParcelableArrayList("round", round);

    }


}
