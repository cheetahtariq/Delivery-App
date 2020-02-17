package com.infoappsolution.loadmaal;

import com.google.android.gms.maps.model.LatLng;

public class Users {
    public String username,number,password;
    LatLng latLng;
    public Users() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users( String number, String password, LatLng latLng) {
        this.number = number;
        this.password = password;
        this.latLng = latLng;
    }


}
