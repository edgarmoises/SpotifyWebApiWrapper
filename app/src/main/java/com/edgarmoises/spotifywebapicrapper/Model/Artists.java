package com.edgarmoises.spotifywebapicrapper.Model;

/**
 * Created by DevelopOSD on 02/12/2015.
 */
public class Artists {

    private ExternalUrls external_urls;
    private String id;
    private String name;

    public ExternalUrls getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(ExternalUrls external_urls) {
        this.external_urls = external_urls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
