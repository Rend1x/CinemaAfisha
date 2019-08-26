package com.example.cinemaafisha.di.component;

import android.content.Context;

import com.example.cinemaafisha.helper.MyApplication;
import com.example.cinemaafisha.di.module.ContextModule;
import com.example.cinemaafisha.di.module.RetrofitModule;
import com.example.cinemaafisha.di.qualifier.ApplicationContext;
import com.example.cinemaafisha.di.scopes.ApplicationScope;
import com.example.cinemaafisha.retrofit.MyAPI;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public MyAPI getApiInterface();

    @ApplicationContext
    public Context getContext();

    public void injectApplication(MyApplication myApplication);
}
