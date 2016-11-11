package com.example.heather.myapp;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Heather on 31/10/2016.
 */

public class MyApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
