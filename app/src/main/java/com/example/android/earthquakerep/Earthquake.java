package com.example.android.earthquakerep;

/**
 * Created by Paviliondm4 on 1/31/2017.
 */

public class Earthquake {

    /*Date of the earthquake*/
    private String mDate ;

    /*Location of the earthquake*/
    private String mLocation ;

    /*Magnitude of the earthquake */
    private double mMagnitude ;

    private long mTimeInMiliSeconds ;

    /*
    *Constructs a new {@link earthquake} object
    @param magnitude is the magnitude (size) of the earthquake .
    @param location the city location of the earthquake
    @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the earthquake happened
     */
    public Earthquake (double magnitude , String location, long time){

        this.mMagnitude = magnitude ;
        this.mTimeInMiliSeconds = time ;
        this.mLocation = location;

    }

    public long getTimeInMiliSeconds() {
        return mTimeInMiliSeconds;
    }

    public String getLocation() {
        return mLocation;
    }

    /*
    * Returns the magnitude of the earthquake
    */
    public double getMagnitude() {
        return mMagnitude;
    }


}
