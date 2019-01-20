package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import androidx.loader.content.AsyncTaskLoader;

public class QuakeLoader extends AsyncTaskLoader<ArrayList<Quake>> {

    private String urlString;

    public QuakeLoader(@NonNull Context context, String urlString) {
        super(context);
        this.urlString=urlString;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
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

        return QueryUtils.extractQuakes(jsonResponse);
    }


}
