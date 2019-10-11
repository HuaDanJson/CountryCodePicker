package com.example.countrycodepicker;

import android.app.Application;

public class BaseApplication extends Application {

    private static BaseApplication INSTANCE;

    public static BaseApplication getInstance() {

        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            INSTANCE = new BaseApplication();
            INSTANCE.onCreate();
            return INSTANCE;
        }
    }

    public BaseApplication() {
        INSTANCE = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
