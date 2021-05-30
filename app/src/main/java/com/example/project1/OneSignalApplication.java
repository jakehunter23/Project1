package com.example.project1;

import android.app.Application;

import com.onesignal.OneSignal;

public class OneSignalApplication extends Application {
    private static final String ONESIGNAL_APP_ID = "c8937fe5-9250-4c14-8fe4-870629ec49ff";
    public void onCreate() {
        super.onCreate();
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}
