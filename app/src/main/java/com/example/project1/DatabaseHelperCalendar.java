package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelperCalendar extends SQLiteOpenHelper {

    private Context context;
    private static final String DATBASE_NAME = "Calendar.db";
    private static final int VERSION = 3;
//    public static final String TABLE_NAME="my_Calendar";
//    public static final String COLUMN_ID="_id";
//    public static final String COLUMN_TITLE="Calendar_Title";
//    public static final String COLUMN_LOCATIONN="Calendar_location";
//    public static final String COLUMN_TIME="Calendar_Time";


    public DatabaseHelperCalendar(@Nullable Context context) {
        super(context, DATBASE_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE my_Calendar (_id integer primary key autoincrement," +
                " Calendar_title text," +
                " Calendar_location text," +
                " Calendar_date DATE," +
                " Calendar_Time text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS my_Calendar";
        db.execSQL(query);
        onCreate(db);
    }

    //method for inserting data into database

    void addData(String Calendar_title, String Calendar_location, String Calendar_date, String Calendar_Time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Calendar_title", Calendar_title);
        cv.put("Calendar_location", Calendar_location);
        cv.put("Calendar_date", Calendar_date);
        cv.put("Calendar_Time", Calendar_Time);
        long result = db.insert("my_Calendar", null, cv);
        if (result == -1) {
            Log.e("Error","-1");
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("Inserted","1");
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }

        //db.execSQL("DROP TABLE IF EXISTS "+"my_Calendar");
    }

    //delete single entry
    void deleteSingleRow(String _id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("my_Calendar", "_id=?", new String[]{_id});
        if(result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    //for fetching the data
    public Cursor readData() {
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
        Log.e("Date ","is: "+modifiedDate);
        String query = "select * from my_Calendar where Calendar_date='"+modifiedDate+"'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }



}
