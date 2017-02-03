package com.example.android.earthquakerep;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        // Find the TextView with view ID magnitude
        TextView magTextView = (TextView) listItemView.findViewById(R.id.tv1) ;
        // Display the magnitude of the current earthquake in that TextView
        magTextView.setText(currentQuake.getMagnitude());

        // Find the TextView with view ID location
        TextView placeTextView = (TextView) listItemView.findViewById(R.id.tv2) ;
        TextView placeTwoTextView = (TextView) listItemView.findViewById(R.id.tv5) ;
        String location = currentQuake.getLocation() ;
        String stringPlaceHolder = "Near the" ;

        //Condition for splitting the Location String in two different textViews
        if ( location.contains("of") ){
            String[] separated =  location.split("of");
            placeTwoTextView.setText(separated[0] + "of");
            placeTextView.setText(separated[1].trim());
        } else{
            placeTwoTextView.setText(stringPlaceHolder);
            placeTextView.setText(currentQuake.getLocation());
        }

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentQuake.getTimeInMiliSeconds());

        // Find the TextView with view ID date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.tv3) ;
        // Format the date string (i.e. "Mar 3, 1984")
        String dateToDisplay = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateTextView.setText(dateToDisplay);

        // Find the TextView with view ID time
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.tv4);
        // Format the time string (i.e. "4:30PM")
        String timeToDisplay = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeTextView.setText(timeToDisplay);

        return listItemView ;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
