package com.example.azurezoomi.testproject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by AzureZoomi on 5/13/2016.
 */
public interface WebService {

    String ENDPOINT_URL = "https://ajax.googleapis.com/ajax/services/";

    @GET("feed/find")
    Observable<Blog> findFeeds(@Query("v") String version, @Query("q") String qurey);

}
