package com.infoappsolution.loadmaal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class UserType extends AppCompatActivity {

    RelativeLayout makeDelivery, takedelivery;
    String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        makeDelivery = findViewById(R.id.makedelivery);
        takedelivery = findViewById(R.id.need_vehical);

        try {
            SharedPreferences prefs = getSharedPreferences("MYPREF", MODE_PRIVATE);
             name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
        } catch (Exception e) {
            e.printStackTrace();
        }


        makeDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getSharedPreferences("MYPREF", MODE_PRIVATE).edit();
                editor.putString("name", name);
                editor.putString("usertype", "make");
                editor.apply();
                editor.commit();
                Intent intent = new Intent(UserType.this, MapsActivity2.class);
                startActivity(intent);
            }
        });
        takedelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("MYPREF", MODE_PRIVATE).edit();
                editor.putString("name", name);
                editor.putString("usertype", "take");
                editor.apply();
                editor.commit();
                Intent intent = new Intent(UserType.this, TrackDelivery.class);
                startActivity(intent);
            }
        });
    }
}
