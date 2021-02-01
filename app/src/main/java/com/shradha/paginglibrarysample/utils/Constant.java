package com.shradha.paginglibrarysample.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ${Shradha} on 30-01-2021.
 */

public class Constant {

    public final static String LOADING = "Loading";
    public final static String LOADED = "Loaded";
    public final static String CHECK_NETWORK_ERROR = "Check your network connection.";
   // public final static String API_KEY = "https://newsapi.org/docs";//put your api_key generate it from "https://newsapi.org/docs"
    //public static final String sources[] = {"bbc-news", "abc-news-au", "bloomberg", "cnbc"};

    // the size of a page that we want
    public static final int PAGE_SIZE = 5;

    // we will start from the first page which is 1
    public static final int PER_PAGE = 1;

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
