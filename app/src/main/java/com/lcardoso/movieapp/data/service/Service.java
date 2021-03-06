package com.lcardoso.movieapp.data.service;

import com.lcardoso.movieapp.data.model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

//    @GET("movie/popular")
//    Call<MovieResponse> getMovies(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/popular")
    Observable<MovieResponse> getMovies(@Query("api_key") String apiKey, @Query("language") String language);
}
