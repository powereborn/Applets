package com.example.nicolas.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nicolas.fragment.ItemClubFragment;
import com.example.nicolas.fragment.WebViewFragment;
import com.example.nicolas.helper.ClubBDD;
import com.example.nicolas.model.Club;

import java.util.ResourceBundle;


public class MainActivity extends ActionBarActivity implements ItemClubFragment.OnFragmentInteractionListener, WebViewFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Gestion de la transaction avec le fragment club
         */
        if (savedInstanceState == null) {

            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();

            ItemClubFragment fragmentFound = (ItemClubFragment) fm.findFragmentByTag("fragmentClub");

            if(fragmentFound == null) {
                ItemClubFragment fragment = new ItemClubFragment();
                transaction.add(R.id.container, fragment, "fragmentClub");
            }
            else
                transaction.show(fragmentFound);

            transaction.commit();
        }

        /**
         * INSERTION DES CLUBS
         */
        try {

            ClubBDD clubBdd = new ClubBDD(this);

            clubBdd.open();

            clubBdd.clearClub();
            clubBdd.insertClub(new Club("Applets", "A-1966", getResources().getIdentifier("ic_applets", "drawable", getPackageName()), "http://www.clubapplets.ca/"));
            clubBdd.insertClub(new Club("Conjure", "A-1111", getResources().getIdentifier("ic_conjure", "drawable", getPackageName()), "http://www.conjure.etsmtl.ca"));

            clubBdd.close();
        }
        catch(Exception e) { Toast.makeText(this, R.string.error_insert_bdd + e.getMessage(), Toast.LENGTH_LONG).show(); }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String name, String website) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        WebViewFragment fragmentFound = (WebViewFragment) fm.findFragmentByTag("fragmentWebView");

        if(fragmentFound == null) {
            WebViewFragment fragment = new WebViewFragment();

            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("website", website);
            fragment.setArguments(bundle);

            transaction.replace(R.id.container, fragment, "fragmentWebView");
            transaction.addToBackStack(null);

        }
        else {
            fragmentFound.setWebSite(name, website);
            transaction.show(fragmentFound);
        }

        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
