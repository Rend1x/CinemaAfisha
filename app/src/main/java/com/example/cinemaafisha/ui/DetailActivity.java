package com.example.cinemaafisha.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.cinemaafisha.R;
import com.example.cinemaafisha.presenters.DetailPresenter;
import com.example.cinemaafisha.views.DetailView;
import com.squareup.picasso.Picasso;

import static com.example.cinemaafisha.config.Config.BASE_URL_IMAGE;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {

    @InjectPresenter
    DetailPresenter mDetailPresenter;

    private ImageView mImageView;
    private TextView mTitle;
    private TextView mOverview;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mImageView = findViewById(R.id.detail_image);
        mTitle = findViewById(R.id.detail_title);
        mOverview = findViewById(R.id.detail_overview);
        mProgressBar = findViewById(R.id.detail_progress_bar);

        mDetailPresenter.loadFilm();
    }

    @Override
    public void startLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mImageView.setVisibility(View.GONE);
        mTitle.setVisibility(View.GONE);
        mOverview.setVisibility(View.GONE);
    }

    @Override
    public void endLoading() {
        mProgressBar.setVisibility(View.GONE);
        mImageView.setVisibility(View.VISIBLE);
        mTitle.setVisibility(View.VISIBLE);
        mOverview.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFilm() {
        String title = getIntent().getStringExtra("title");
        String path = getIntent().getStringExtra("path");
        String overview = getIntent().getStringExtra("overview");
        Picasso.with(this)
                .load(BASE_URL_IMAGE + path)
                .placeholder(R.drawable.ic_photo_black_24dp)
                .into(mImageView);

        mTitle.setText(title);
        mOverview.setText(overview);
    }
}
