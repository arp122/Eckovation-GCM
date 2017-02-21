package com.example.arpit.eckovation_gcm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by arpit on 24-10-2015.
 */
public class SignUpActivity extends AppCompatActivity {
    Button btSignUp;
    Button btLogin;
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btSignUp=(Button)findViewById(R.id.btSignUp);
        btLogin=(Button)findViewById(R.id.btLogin);

        etUsername=(EditText)findViewById(R.id.etFriends);
        etPassword=(EditText)findViewById(R.id.etPassword);

        final ProgressDialog dlg = new ProgressDialog(SignUpActivity.this);
        dlg.setTitle("Please wait.");
        dlg.setMessage("Signing up.  Please wait.");

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.show();
                ParseUser user = new ParseUser();
                user.setUsername(etUsername.getText().toString());
                user.setPassword(etPassword.getText().toString());

                user.signUpInBackground(new SignUpCallback() {

                    @Override
                    public void done(ParseException e) {

                        if (e != null) {
                            // Show the error message
                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            dlg.dismiss();
                        } else {
                            // Start an intent for the dispatch activity
                            dlg.dismiss();
                            ParsePush.subscribeInBackground(etUsername.getText().toString());

                            Intent intent = new Intent(SignUpActivity.this, SendMessage.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}
