package com.example.cinemaafisha.di.module;

import android.content.Context;

import com.example.cinemaafisha.di.qualifier.ApplicationContext;
import com.example.cinemaafisha.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
