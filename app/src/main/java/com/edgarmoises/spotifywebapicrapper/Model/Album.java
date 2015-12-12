package com.edgarmoises.spotifywebapicrapper.Model;

import java.util.List;

/**
 * Created by DevelopOSD on 02/12/2015.
 */
public class Album {

    private List<Images> images;
    private String name;

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
