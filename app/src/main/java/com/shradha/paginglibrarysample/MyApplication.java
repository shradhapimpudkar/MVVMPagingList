package com.shradha.paginglibrarysample;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.shradha.paginglibrarysample.di.AppComponent;
import com.shradha.paginglibrarysample.di.DaggerAppComponent;
import com.shradha.paginglibrarysample.di.UtilsModule;


/**
 * Created by ${Shradha} on 30-01-2021.
 */

public class MyApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().utilsModule(new UtilsModule()).build();
        Fresco.initialize(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
