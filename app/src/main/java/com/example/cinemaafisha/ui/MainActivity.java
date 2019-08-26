package com.example.cinemaafisha.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.cinemaafisha.adapter.ClickListener;
import com.example.cinemaafisha.helper.MyApplication;
import com.example.cinemaafisha.R;
import com.example.cinemaafisha.adapter.RecyclerViewAdapter;
import com.example.cinemaafisha.di.component.ApplicationComponent;
import com.example.cinemaafisha.di.component.DaggerMainActivityComponent;
import com.example.cinemaafisha.di.component.MainActivityComponent;
import com.example.cinemaafisha.di.module.MainActivityContextModule;
import com.example.cinemaafisha.di.qualifier.ActivityContext;
import com.example.cinemaafisha.di.qualifier.ApplicationContext;
import com.example.cinemaafisha.pojo.Films;
import com.example.cinemaafisha.presenters.MainPresenter;
import com.example.cinemaafisha.retrofit.MyAPI;
import com.example.cinemaafisha.views.MainView;


import java.util.List;

import javax.inject.Inject;


public class MainActivity extends MvpAppCompatActivity implements ClickListener, MainView {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mErrorMessage;
    MainActivityComponent mMainActivityComponent;

    @Inject
    public RecyclerViewAdapter mRecyclerViewAdapter;

    @Inject
    public MyAPI mApiInterface;

    @InjectPresenter
    MainPresenter mMainPresenter;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context mActivityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mErrorMessage = findViewById(R.id.error_text);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mMainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mMainActivityComponent.injectMainActivity(this);
        mMainPresenter.loadFilms(mApiInterface);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void launchIntent(String title, String path, String overview) {
        startActivity(new Intent(mActivityContext, DetailActivity.class)
                .putExtra("title", title)
                .putExtra("path", path)
                .putExtra("overview", overview));
    }

    @Override
    public void startLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void endLoading() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(int textResource) {
        mErrorMessage.setText(getText(textResource));
    }

    @Override
    public void showFilms(List<Films> films) {
        mRecyclerViewAdapter.setData(films);
    }

}
