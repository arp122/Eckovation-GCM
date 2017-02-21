package com.example.arpit.eckovation_gcm;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by arpit on 21-10-2015.
 */
public class ParseSetup extends Application {

    public void onCreate() {
        Parse.initialize(this, "VvlRUptYmy8uTMauHmZaL9eGB4eqqGlg8wYDvGQC", "SjRUj1AdiCa9l77YtHDd1wh7hdQiNb5TcqsX1sQw");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
