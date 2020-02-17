package com.infoappsolution.loadmaal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    LinearLayout submit;
    FirebaseDatabase database;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    EditText name, pass;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        name = findViewById(R.id.username);
        pass = findViewById(R.id.password);

        submit = findViewById(R.id.submit);
        register = findViewById(R.id.register);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = name.getText().toString();
                String password = pass.getText().toString();
                if (user_name.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Please enter your Name.", Toast.LENGTH_SHORT).show();
                } else if (password.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Please enter Password.", Toast.LENGTH_SHORT).show();
                } else {
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                                myRef = database.getReference("Users");
                                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {
                                        if (snapshot.hasChild(user_name)) {

                                            DatabaseReference username = myRef.child(user_name);
                                            DatabaseReference user_password = username.child("password");

                                            user_password.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    //Users users = dataSnapshot.getValue(Users.class);
                                                    String userpasswrdinfirebase = dataSnapshot.getValue(String.class);
                                                    if (userpasswrdinfirebase!=null && userpasswrdinfirebase.equalsIgnoreCase(password))
                                                    {
                                                        SharedPreferences.Editor editor = getSharedPreferences("MYPREF", MODE_PRIVATE).edit();
                                                        editor.putString("name", user_name);
                                                        editor.apply();
                                                        editor.commit();
                                                        Intent intent = new Intent(Login.this, UserType.class);
                                                        startActivity(intent);
                                                    }
                                                    else {
                                                        Toast.makeText(getApplicationContext(), "Please enter correct Password.", Toast.LENGTH_LONG).show();
                                                    }
                                                    // Log.i(TAG, dataSnapshot.child("ZNAME").getValue(String.class));
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {
                                                   // Log.w(TAG, "onCancelled", databaseError.toException());
                                                }
                                            });

                                        } else {
                                            Toast.makeText(getApplicationContext(), "Username not exist.", Toast.LENGTH_LONG).show();

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
