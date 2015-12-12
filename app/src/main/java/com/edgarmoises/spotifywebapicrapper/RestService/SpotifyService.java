package com.edgarmoises.spotifywebapicrapper.RestService;

import com.edgarmoises.spotifywebapicrapper.Model.SpotifySongs;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by DevelopOSD on 30/11/2015.
 */
public interface SpotifyService {

    @GET("/v1/search?type=track")
    Observable<SpotifySongs> getSongs(@Query("q") String songName);
}
