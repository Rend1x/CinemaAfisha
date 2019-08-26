package com.example.cinemaafisha.views;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.cinemaafisha.pojo.Films;

import java.util.List;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface MainView extends MvpView {

    void startLoading();

    void endLoading();

    void showError(int textResource);

    void showFilms(List<Films> films);
}
