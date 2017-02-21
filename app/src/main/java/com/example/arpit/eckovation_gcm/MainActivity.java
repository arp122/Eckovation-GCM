package com.example.arpit.eckovation_gcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // Check if there is current user info
        if (ParseUser.getCurrentUser() != null) {
            // Start an intent for the logged in activity
            startActivity(new Intent(this, SendMessage.class));
            this.finish();
        } else {
            // Start and intent for the logged out activity
            startActivity(new Intent(this, SignUpActivity.class));
            this.finish();
        }
    }
}
