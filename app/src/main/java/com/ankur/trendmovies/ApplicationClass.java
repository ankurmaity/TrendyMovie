package com.ankur.trendmovies;

import android.app.Application;

/**
 * @author ankur
 */
public class ApplicationClass extends Application {
    private static ApplicationClass app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
    }

    public static ApplicationClass getInstance() {
        return app;
    }
}
