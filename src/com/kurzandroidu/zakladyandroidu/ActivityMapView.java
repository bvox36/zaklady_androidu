package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivityMapView extends Activity {

    private GoogleMap           mMap;
    private static final LatLng PRAGUE = new LatLng(50.08774, 14.42112);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.layout_mapview);
        Utils.getOverflowMenu(this);

        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        mMap.setMyLocationEnabled(true);
        mMap.addMarker(new MarkerOptions().position(PRAGUE).title("Praha"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(PRAGUE, 12));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu_map, menu);
        return true;
    }
}
