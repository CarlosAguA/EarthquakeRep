package com.example.android.earthquakerep;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
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
        // Format the magnitude to show 1 decimal place
        String output = formatMagnitude(currentQuake.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magTextView.setText(output);

        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentQuake.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor) ;
        // magnitudeCircle.setColor(ContextCompat.getColor(getContext() , R.color.magnitude1) ) ;


        // Find the TextView with view ID location
        TextView placeTextView = (TextView) listItemView.findViewById(R.id.tv2) ;
        TextView placeTwoTextView = (TextView) listItemView.findViewById(R.id.tv5) ;
        String location = currentQuake.getLocation() ;
        String stringPlaceHolder = getContext().getString(R.string.near_the) ;

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

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor (double magnitude) {

        int color ;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {

            case 0:
                color = R.color.magnitude1;
                break;
            case 1:
                color = R.color.magnitude1;
                break;
            case 2:
                color = R.color.magnitude2;
                break;
            case 3:
                color = R.color.magnitude3;
                break;
            case 4:
                color = R.color.magnitude4;
                break;
            case 5:
                color = R.color.magnitude5;
                break;
            case 6:
                color = R.color.magnitude6;
                break;
            case 7:
                color = R.color.magnitude7;
                break;
            case 8:
                color = R.color.magnitude8;
                break;
            case 9:
                color = R.color.magnitude9;
                break;
            case 10:
                color = R.color.magnitude9;
                break;
            default :
                color = R.color.magnitude10plus;
                break;

        }

        /*
        Note on color values: In Java code, you can refer to the colors that you defined in the colors.xml
         file using the color resource ID such as R.color.magnitude1, R.color.magnitude2. You still need to
         convert the color resource ID into a color integer value though. Example:
         */
        return ContextCompat.getColor(getContext(),color);
    }



}
