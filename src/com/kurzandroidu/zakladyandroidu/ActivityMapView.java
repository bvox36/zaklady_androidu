package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivityMapView extends Activity {

    private GoogleMap            mMap;
    private static final LatLng  PRAGUE = new LatLng(50.08774, 14.42112);
    private static final LatLng  BEROUN = new LatLng(49.96239, 14.06517);
    private AutoCompleteTextView mAddress;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_mapview);
        mAddress = (AutoCompleteTextView) findViewById(R.id.textViewAddress);
        mAddress.setAdapter(new AddressAutocompleteAdapter(this));
        Utils.getOverflowMenu(this);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        mMap.setMyLocationEnabled(true);
        mMap.addMarker(new MarkerOptions().position(PRAGUE).title("Praha"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(PRAGUE, 12));

        mMap.setInfoWindowAdapter(new InfoWindowAdapter() {

            @Override
            public View getInfoContents(Marker marker) {
                TextView v = new TextView(ActivityMapView.this);
                v.setText("InfoWindow: " + marker.getPosition());
                return v;
            }

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }
        });

        DrawPathAsyncTask drawPathAsyncTask = new DrawPathAsyncTask();
        String distanceUrl = GoogleDirections.JSONParser.makeURLForDistanceAPI(
                PRAGUE.latitude, PRAGUE.longitude, BEROUN.latitude,
                BEROUN.longitude);
        drawPathAsyncTask.execute(distanceUrl);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu_map, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionNormalMap:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.actionSatelliteMap:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.actionTerrainMap:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // --------------------------------------------------------------------------------------- 
    private class DrawPathAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String json = GoogleDirections.JSONParser
                    .getJSONStrFromUrl(params[0]);
            return json;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result != null) {
                GoogleDirections.JSONParser.drawPathOnMap(mMap, result);
            }
        }
    }
}
