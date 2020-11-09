package com.ankur.trendmovies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;

import com.ankur.trendmovies.R;
import com.ankur.trendmovies.adapter.TrendingMovieAdapter;
import com.ankur.trendmovies.models.Movie;
import com.ankur.trendmovies.models.TrendingMoviesResponse;
import com.ankur.trendmovies.request.RetrofitClient;
import com.ankur.trendmovies.utils.Dialogs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TrendingMovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);

        getPopularMovies();
        initialize();
    }

    private void getPopularMovies() {
        Dialog progressDialog = Dialogs.showProgressDialog(PopularActivity.this);
        Call<TrendingMoviesResponse> call = RetrofitClient.getRetrofitApi().trendingMovies();
        call.enqueue(new Callback<TrendingMoviesResponse>() {
            @Override
            public void onResponse(Call<TrendingMoviesResponse> call, Response<TrendingMoviesResponse> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    TrendingMoviesResponse trendingMoviesResponse = response.body();

                    setAdapter(trendingMoviesResponse.getResults());
                    Dialogs.hideProgressDialog(progressDialog);
                }

            }

            @Override
            public void onFailure(Call<TrendingMoviesResponse> call, Throwable t) {
                Dialogs.hideProgressDialog(progressDialog);
            }
        });
    }

    private void initialize() {
        recyclerView = findViewById(R.id.movies_rv);
    }

    private void setAdapter(List<Movie> movieList) {
        movieAdapter = new TrendingMovieAdapter(PopularActivity.this, movieList);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }
}