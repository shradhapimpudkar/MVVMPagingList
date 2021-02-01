package com.shradha.paginglibrarysample.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.shradha.paginglibrarysample.paging_datasource.PagingDataSourceClass;
import com.shradha.paginglibrarysample.paging_datasource.PagingDataSourceFactory;
import com.shradha.paginglibrarysample.utils.Constant;
import com.shradha.paginglibrarysample.utils.DataModelClass;
import com.shradha.paginglibrarysample.utils.Repository;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by ${Shradha} on 30-01-2021.
 */
public class PagingLibViewModel extends ViewModel {

    private PagingDataSourceFactory pagingDataSourceFactory;
    private LiveData<PagedList<DataModelClass>> listLiveData;

    private LiveData<String> progressLoadStatus = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PagingLibViewModel(Repository repository) {
        pagingDataSourceFactory = new PagingDataSourceFactory(repository, compositeDisposable);
        initializePaging();
    }


    private void initializePaging() {

        PagedList.Config pagedListConfig =
                new PagedList.Config.Builder()
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(Constant.PAGE_SIZE)
                        .setPageSize(Constant.PER_PAGE).build();

        listLiveData = new LivePagedListBuilder<>(pagingDataSourceFactory, pagedListConfig)
                .build();

        progressLoadStatus = Transformations.switchMap(pagingDataSourceFactory.getMutableLiveData(), PagingDataSourceClass::getProgressLiveStatus);

    }

    public LiveData<String> getProgressLoadStatus() {
        return progressLoadStatus;
    }

    public LiveData<PagedList<DataModelClass>> getListLiveData() {
        return listLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}