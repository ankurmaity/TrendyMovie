package com.ankur.trendmovies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ankur.trendmovies.R;
import com.ankur.trendmovies.adapter.CastAdapter;
import com.ankur.trendmovies.adapter.TrendingMovieAdapter;
import com.ankur.trendmovies.models.CreditResponse;
import com.ankur.trendmovies.models.Movie;
import com.ankur.trendmovies.models.MovieDetails;
import com.ankur.trendmovies.request.RetrofitClient;
import com.ankur.trendmovies.utils.Constants;
import com.ankur.trendmovies.utils.Dialogs;
import com.ankur.trendmovies.utils.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    private String movieId = "";
    private CreditResponse creditResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieId = getIntent().getExtras().getString("movieID");
        getMovieDetails();
        getCredits();
    }

    private void getMovieDetails() {
        Dialog progressDialog = Dialogs.showProgressDialog(MovieDetailsActivity.this);
        Call<MovieDetails> call = RetrofitClient.getRetrofitApi().moviesDetails(movieId);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    MovieDetails movieDetails = response.body();

                    initialize(movieDetails);
                    Dialogs.hideProgressDialog(progressDialog);
                }

            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Dialogs.hideProgressDialog(progressDialog);
            }
        });
    }

    private void getCredits() {
        Dialog progressDialog = Dialogs.showProgressDialog(MovieDetailsActivity.this);
        Call<CreditResponse> call = RetrofitClient.getRetrofitApi().moviesCredits(movieId);
        call.enqueue(new Callback<CreditResponse>() {
            @Override
            public void onResponse(Call<CreditResponse> call, Response<CreditResponse> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    creditResponse = response.body();
                    setCastAdapter(creditResponse);
                    Dialogs.hideProgressDialog(progressDialog);
                }

            }

            @Override
            public void onFailure(Call<CreditResponse> call, Throwable t) {
                Dialogs.hideProgressDialog(progressDialog);
            }
        });
    }

    private void setCastAdapter(CreditResponse creditResponse) {
        RecyclerView recyclerView = findViewById(R.id.cast_rv);
        CastAdapter castAdapter = new CastAdapter(MovieDetailsActivity.this, creditResponse.getCast());
        recyclerView.setAdapter(castAdapter);

        LinearLayoutManager horizontalLayout
                = new LinearLayoutManager(
                MovieDetailsActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false);

        recyclerView.setLayoutManager(horizontalLayout);
    }

    private void initialize(MovieDetails details) {
        Utilities.loadImageInside(MovieDetailsActivity.this, Constants.BASE_URL_IMAGE_400 + details.getPoster_path(), findViewById(R.id.poster_iv));
        ((TextView) findViewById(R.id.name_tv)).setText(details.getTitle());
        ((TextView) findViewById(R.id.rating_tv)).setText(details.getVote_average() + "");
        ((TextView) findViewById(R.id.vote_count)).setText(details.getVote_count() + " votes");
        ((TextView) findViewById(R.id.tagline)).setText(details.getTagline());
        ((TextView) findViewById(R.id.overview)).setText(details.getOverview());

        String release_genre = details.getRelease_date() + " | " + details.getGenres() + " | " + details.getRuntime();
        ((TextView) findViewById(R.id.release_genre_text)).setText(release_genre);
    }
}