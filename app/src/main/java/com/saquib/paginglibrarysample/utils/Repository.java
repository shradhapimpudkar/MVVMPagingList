package com.saquib.paginglibrarysample.utils;

import com.google.gson.JsonElement;

import io.reactivex.Observable;

/**
 * Created by ${Shradha} on 30-01-2021.
 */

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    /*
     * method to call paging api
     */
    public Observable<JsonElement> executePagingApi(int index) {
        return apiCallInterface.fetchListpaging(Constant.PAGE_SIZE, Constant.PER_PAGE);
    }

}
