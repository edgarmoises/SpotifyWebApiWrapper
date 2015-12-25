package com.edgarmoises.spotifywebapicrapper.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgarmoises.spotifywebapicrapper.Adapters.ViewPagerAdapter;
import com.edgarmoises.spotifywebapicrapper.R;

/**
 * Created by DevelopOSD on 08/12/2015.
 */
public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();

    private View mRootView;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ViewPagerAdapter mPageradapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.main_fragment, container, false);

        InitViews();

        return mRootView;
    }

    private void InitViews(){
        mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        mViewPager = (ViewPager) mRootView.findViewById(R.id.viewPager);
        mPageradapter = new ViewPagerAdapter(getFragmentManager());
        AddFragments();
        mViewPager.setAdapter(mPageradapter);

        mTabLayout = (TabLayout) mRootView.findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void AddFragments(){
        mPageradapter.addFragment(new ArtistSearchFragment(), getResources().getString(R.string.artist_fragment_title));
        mPageradapter.addFragment(new SongSearchFragment(), getResources().getString(R.string.song_fragment_title));
    }
}
