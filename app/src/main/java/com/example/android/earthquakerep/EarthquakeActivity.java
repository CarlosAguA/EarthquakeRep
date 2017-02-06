package com.example.android.earthquakerep;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);


        /*
        * Modify the EarthquakeActivity to call QueryUtils.extractEarthquakes()
        * to get a list of Earthquake objects from the JSON response
        */
        // Create a fake list of earthquakes.
        final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        final EarthquakeAdapter adapter = new EarthquakeAdapter(this , earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);


        //Add a clickListener for the list Items of the list.
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


                //Create object instance from Earthquake class and get the position of the clickedItem from the aray
                Earthquake quake = adapter.getItem(position);
                Log.v("Earthquake Activity", "Current quake: " + quake.toString() );

                //Get the assigned url query from the list item. Add feature to earthquake class
                //modifyint queryUtils file
                Log.v("Earthquake Activity", "Quake URL: " + quake.getUrl() );
                String url = quake.getUrl() ;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
    }

}


