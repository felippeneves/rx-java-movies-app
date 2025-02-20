package com.felippeneves.rx_java_movies_app.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.felippeneves.rx_java_movies_app.BuildConfig;
import com.felippeneves.rx_java_movies_app.R;
import com.felippeneves.rx_java_movies_app.adapter.MovieAdapter;
import com.felippeneves.rx_java_movies_app.model.Movie;
import com.felippeneves.rx_java_movies_app.model.MovieDBResponse;
import com.felippeneves.rx_java_movies_app.service.MoviesDataService;
import com.felippeneves.rx_java_movies_app.service.RetrofitInstance;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeContainer;
    private Observable<MovieDBResponse> movieDBResponseObservable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(" TMDb Popular Movies Today");

        getPopularMovies();

        swipeContainer = findViewById(R.id.swipe_layout);
        swipeContainer.setColorSchemeResources(R.color.colorPrimary);
        swipeContainer.setOnRefreshListener(() -> getPopularMovies());
    }

    public void getPopularMovies() {
        movies = new ArrayList<>();
        MoviesDataService getMoviesDataService = RetrofitInstance.getService();
        movieDBResponseObservable = getMoviesDataService.getPopularMovies(BuildConfig.API_KEY);

        compositeDisposable.add(
                movieDBResponseObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(movieDBResponse -> Observable.fromArray(movieDBResponse.getMovies().toArray(new Movie[0])))
                        .filter(movie -> movie.getVoteAverage() > 7.0)
                        .subscribeWith(new DisposableObserver<Movie>() {
                            @Override
                            public void onNext(Movie movie) {
                                movies.add(movie);
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onComplete() {
                                init();
                                swipeContainer.setRefreshing(false);
                            }
                        })
        );
    }

    public void init() {
        recyclerView = findViewById(R.id.rvMovies);
        movieAdapter = new MovieAdapter(this, movies);


        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
