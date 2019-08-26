package com.example.cinemaafisha.helper;

import android.app.Activity;
import android.app.Application;

import com.example.cinemaafisha.di.component.ApplicationComponent;
import com.example.cinemaafisha.di.component.DaggerApplicationComponent;
import com.example.cinemaafisha.di.module.ContextModule;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);
    }

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}


