package com.example.alan_.proyectomoviles;


import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class User {

    //public String name;
    public String email;
    public String password;
    public boolean permiso;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String email, String password, boolean permiso) {
        this.email = email;
        this.password = password;
        this.permiso = permiso;
    }
}