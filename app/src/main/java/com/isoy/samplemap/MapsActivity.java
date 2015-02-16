package com.isoy.samplemap;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private EditText txtlong;
    private EditText txtlat;
    private Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded(0.0,0.0);
        txtlong = (EditText) findViewById(R.id.editTxtLat);
        txtlat = (EditText) findViewById(R.id.editTxtLong);
        btnGo = (Button) findViewById(R.id.buttonGo);
    }

    public void btnGoClicked(View v){
        //txtlong.getText();
        //txtlat.getText();
        Double lat = Double.parseDouble(txtlat.getText().toString());
        Double lon = Double.parseDouble(txtlong.getText().toString());
        if(lon>=0 && lat >= 0)
            setUpMap(lon,lat);
        else
            Toast.makeText(getApplicationContext(), "Input a Value for both fields",
                    Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded(Double lon, Double lat) {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap(lon,lat);
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap(Double lon, Double lat) {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings mapSettings = mMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);
        LatLng currLocation = new LatLng(lon, lat);
        Marker mrkCurrLocation = mMap.addMarker(new MarkerOptions()
        .position(currLocation)
        .title("Current Location")
        .snippet("The location you entered"));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(currLocation)
                .zoom(4)
                .bearing(70)
                .tilt(25)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
