package com.example.alan_.proyectomoviles;

import com.google.firebase.database.IgnoreExtraProperties;



@IgnoreExtraProperties
public class Group {

    public int groupNumber;
    public String groupLetter;
    public int numStudents;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Group() {
    }

    public Group(int groupNumber, String groupLetter, int numStudents) {
        this.groupNumber = groupNumber;
        this.groupLetter = groupLetter;
        this.numStudents = numStudents;
    }
}