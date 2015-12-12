package com.edgarmoises.spotifywebapicrapper.RestService;

import retrofit.RestAdapter;

/**
 * Created by DevelopOSD on 02/12/2015.
 */
public class ServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint){
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .build();

        T service = restAdapter.create(clazz);

        return service;
    }
}
