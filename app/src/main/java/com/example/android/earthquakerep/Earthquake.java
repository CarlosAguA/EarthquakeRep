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
    private String mMagnitude ;

    /*
    *Constructs a new {@link earthquake} object
    @param magnitude is the magnitude (size) of the earthquake .
    @param location the city location of the earthquake
    @param date is the date the earthquake happened.
     */
    public Earthquake (String magnitude , String location, String date ){

        this.mMagnitude = magnitude ;
        this.mDate = date ;
        this.mLocation = location;

    }

    /*
    * Returns the magnitude of the earthquake
    */
    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(String magnitude) {
        this.mMagnitude = magnitude;
    }

}
