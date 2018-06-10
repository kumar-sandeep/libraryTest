package com.example.sandeep.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {

    private TextView emailView,nameView,mobileView,dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        emailView = (TextView)findViewById(R.id.email_view);
        nameView = (TextView)findViewById(R.id.name_view);
        mobileView = (TextView)findViewById(R.id.mobile_view);
        dateView = (TextView)findViewById(R.id.dob_view);
        getSupportActionBar().hide();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                if (userModel != null)
                {
                    emailView.setText(userModel.getEmail_id());
                    nameView.setText(userModel.getName());
                    mobileView.setText(userModel.getMobile_no());
                    dateView.setText(userModel.getDate());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("View", "Failed to read value.", error.toException());
            }
        });
    }
}
