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

    private String mUrl;

    /*
    *Constructs a new {@link earthquake} object
    @param magnitude is the magnitude (size) of the earthquake .
    @param location the city location of the earthquake
    @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the earthquake happened
     */
    public Earthquake (double magnitude , String location, long time , String url ){

        this.mMagnitude = magnitude ;
        this.mTimeInMiliSeconds = time ;
        this.mLocation = location;
        this.mUrl = url ;

    }

    /*
   * Returns the time in miliseconds of the earthquake
   */
    public long getTimeInMiliSeconds() {
        return mTimeInMiliSeconds;
    }

    /*
   * Returns the location of the earthquake
   */
    public String getLocation() {
        return mLocation;
    }

    /*
    * Returns the magnitude of the earthquake
    */
    public double getMagnitude() {
        return mMagnitude;
    }

    /*
    * Returns the url (Webpage information)
    */
    public String getUrl() {
        return mUrl;
    }

}
