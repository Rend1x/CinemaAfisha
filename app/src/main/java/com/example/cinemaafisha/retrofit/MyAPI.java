package com.example.cinemaafisha.retrofit;

import com.example.cinemaafisha.pojo.TotalFilms;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyAPI {
    @GET("popular")
    Observable<TotalFilms> getFilms(
            @Query("api_key") String api
    );
}
