package com.edgarmoises.spotifywebapicrapper.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edgarmoises.spotifywebapicrapper.Adapters.SongsAdapter;
import com.edgarmoises.spotifywebapicrapper.Model.Items;
import com.edgarmoises.spotifywebapicrapper.Model.SpotifySongs;
import com.edgarmoises.spotifywebapicrapper.R;
import com.edgarmoises.spotifywebapicrapper.RestService.ServiceFactory;
import com.edgarmoises.spotifywebapicrapper.RestService.SpotifyService;
import com.edgarmoises.spotifywebapicrapper.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DevelopOSD on 02/12/2015.
 */
public class ArtistSearchFragment extends Fragment {

    private View mRootView;
    private RecyclerView mResultsList;
    private RecyclerView.LayoutManager mManager;
    private SongsAdapter mAdapter;
    private TextView mEmpty;

    private List<Items> mItems;

    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        mItems = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.results_fragment, container, false);

        InitViews();

        return mRootView;
    }

    private void InitViews(){
        mResultsList = (RecyclerView) mRootView.findViewById(R.id.results_list);
        mAdapter = new SongsAdapter(getActivity());
        mAdapter.setOnItemClickListener(new SongsAdapter.ItemClickListener(){
            @Override
            public void onClick(View view, int position) {
                mItems = mAdapter.getResultsList();
                Items item = mItems.get(position);

                String uri = item.getArtists().get(0).getExternal_urls().getSpotify();
                Intent launcher = new Intent( Intent.ACTION_VIEW, Uri.parse(uri) );
                startActivity(launcher);
            }
        });
        mManager = new LinearLayoutManager(getActivity());
        mResultsList.setLayoutManager(mManager);
        mResultsList.setAdapter(mAdapter);

        mEmpty = (TextView) mRootView.findViewById(R.id.emptyView);
        CheckAdapterSize();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.search_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                MakeRequest(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void MakeRequest(String search){

        ShowProgressDialog();

        SpotifyService service = ServiceFactory.createRetrofitService(SpotifyService.class, Constants.SPOTIFY_BASE_URL);
        service.getSongs(search)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SpotifySongs>() {
                    @Override
                    public void onCompleted() {
                        CheckAdapterSize();
                        HideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Request", e.getMessage());
                        HideProgressDialog();
                    }

                    @Override
                    public void onNext(SpotifySongs spotifySongs) {
                        mAdapter.AddInformation(spotifySongs.getTracks().getItems());
                    }
                });

    }

    private void CheckAdapterSize(){
        if (mAdapter.getItemCount() == 0){
            mEmpty.setVisibility(View.VISIBLE);
        }else{
            mEmpty.setVisibility(View.GONE);
        }
    }

    private void ShowProgressDialog(){
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity(), R.style.ProgressDialogStyle);
            mProgressDialog = ProgressDialog.show(getActivity(), getString(R.string.progress_title), getString(R.string.progress_message));
        }
    }

    private void HideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
