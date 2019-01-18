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

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    private ArrayList<Quake> quakes;
    private final String urlString="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private class EarthquakeTask extends AsyncTask<String,Void,ArrayList<Quake>>{

        @Override
        protected ArrayList<Quake> doInBackground(String... strings) {
            if(strings.length<1 || strings[0]==null){
                return null;
            }

            String urlString=strings[0];
            String jsonResponse;
            try {
                jsonResponse=QueryUtils.makeHttpRequest(new URL(urlString));
            } catch (MalformedURLException e) {
                System.err.println("Malformed URL");
                return null;
            }

            return QueryUtils.extractQuakes(jsonResponse);
        }

        @Override
        protected void onPostExecute(ArrayList<Quake> quakes) {
            EarthquakeActivity.this.quakes=quakes;
            EarthquakeActivity.this.updateUI();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        new EarthquakeTask().execute(urlString);
//        ArrayList<Quake> quakes=new ArrayList<>();
//        quakes=QueryUtils.extractQuakes();
/*
quakes.add(new Quake(8.5f,"Jamaica", (long) 100000000));
quakes.add(new Quake(5.6f,"Afghanistan", (long) 100000000));
quakes.add(new Quake(8.4f,"America", (long) 100000000));
quakes.add(new Quake(1.3f,"New York", (long) 1000000123));
quakes.add(new Quake(4.3f,"Thailand", (long) 1022000000));
quakes.add(new Quake(2.5f,"Washington", (long) 1320000000));
quakes.add(new Quake(2.6f,"Downtown", (long) 770000000));
quakes.add(new Quake(3.9f,"Gotham", (long) 560000000));
quakes.add(new Quake(6.7f,"Baker Street", (long) 300000000));
quakes.add(new Quake(3.1f,"Antarctica", (long) 880000000));
quakes.add(new Quake(0.5f,"Indonesia", (long) 1013300200));
quakes.add(new Quake(1.2f,"Malaysia", (long) 1013900090));
*/


    }

    public void updateUI(){
        QuakeAdapter quakeAdapter=new QuakeAdapter(this,R.layout.quake_tile,quakes);
        ListView listView=findViewById(R.id.list);
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
