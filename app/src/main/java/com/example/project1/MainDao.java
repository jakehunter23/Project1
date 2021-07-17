package com.example.project1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
 public interface MainDao {
    //insert query
    @Insert(onConflict= REPLACE)
    void insert(Maindata maindata);

    //dElete query
    @Delete
    void delete(Maindata maindata);

    //delete all query
    @Delete
    void reset(List<Maindata> maindata);

    //GET all data
    @Query("SELECT * FROM table_name")
    List<Maindata> getAll();









}
