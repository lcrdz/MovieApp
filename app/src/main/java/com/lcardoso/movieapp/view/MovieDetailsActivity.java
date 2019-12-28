package com.lcardoso.movieapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcardoso.movieapp.R;
import com.lcardoso.movieapp.data.model.Movie;
import com.squareup.picasso.Picasso;

import static com.lcardoso.movieapp.util.Constants.IMAGE_URL;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView imageMovieDetail;
    private TextView tvTitle, tvRating, tvDate, tvDescription, tvBack;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        getMovie();
        setComponents();
    }

    private void getMovie() {
        movie = getIntent().getParcelableExtra("movie");
    }

    private void setComponents() {
        imageMovieDetail = findViewById(R.id.imageMovieDetail);
        tvTitle = findViewById(R.id.tvTitle);
        tvRating = findViewById(R.id.tvRating);
        tvDate = findViewById(R.id.tvDate);
        tvDescription = findViewById(R.id.tvDescription);
        tvBack = findViewById(R.id.tvBack);

        Picasso.get().load(IMAGE_URL + movie.getBackdropPath())
                .placeholder(R.drawable.ic_launcher_background).into(imageMovieDetail);
        tvTitle.setText(movie.getTitle());
        tvRating.setText(movie.voteAverage.toString());
        tvDate.setText(movie.getReleaseDate());
        tvDescription.setText(movie.getOverview());
        tvBack.setOnClickListener(v -> onBackPressed());
    }
}
