package com.example.alan_.proyectomoviles;


import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class User {

    public String name;
    public String email;
    public boolean permiso;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email, boolean permiso) {
        this.name = name;
        this.email = email;
        this.permiso = permiso;
    }
}