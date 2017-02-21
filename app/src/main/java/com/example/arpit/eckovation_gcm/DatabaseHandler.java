package com.example.arpit.eckovation_gcm;

/**
 * Created by arpit on 24-10-2015.
 */

import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.database.sqlite.SQLiteQueryBuilder;
        import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper
{
    Context context;
    private static String makeTable = "CREATE TABLE IF NOT EXISTS main"+"" +
            " (id NUMBER,name TEXT, message TEXT,time TEXT)";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "new1234";

    public DatabaseHandler(Context context)
    {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Query", makeTable);

        db.execSQL(makeTable);

    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {


    }

    public void addDetails(String name,String message, String time)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values_main= new ContentValues();
        values_main.put("name", name);
        values_main.put("time",time);
        values_main.put("message", message);

        db.insert("main", null, values_main);
    }

    public List<Details> getDetails() throws SQLException
    {
        String query = "SELECT * FROM main order by time desc";
        SQLiteDatabase db = this.getWritableDatabase();
        List<Details> data =new ArrayList<Details>();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Details person = new Details();
                person.name = cursor.getString(cursor.getColumnIndex("name"));
                person.time = cursor.getString(cursor.getColumnIndex("time"));
                person.message = cursor.getString(cursor.getColumnIndex("message"));
                data.add(person);

            }
            while(cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
   public void deleteTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "DROP TABLE IF EXISTS main";
        db.execSQL(query1);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS  main");
        // Create tables again
        onCreate(db);
    }

}
