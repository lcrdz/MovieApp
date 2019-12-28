package com.lcardoso.movieapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lcardoso.movieapp.R;
import com.lcardoso.movieapp.data.model.Movie;
import com.lcardoso.movieapp.util.RecyclerItemClickListener;
import com.lcardoso.movieapp.viewmodel.MovieActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MovieActivity extends AppCompatActivity {

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerMovie;
    private MovieAdapter movieAdapter;
    private MovieActivityViewModel movieActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieActivityViewModel = ViewModelProviders.of(this).get(MovieActivityViewModel.class);
        getMovies();
    }

    private void getMovies() {

        movieActivityViewModel.getMovies().observe(this, movies -> {
            movieList = movies;
            setRecyclerView();
            touchRecycler();
        });
    }

    public void setRecyclerView() {
        recyclerMovie = findViewById(R.id.recyclerMovie);
        movieAdapter = new MovieAdapter(movieList);
        recyclerMovie.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        recyclerMovie.setAdapter(movieAdapter);
    }

    public void touchRecycler() {
        recyclerMovie.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                recyclerMovie,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Movie movie = movieList.get(position);
                        Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                        intent.putExtra("movie", movie);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieActivityViewModel.clear();
    }
}
