package com.ankur.trendmovies.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ankur.trendmovies.ApplicationClass;
import com.ankur.trendmovies.R;
import com.ankur.trendmovies.activities.MovieDetailsActivity;
import com.ankur.trendmovies.models.Movie;
import com.ankur.trendmovies.utils.Constants;
import com.ankur.trendmovies.utils.Utilities;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrendingMovieAdapter extends RecyclerView.Adapter<TrendingMovieAdapter.ViewHolder> {

    private final String TAG = TrendingMovieAdapter.class.getName();
    private List<Movie> movieList;
    private Activity activity;

    public TrendingMovieAdapter(Activity activity, List<Movie> movieList) {
        this.movieList = movieList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    String[] color = {
            "#FF0000", "#FF3300", "#ff6600", "#ff9900", "#FFCC00", "#FFFF00", "#ccff00", "#99ff00", "#66ff00", "#33ff00", "#00FF00"
    };

    int getColor(double value) {

        return Color.parseColor(color[(int) value]);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.nameTV.setText(movie.getTitle());
        if (Utilities.has(movie.getVote_average())) {
            Drawable color = new ColorDrawable(getColor(movie.getVote_average()));
            holder.rateLayout.setImageDrawable(color);
            holder.rateTV.setText(movie.getVote_average() + "");
        } else {
            holder.rateTV.setText("NR");
        }

        Utilities.loadImageInside(ApplicationClass.getInstance(), Constants.BASE_URL_IMAGE_200 + movie.getPoster_path(), holder.posterIV);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;
        TextView rateTV;
        CircleImageView rateLayout;
        ImageView posterIV;

        public ViewHolder(final View v) {
            super(v);

            nameTV = v.findViewById(R.id.name_tv);
            rateTV = v.findViewById(R.id.rating_tv);
            rateLayout = v.findViewById(R.id.circle);
            posterIV = v.findViewById(R.id.poster_iv);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, MovieDetailsActivity.class);
                    intent.putExtra("movieID", movieList.get(getAdapterPosition()).getId()+"");
                    activity.startActivity(intent);
                }
            });
        }
    }
}
