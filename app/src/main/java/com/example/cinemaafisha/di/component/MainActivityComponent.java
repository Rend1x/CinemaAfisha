package com.example.cinemaafisha.di.component;

import android.content.Context;

import com.example.cinemaafisha.di.module.AdapterModule;
import com.example.cinemaafisha.di.qualifier.ActivityContext;
import com.example.cinemaafisha.di.scopes.ActivityScope;
import com.example.cinemaafisha.ui.MainActivity;

import dagger.Component;


@ActivityScope
@Component(modules = AdapterModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();


    void injectMainActivity(MainActivity mainActivity);
}
