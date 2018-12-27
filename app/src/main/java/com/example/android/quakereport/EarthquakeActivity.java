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
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ArrayList<Quake> quakes=new ArrayList<>();
        quakes=QueryUtils.extractQuakes();
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

        QuakeAdapter quakeAdapter=new QuakeAdapter(this,R.layout.quake_tile,quakes);
        ListView listView=findViewById(R.id.list);
        listView.setAdapter(quakeAdapter);


    }
}
