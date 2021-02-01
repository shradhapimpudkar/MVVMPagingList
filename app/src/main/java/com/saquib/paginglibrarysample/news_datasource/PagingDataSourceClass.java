package com.saquib.paginglibrarysample.news_datasource;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.saquib.paginglibrarysample.utils.Constant;
import com.saquib.paginglibrarysample.utils.DataModelClass;
import com.saquib.paginglibrarysample.utils.Repository;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ${Shradha} on 30-01-2021.
 */
public class PagingDataSourceClass extends PageKeyedDataSource<Integer, DataModelClass> {

    private Repository repository;
    private Gson gson;
    private int sourceIndex;
    private MutableLiveData<String> progressLiveStatus;
    private CompositeDisposable compositeDisposable;

    PagingDataSourceClass(Repository repository, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
        progressLiveStatus = new MutableLiveData<>();
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = builder.setLenient().create();
    }


    public MutableLiveData<String> getProgressLiveStatus() {
        return progressLiveStatus;
    }


    /*
     * This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */
    @SuppressLint("CheckResult")
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, DataModelClass> callback) {

        repository.executePagingApi(sourceIndex)
                .doOnSubscribe(disposable ->
                {
                    compositeDisposable.add(disposable);
                    progressLiveStatus.postValue(Constant.LOADING);
                })
                .subscribe(
                        (JsonElement result) ->
                        {
                            progressLiveStatus.postValue(Constant.LOADED);

                            JSONObject object = new JSONObject(gson.toJson(result));
                            object.getInt("page");
                            object.getInt("per_page");
                            object.getInt("total");
                            object.getInt("total_pages");
                            JSONArray array = object.getJSONArray("data");

                            ArrayList<DataModelClass> arrayList = new ArrayList<>();

                            for (int i = 0; i < array.length(); i++) {
                               /* arrayList.add(new DataModelClass(
                                        array.getJSONObject(i).optString("email"),
                                        array.getJSONObject(i).optString("urlToImage")));*/

                                arrayList.add(new DataModelClass(
                                        array.getJSONObject(i).optInt("id"),
                                        array.getJSONObject(i).optString("email"),
                                        array.getJSONObject(i).optString("first_name"),
                                        array.getJSONObject(i).optString("last_name"),
                                        array.getJSONObject(i).optString("avatar")));
                            }

                            sourceIndex++;
                            callback.onResult(arrayList, null, sourceIndex);
                        },
                        throwable ->
                                progressLiveStatus.postValue(Constant.LOADED)

                );

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataModelClass> callback) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataModelClass> callback) {

        repository.executePagingApi(params.key)
                .doOnSubscribe(disposable ->
                {
                    compositeDisposable.add(disposable);
                    progressLiveStatus.postValue(Constant.LOADING);
                })
                .subscribe(
                        (JsonElement result) ->
                        {
                            progressLiveStatus.postValue(Constant.LOADED);

                            JSONObject object = new JSONObject(gson.toJson(result));
                            JSONArray array = object.getJSONArray("articles");

                            ArrayList<DataModelClass> arrayList = new ArrayList<>();

                            for (int i = 0; i < array.length(); i++) {
                               /* arrayList.add(new DataModelClass(array.getJSONObject(i).optString("title"),
                                        array.getJSONObject(i).optString("urlToImage")));*/

                                arrayList.add(new DataModelClass(
                                        array.getJSONObject(i).optInt("id"),
                                        array.getJSONObject(i).optString("email"),
                                        array.getJSONObject(i).optString("first_name"),
                                        array.getJSONObject(i).optString("last_name"),
                                        array.getJSONObject(i).optString("avatar")));

                            }

                            callback.onResult(arrayList, params.key == 3 ? null : params.key + 1);

                        },
                        throwable ->
                                progressLiveStatus.postValue(Constant.LOADED)
                );
    }
}
