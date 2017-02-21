package com.example.arpit.eckovation_gcm;

/**
 * Created by arpit on 24-10-2015.
 */

public class Details
{
    int id;
    String name;
    String message;
    String time;

    public Details()
    {

    }
    public Details(int slno, String name, String message, String time) {
        id = slno + 1;
        this.name = name;
        this.message = message;
         this.time = time;
    }

    public String getName(){
        return this.name;
    }
    public String getMessge(){
        return this.message;
    }
    public String getTime(){
        return this.time;
    }


}

