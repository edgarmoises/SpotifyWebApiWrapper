package com.edgarmoises.spotifywebapicrapper.Model;

import java.util.List;

/**
 * Created by DevelopOSD on 02/12/2015.
 */
public class Items {

    private Album album;
    private List<Artists> artists;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Artists> getArtists() {
        return artists;
    }

    public void setArtists(List<Artists> artists) {
        this.artists = artists;
    }
}
