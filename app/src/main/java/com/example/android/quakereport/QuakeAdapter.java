package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class QuakeAdapter extends ArrayAdapter {

    private ArrayList objects;

    public QuakeAdapter(@androidx.annotation.NonNull Context context, int resource, @androidx.annotation.NonNull List objects) {
        super(context, resource, objects);
        this.objects= (ArrayList) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View quakeView=convertView;

        if(quakeView==null){
            quakeView=LayoutInflater.from(getContext()).inflate(
                    R.layout.quake_tile,parent,false
            );
        }

        Quake quake= (Quake) objects.get(position);
        TextView magTv=quakeView.findViewById(R.id.magTv);
        TextView locationTv=quakeView.findViewById(R.id.locationTv);
        TextView dateTv=quakeView.findViewById(R.id.dateTv);

        magTv.setText(""+quake.getMagnitude());
        locationTv.setText(quake.getLocation());
        dateTv.setText(quake.getSimpleDate());

        return quakeView;
    }
}
