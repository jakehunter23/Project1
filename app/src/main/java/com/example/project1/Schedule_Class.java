package com.example.project1;

import android.widget.Button;
import android.widget.EditText;

public class Schedule_Class {

    String title, location, description,time;
    String id;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Schedule_Class(String id, String title, String location, String description, String time) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.time = time;
        this.id=id;
    }
}
