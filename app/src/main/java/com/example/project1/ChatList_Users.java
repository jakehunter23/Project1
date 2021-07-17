package com.example.project1;

public class ChatList_Users {

    String username;
    String email;
    String id;

    public String getid() {
        return id;
    }

    public void setUid(String uid) {
        id = uid;
    }

    public ChatList_Users(){

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ChatList_Users(String name, String email) {
        this.username = name;
        this.email = email;
    }
}
