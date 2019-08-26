package com.example.cinemaafisha.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.cinemaafisha.views.DetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    public void loadFilm() {
        getViewState().startLoading();
        filmLoaded();
    }

    private void filmLoaded() {
        getViewState().endLoading();
        getViewState().showFilm();
    }

}
