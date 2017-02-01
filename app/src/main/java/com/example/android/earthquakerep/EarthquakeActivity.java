package com.example.android.earthquakerep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);


        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake("2.5","San Francisco", "30-01-2017"));
        earthquakes.add(new Earthquake("2.5","London", "30-01-2017"));
        earthquakes.add(new Earthquake("2.5","Tokyo", "30-01-2017"));
        earthquakes.add(new Earthquake("2.5","Mexico City", "30-01-2017"));
        earthquakes.add(new Earthquake("2.5","Moscow", "30-01-2017"));
        earthquakes.add(new Earthquake("2.5","Rio de Janeiro", "30-01-2017"));
        earthquakes.add(new Earthquake("2.5", "Paris", "30-01-2017"));


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this , earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}
