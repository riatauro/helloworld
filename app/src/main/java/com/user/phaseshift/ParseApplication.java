package com.user.phaseshift;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, " bA2jSzNoYuZ5XOdcXzpC8LUhFoOLZvIXiRUlGS4Y", "SUgwli06qEgpFmQob5d56eFZsisbhSyMtdjDiGCD");


    }

}