package com.example.alan_.proyectomoviles;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;


@IgnoreExtraProperties
public class Group {

    public int groupGrade;
    public String groupLetter;
    public ArrayList<String> groupStudents;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String groupId;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Group() {
    }

    public Group(int groupGrade, String groupLetter,  ArrayList<String> groupStudents) {
        this.groupGrade = groupGrade;
        this.groupLetter = groupLetter;
        this.groupStudents = groupStudents;
    }
}