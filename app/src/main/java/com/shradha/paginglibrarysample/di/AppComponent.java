package com.shradha.paginglibrarysample.di;


import com.shradha.paginglibrarysample.ui.PagingDemoAct;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(PagingDemoAct pagingDemoAct);

}
