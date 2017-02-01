package com.example.android.earthquakerep;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Paviliondm4 on 1/31/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private Earthquake currentQuake;

    public EarthquakeAdapter (Activity context, ArrayList<Earthquake> earthquake ){
        super(context,0 , earthquake);

    }

    @NonNull
    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_view, parent, false);
        }

        currentQuake = getItem(position);


        TextView magTextView = (TextView) listItemView.findViewById(R.id.tv1) ;
        magTextView.setText(currentQuake.getMagnitude());

        TextView placeTextView = (TextView) listItemView.findViewById(R.id.tv2) ;
        placeTextView.setText(currentQuake.getLocation());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.tv3) ;
        dateTextView.setText(currentQuake.getDate());


        return listItemView ;
    }
}
