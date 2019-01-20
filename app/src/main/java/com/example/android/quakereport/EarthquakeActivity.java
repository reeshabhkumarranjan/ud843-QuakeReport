/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.loader.app.LoaderManager;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Quake>> {

    private ArrayList<Quake> quakes=new ArrayList<Quake>();
    private final String urlString="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    private static final int EARTHQUAKE_LOADER_ID=0;
    private TextView emptyView;
    private ProgressBar progressBar;

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    public Loader<ArrayList<Quake>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG,"TEST: onCreateLoader() called");
        return new QuakeLoader(EarthquakeActivity.this,urlString);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Quake>> loader, ArrayList<Quake> data) {
        this.quakes=data;
        updateUI();
        Log.i(LOG_TAG,"TEST: onLoadFinished() called");
        emptyView.setText("No earthquakes found.");
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Quake>> loader) {
        this.quakes=new ArrayList<Quake>();
        Log.i(LOG_TAG,"TEST: onLoaderReset() called");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
//        new EarthquakeTask().execute(urlString);
        getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID,null,this);
        Log.i(LOG_TAG,"TEST: Loader initialised.");
        ListView listView=findViewById(R.id.listView);
        emptyView=(TextView)findViewById(R.id.emptyView);
        listView.setEmptyView(emptyView);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

    }

    public void updateUI(){
        QuakeAdapter quakeAdapter=new QuakeAdapter(this,R.layout.quake_tile,quakes);
        ListView listView=findViewById(R.id.listView);
        listView.setAdapter(quakeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url=quakes.get(position).getUrl();
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
