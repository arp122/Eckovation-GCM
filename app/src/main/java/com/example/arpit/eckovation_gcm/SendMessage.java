package com.example.arpit.eckovation_gcm;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SendCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * Created by arpit on 24-10-2015.
 */
public class SendMessage extends AppCompatActivity implements View.OnClickListener {

    Button btSend;
    EditText etFriend;
    EditText etMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        btSend=(Button)findViewById(R.id.btSend);
        btSend.setOnClickListener(this);

        etFriend=(EditText)findViewById(R.id.etFriends);
        etMessage=(EditText)findViewById(R.id.etMessage);

        ParsePush.subscribeInBackground(ParseUser.getCurrentUser().getUsername());

    }

    @Override
    public void onClick(View view) {

        final ProgressDialog dlg = new ProgressDialog(SendMessage.this);
        dlg.setTitle("Please wait.");
        dlg.setMessage("Sending your message.");
        dlg.show();

        JSONObject jsonobj = new JSONObject();
        JSONObject obj = new JSONObject();

        try {
            obj.put("title", "Message From "+ParseUser.getCurrentUser().getUsername());
            obj.put("message", etMessage.getText().toString());
            jsonobj.put("is_background", false);
            jsonobj.put("data", obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ParsePush push = new ParsePush();
        push.setChannel(""+etFriend.getText().toString());
        push.setData(jsonobj);
        push.sendInBackground(new SendCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(SendMessage.this, e.getMessage(), Toast.LENGTH_LONG).show();

                } else {
                    dlg.dismiss();
                    Toast.makeText(SendMessage.this, "Message Sent", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            ParseUser.getCurrentUser().logOut();
            startActivity(new Intent(SendMessage.this, MainActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
