package com.felippeneves.rx_java_movies_app.service;

import com.felippeneves.rx_java_movies_app.model.MovieDBResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesDataService {
    @GET("movie/popular")
    Observable<MovieDBResponse> getPopularMovies(@Query("api_key") String apiKey);
}
