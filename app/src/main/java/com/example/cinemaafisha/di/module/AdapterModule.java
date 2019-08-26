package com.example.cinemaafisha.di.module;


import com.example.cinemaafisha.adapter.ClickListener;
import com.example.cinemaafisha.adapter.RecyclerViewAdapter;
import com.example.cinemaafisha.di.scopes.ActivityScope;
import com.example.cinemaafisha.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getFilmLIst(ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }
}
