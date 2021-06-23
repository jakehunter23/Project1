package com.example.project1;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Add database entities
@Database(entities = {Maindata.class},version = 2,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    //create database instance
    private static RoomDB database;

    //define database  Name;
    private static String DATABASE_NAME="database";

    public synchronized static RoomDB getInstance(Context context){
        //check condition
        if(database==null){
            //when databse is null
            //initaliaze database

            database= Room.databaseBuilder(context.getApplicationContext()
                            ,RoomDB.class,DATABASE_NAME)
                        .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return database;
    }

    //create DAO
    public abstract MainDao mainDao();
}
