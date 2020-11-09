package com.ankur.trendmovies.request;

import com.ankur.trendmovies.utils.Constants;
import com.ankur.trendmovies.utils.Utilities;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ankur
 */
public class RetrofitClient {
    private static OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();

    private static HttpLoggingInterceptor logging
            = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static RetrofitApi retrofitApi;

    public static RetrofitApi getRetrofitApi() {
        if (!Utilities.has(retrofitApi)) {
            if (!httpClient.interceptors().contains(logging)) {
                httpClient.addInterceptor(logging);
                retrofitBuilder.client(httpClient.build());
                retrofit = retrofitBuilder.build();
            }
            retrofitApi = retrofit.create(RetrofitApi.class);
        }

        return retrofitApi;
    }

}
