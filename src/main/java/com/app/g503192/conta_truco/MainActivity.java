package com.app.g503192.conta_truco;

//import android.app.ActionBar;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements SettingsOptions {

        String mTeam1Name;
        String mTeam2Name;
        int mMaxRoundsOpt;


        private PrefManager prefManager;

        static ArrayList<Rodada> round = new ArrayList<Rodada>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setIcon(R.drawable.truco_app_icon_no_shadow);


        // Set up the action bar.
        ActionBar actionBar = getSupportActionBar();
//set custom actionbar
        actionBar.setCustomView(R.layout.title_bar);
//Displays the custom design in the actionbar
        actionBar.setDisplayShowCustomEnabled(true);
//Turns the homeIcon a View
//        View homeIcon = findViewById(android.R.id.home);
////Hides the View (and so the icon)
//        ((View)homeIcon.getParent()).setVisibility(View.GONE);


        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Ache o layout da aba que mostra as abas
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);


        // Conecte o layout da aba com o view pager. Isto irá
        //   1. Atualizar o layout da aba quando o view pager for deslizado
        //   2. Atualizar o view pager quando uma aba for selecionada
        //   3. Definir os nomes da aba do layout da aba com os títulos do adapter do view pager
        //      chamando onPageTitle()
        tabLayout.setupWithViewPager(viewPager);

        //Add images to tabs
//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_history);
//        tabLayout.getTabAt(2).setIcon(R.drawable.ic_settings);


        // Checking for first time launch - before calling helpDialog()
        prefManager = new PrefManager(this);
        if (prefManager.isFirstTimeLaunch()) {
            prefManager.setFirstTimeLaunch(false);
            HelpDialog alert = new HelpDialog();
            alert.showDialog(this);
        }


//


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                HelpDialog alert = new HelpDialog();
                alert.showDialog(this);
                break;

            case R.id.action_about:
                AboutDialog about = new AboutDialog();
                about.AboutDialog(this);
        }
        return true;
    }

    @Override
    public void setNameTeam1 (String team1){
        mTeam1Name = team1;
    }

    @Override
    public void setNameTeam2 (String team2){
        mTeam2Name = team2;
    }

    @Override
    public void setMaxRounds (int maxRounds){
        mMaxRoundsOpt = maxRounds;
    }

    @Override
    public String getNameTeam1 (){
        return mTeam1Name;
    }

    @Override
    public String getNameTeam2 (){
        return mTeam2Name;
    }

    @Override
    public int getMaxRounds (){
        return mMaxRoundsOpt;
    }

    @Override
    public void sendRounds(int Round, int Score1, int Score2, int Victory1, int Victory2, String Team1, String Team2) {
        round.add(new Rodada(Round, Score1, Score2, Victory1, Victory2, Team1, Team2));
    }

    @Override
    public ArrayList<Rodada> getRounds() {
        return round;
    }

    @Override
    public void deleteRounds() {
        round.clear();

    }

}
