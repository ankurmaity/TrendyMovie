package com.ankur.trendmovies.request;


import com.ankur.trendmovies.models.CreditResponse;
import com.ankur.trendmovies.models.MovieDetails;
import com.ankur.trendmovies.models.TrendingMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author ankur
 */
public interface RetrofitApi {
    String APIKEY = "57c051b4759d0cb95e0c56c0f402ce28";

    @GET("/3/trending/movie/day?api_key=" + APIKEY)
    Call<TrendingMoviesResponse> trendingMovies();

    @GET("/3/movie/{movie_id}/credits?api_key=" + APIKEY)
    Call<CreditResponse> moviesCredits(@Path("movie_id") String movieId);

    @GET("/3/movie/{movie_id}?api_key=" + APIKEY)
    Call<MovieDetails> moviesDetails(@Path("movie_id") String movieId);
}
