package com.saquib.paginglibrarysample.news_datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.saquib.paginglibrarysample.utils.DataModelClass;
import com.saquib.paginglibrarysample.utils.Repository;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by ${Shradha} on 30-01-2021.
 */
public class PagingDataSourceFactory extends DataSource.Factory<Integer, DataModelClass> {

    private MutableLiveData<PagingDataSourceClass> liveData;
    private Repository repository;
    private CompositeDisposable compositeDisposable;

    public PagingDataSourceFactory(Repository repository, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<PagingDataSourceClass> getMutableLiveData() {
        return liveData;
    }

    @Override
    public DataSource<Integer, DataModelClass> create() {
        PagingDataSourceClass dataSourceClass = new PagingDataSourceClass(repository, compositeDisposable);
        liveData.postValue(dataSourceClass);
        return dataSourceClass;
    }
}
