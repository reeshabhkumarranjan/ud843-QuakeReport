package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import androidx.loader.content.AsyncTaskLoader;

public class QuakeLoader extends AsyncTaskLoader<ArrayList<Quake>> {

    private String urlString;
    private static final String LOG_TAG=QuakeLoader.class.getName();

    public QuakeLoader(@NonNull Context context, String urlString) {
        super(context);
        this.urlString=urlString;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.i(LOG_TAG,"TEST: onStartLoading() called");
    }

    @Nullable
    @Override
    public ArrayList<Quake> loadInBackground() {

        String jsonResponse;
        try {
            jsonResponse=QueryUtils.makeHttpRequest(new URL(urlString));
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL");
            return null;
        }

        Log.i(LOG_TAG,"TEST: loadInBackground() called");

        return QueryUtils.extractQuakes(jsonResponse);
    }


}
