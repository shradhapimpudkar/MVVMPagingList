package com.saquib.paginglibrarysample.utils;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ${Shradha} on 30-01-2021.
 */
public interface ApiCallInterface {

    @GET(Urls.FetchPagingList)
    Observable<JsonElement> fetchListpaging(
            @Query("page") int page,
            @Query("per_page") int pagesize);

}
