package com.edgarmoises.spotifywebapicrapper.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.edgarmoises.spotifywebapicrapper.Fragments.ArtistSearchFragment;
import com.edgarmoises.spotifywebapicrapper.Fragments.MainFragment;
import com.edgarmoises.spotifywebapicrapper.R;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        if (savedInstanceState == null){
            LoadFragment();
        }
    }

    private void LoadFragment(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new MainFragment())
                .commit();
    }
}
