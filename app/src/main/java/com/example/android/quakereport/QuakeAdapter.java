package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

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
        TextView baseLocationTv=quakeView.findViewById(R.id.baseLocationTv);
        TextView locationOffsetTv=quakeView.findViewById(R.id.locationOffsetTv);
        TextView dateTv=quakeView.findViewById(R.id.dateTv);
        TextView timeTv=quakeView.findViewById(R.id.timeTv);

        magTv.setText(""+quake.getSimpleMagnitude());
        baseLocationTv.setText(quake.getBaseLocation());
        locationOffsetTv.setText(quake.getLocationOffset());
        dateTv.setText(quake.getSimpleDate());
        timeTv.setText(quake.getSimpleTime());

        // Setting the color of the background of the magnitude circle.
        GradientDrawable magnitudeCircle=(GradientDrawable)magTv.getBackground();
        int magnitudeColor=ContextCompat.getColor(getContext(),getMagnitudeColorResourceID(quake.getMagnitude()));
        magnitudeCircle.setColor(magnitudeColor);

        return quakeView;
    }

    private int getMagnitudeColorResourceID(double magnitude){

        switch ((int)magnitude){

            case 0:
            case 1:
                return R.color.magnitude1;
            case 2:
                return R.color.magnitude2;
            case 3:
                return R.color.magnitude3;
            case 4:
                return R.color.magnitude4;
            case 5:
                return R.color.magnitude5;
            case 6:
                return R.color.magnitude6;
            case 7:
                return R.color.magnitude7;
            case 8:
                return R.color.magnitude8;
            case 9:
                return R.color.magnitude9;
            case 10:
            default:
                return R.color.magnitude10plus;
        }
    }
}
