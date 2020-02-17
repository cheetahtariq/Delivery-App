package com.infoappsolution.loadmaal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class Splash extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences prefs = getSharedPreferences("MYPREF", MODE_PRIVATE);
                String name = prefs.getString("name", "");//"No name defined" is the default value.
                String usertype = prefs.getString("usertype", "");//"No name defined" is the default value.

                if (name.equalsIgnoreCase("")) {
                    Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                } else if (usertype.equalsIgnoreCase("make")) {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(Splash.this, MapsActivity2.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                } else if (usertype.equalsIgnoreCase("take")) {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(Splash.this, TrackDelivery.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);

    }

}
