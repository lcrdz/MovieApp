package com.lcardoso.movieapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lcardoso.movieapp.R;
import com.lcardoso.movieapp.data.model.Movie;
import com.lcardoso.movieapp.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.bind(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle, tvDescription;
        public ImageView imageMovie;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imageMovie = itemView.findViewById(R.id.imageMovie);
        }

        public void bind(Movie movie) {

            if (movie.getOverview().length() > 300) {
                tvDescription.setText(movie.getOverview().substring(0, 300) + "...");
            } else if (movie.getOverview().length() == 0) {
                tvDescription.setText("Filme sem descrição...");
            } else {
                tvDescription.setText(movie.getOverview());
            }
            tvTitle.setText(movie.getTitle());
            Picasso.get().load(Constants.IMAGE_URL + movie.getPosterPath())
                    .placeholder(R.drawable.ic_launcher_foreground).into(imageMovie);
        }
    }
}
