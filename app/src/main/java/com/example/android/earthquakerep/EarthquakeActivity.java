package com.example.android.earthquakerep;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    HttpRequest urlConnection ;
    /** URL to query the USGS dataset for earthquake information */
    private static final String USGS_REQUEST_URL =
            " http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";

    ArrayList<Earthquake> earthquakes ;

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

       earthquakes = new ArrayList<>();

        urlConnection = new HttpRequest();
        urlConnection.execute(USGS_REQUEST_URL) ;

        /*
        * Modify the EarthquakeActivity to call QueryUtils.extractEarthquakes()
        * to get a list of Earthquake objects from the JSON response
        */
        // Create a fake list of earthquakes.
       // final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();

        /*
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        final EarthquakeAdapter adapter = new EarthquakeAdapter(this , earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        */

        /*
        //Add a clickListener for the list Items of the list.
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                //Create object instance from Earthquake class and get the position of the clickedItem from the aray
                Earthquake quake = adapter.getItem(position);
                Log.v("Earthquake Activity", "Current quake: " + adapter.getItem(position) );

                //Get the assigned url query from the list item. Add feature to earthquake class
                //modifyint queryUtils file
                Log.v("Earthquake Activity", "Quake URL: " + quake.getUrl() );
                String url = quake.getUrl() ;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        */
    }

    private class HttpRequest extends AsyncTask <String, Void , ArrayList<Earthquake>  >{

        @Override
        protected ArrayList<Earthquake> doInBackground(String... strings) {

            if (strings.length < 1 || strings[0] == null){
                return null;
            }

            URL url = createUrl(USGS_REQUEST_URL); // Pass url String and Return URL object from the method

            // Perform HTTP request with the provided URL and get a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
                Log.e (LOG_TAG , "It was not possible to connect to the server", e) ;
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
             earthquakes = extractFeatureFromJson(jsonResponse);

            return earthquakes ;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {

            // Find a reference to the {@link ListView} in the layout
            ListView earthquakeListView = (ListView) findViewById(R.id.list);

            // Create a new {@link ArrayAdapter} of earthquakes
            final EarthquakeAdapter adapter = new EarthquakeAdapter( EarthquakeActivity.this , earthquakes);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(adapter);

        }
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200 ) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            // TODO: Handle the exception
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e ) ;

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link Earthquake} object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */
    private ArrayList<Earthquake> extractFeatureFromJson(String earthquakeJSON) {
        try {
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
            JSONArray featureArray = baseJsonResponse.getJSONArray("features");

            // If there are results in the features array
            if (featureArray.length() > 0) {

                for (int i = 0 ; i < featureArray.length() ; i++) {
                    // Extract out the first feature (which is an earthquake)
                    JSONObject firstFeature = featureArray.getJSONObject(i);
                    JSONObject properties = firstFeature.getJSONObject("properties");
                    double mag = properties.getDouble("mag");
                    String place = properties.getString("place");
                    String url = properties.getString("url");
                    long time = properties.getLong("time");

                    earthquakes.add(new Earthquake(mag, place, time, url));

                }
                // Create a new {@link Event} object
                return earthquakes ;
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return null;
    }



}


