package com.infoappsolution.loadmaal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    TextView loginIntent;
    LinearLayout submit;
    EditText username, number, password, cpassword;
    FirebaseDatabase database;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginIntent = findViewById(R.id.login);
        submit = findViewById(R.id.submit);
        username = findViewById(R.id.name);
        number = findViewById(R.id.number);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);

        database = FirebaseDatabase.getInstance();

        loginIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String mo_mumber = number.getText().toString();
                String pass = password.getText().toString();
                String cpass = cpassword.getText().toString();

                if (name.equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter your Name.", Toast.LENGTH_SHORT).show();
                }
                else if (mo_mumber.equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter Mobile Number.", Toast.LENGTH_SHORT).show();

                }
                else if (pass.equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter Password.", Toast.LENGTH_SHORT).show();

                }
                else if (cpass.equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter confirm Password.", Toast.LENGTH_SHORT).show();

                }
                else if (!pass.equalsIgnoreCase(cpass))
                {
                    Toast.makeText(getApplicationContext(),"Please enter correct Confirm Password.", Toast.LENGTH_SHORT).show();

                }
                else {
                    myRef = database.getReference("Users");
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.hasChild(name)) {
                                // run some code
                                Toast.makeText(getApplicationContext(),"Username already exist,Please enter another Username.",Toast.LENGTH_LONG).show();
                            }
                            else {
                                writeNewUser(name, mo_mumber, pass);
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    // Read from the database
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            //String value = dataSnapshot.getValue(String.class);
                            //Log.d(TAG, "Value is: " + value);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w(TAG, "Failed to read value.", error.toException());
                        }
                    });

                   // myRef.setValue("Hello, World!");
                    /* myRef = database.getReference("Users");
                    myRef.setValue("Hello, World!");*/
//                    Intent intent = new Intent(MainActivity.this, UserType.class);
//                    startActivity(intent);
                }

            }
        });
    }

    private void writeNewUser(String name, String number, String password) {
        Users user = new Users(number, password,null);
        myRef.child(name).setValue(user);
    }
}
