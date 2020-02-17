package com.infoappsolution.loadmaal;

import com.google.android.gms.maps.model.LatLng;

public class LocationModel {
    public LatLng latLng;

    public LatLng getLatLng() {
        return latLng;
    }

    public LocationModel() {
    }

    public LocationModel(LatLng latLng) {
        this.latLng = latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
