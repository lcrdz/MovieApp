package com.lcardoso.movieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lcardoso.movieapp.data.model.Movie;
import com.lcardoso.movieapp.data.model.MovieRepository;

import java.util.List;

public class MovieActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MovieActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getMovies() {
        return movieRepository.getMovieLiveData();
    }

    public void clear() {
        movieRepository.clear();
    }
}
