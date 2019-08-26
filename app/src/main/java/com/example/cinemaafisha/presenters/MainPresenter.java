package com.example.cinemaafisha.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.cinemaafisha.R;
import com.example.cinemaafisha.config.Config;
import com.example.cinemaafisha.pojo.Films;
import com.example.cinemaafisha.pojo.TotalFilms;
import com.example.cinemaafisha.retrofit.MyAPI;
import com.example.cinemaafisha.views.MainView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    MyAPI myAPI;

    public void loadFilms(MyAPI apiInterface) {

        getViewState().startLoading();

        CompositeDisposable disposable = new CompositeDisposable();

        disposable.add(apiInterface.getFilms(Config.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TotalFilms>() {

                    @Override
                    public void onNext(TotalFilms totalFilms) {
                        filmsLoaded(totalFilms.results);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(R.string.error_message);
                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }

    private void filmsLoaded(List<Films> films) {
        getViewState().endLoading();
        getViewState().showFilms(films);
    }

    private void showError(int textResource) {
        getViewState().endLoading();
        getViewState().showError(textResource);
    }
}
