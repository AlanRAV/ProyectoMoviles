package com.example.alan_.proyectomoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Calificaciones extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView assDetails;
    private EditText inputAssName, inputAssGrade, inputAssDate;
    private Button btnSaveAssignement;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private Button dltAssignment;



    //  private String userId;
    //private String courseId;
    private  String assignmentId;
    //   private  String courseId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        String cID = getIntent().getStringExtra("COURSE_ID");

        //Toast.makeText(this,cID, Toast.LENGTH_SHORT).show();
        //Log.i("aaaaaaaaaaaaaaaaaaaaaa", cID );

        // Displaying toolbar icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        assDetails = (TextView) findViewById(R.id.txt_assignment);
        inputAssName = (EditText) findViewById(R.id.assName);
        inputAssGrade = (EditText) findViewById(R.id.assGrade);
       // inputAssDate = (EditText) findViewById(R.id.assDate);

        //dltAssignment = (Button) findViewById(R.id.btn_deleteAssignment);


        btnSaveAssignement = (Button) findViewById(R.id.btn_saveAssignment);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("courses/"+cID).child("assignments");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");

        // app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });

        // Save / update the user
        btnSaveAssignement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String assignmentName = inputAssName.getText().toString();
                String assignmentGrade = inputAssGrade.getText().toString();
                long assignmentTimeMil = 0;
                String assignmentDate = null;

                // Check for already existed userId
                if (TextUtils.isEmpty(assignmentId)) {
                    createAssignment(assignmentName, assignmentGrade, assignmentTimeMil, assignmentDate );
                } else {
                    updateAssignment(assignmentName, assignmentGrade, assignmentTimeMil, assignmentDate);
                }
            }
        });

        dltAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String assignmentName = inputAssName.getText().toString();
                String assignmentGrade = inputAssGrade.getText().toString();
                String assignmentDate = inputAssDate.getText().toString();


                deleteAssignment(assignmentName, assignmentGrade, assignmentDate);

            }
        });

        toggleButton();
    }

    // Changing button text
    private void toggleButton() {
        if (TextUtils.isEmpty(assignmentId)) {
            btnSaveAssignement.setText("Save Assignment");
        } else {
            btnSaveAssignement.setText("Update");
        }
    }


    /* private void createCourse(String courseTittle, String courseDay, String courseTime) {
         // TODO
         // In real apps this userId should be fetched
         // by implementing firebase auth
         if (TextUtils.isEmpty(courseId)) {
             courseId = mFirebaseDatabase.push().getKey();
         }

         Course course = new Course(courseTittle, courseDay, courseTime);

         mFirebaseDatabase.child(courseId).setValue(course);

         addCourseChangeListener();
     }
 */
    private void createAssignment(String assignmentName, String assignmentGrade, long assignmentTimeMil, String assignmentDate) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(assignmentId)) {
            assignmentId = mFirebaseDatabase.push().getKey();
        }

      //  Assignment assignment = new Assignment(assignmentName, assignmentGrade, assignmentTimeMil, assignmentDate);

       // mFirebaseDatabase.child(assignmentId).setValue(assignment);

        addAssignmentChangeListener();
    }

    /**
     * User data change listener
     */
    private void addAssignmentChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(assignmentId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Assignment assignment = dataSnapshot.getValue(Assignment.class);

                // Check for null
                if (assignment == null) {
                    Log.e(TAG, "Assignment data is null!");
                    return;
                }

                Log.e(TAG, "Assignment data is changed!" + assignment.assignmentName + ", " + assignment.assignmentGrade + ", "+ assignment.assignmentTimeMil+ ", " + assignment.assignmentDate);

                // Display newly updated name and email
                assDetails.setText(assignment.assignmentName + ", " + assignment.assignmentGrade + ", " +assignment.assignmentTimeMil+ ", " + assignment.assignmentDate);

                // clear edit text
                inputAssName.setText("");
                inputAssGrade.setText("");
                inputAssDate.setText("");


                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateAssignment(String assignmentName, String assignmentGrade,long assignmentTimeMil, String assignmentDate) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(assignmentName))
            mFirebaseDatabase.child(assignmentId).child("assignmentName").setValue(assignmentName);

        if (!TextUtils.isEmpty(assignmentGrade))
            mFirebaseDatabase.child(assignmentId).child("assignmentGrade").setValue(assignmentGrade);
        if (!TextUtils.isEmpty(assignmentDate))
            mFirebaseDatabase.child(assignmentId).child("assignmentDueDate").setValue(assignmentDate);


    }

    private void deleteAssignment(String assignmentName, String assignmentGrade, String assignmentDate) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(assignmentName))
            mFirebaseDatabase.child(assignmentId).child("assignmentName").setValue(null);

        if (!TextUtils.isEmpty(assignmentGrade))
            mFirebaseDatabase.child(assignmentId).child("assignmentGrade").setValue(null);
        if (!TextUtils.isEmpty(assignmentDate))
            mFirebaseDatabase.child(assignmentId).child("assignmentDueDate").setValue(null);


    }


  /*  public void findItem (View view){
        String child = "courseTittle";
        String course = "Moviles";
        Query query =mFirebaseDatabase.orderByChild(child).equalTo(course);
        query.addChildEventListener(chi);

    }*/
}