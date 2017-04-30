package com.example.alan_.proyectomoviles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Asistencia extends AppCompatActivity {
    private static final String TAG = Asistencia.class.getSimpleName();
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String groupId;
    private EditText inputGroupLetter2, inputGroupGrade2;
    ArrayList<String> list = new ArrayList<String>();
    private Button btnSaveAssistency, btnSearchGroup;
    ListView ver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);
        inputGroupGrade2 = (EditText) findViewById(R.id.textViewLetra);
        inputGroupLetter2 = (EditText) findViewById(R.id.txtGrupoLetter);
        btnSaveAssistency = (Button) findViewById(R.id.buttonSaveAssistency);
        btnSearchGroup = (Button) findViewById(R.id.buttonSearch);



        ArrayList<String> list = new ArrayList<String>();
        //llenar de la lista

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'courses' node
        mFirebaseDatabase = mFirebaseInstance.getReference("groups");

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
        list.add("prueba");



        btnSearchGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "juan-195@live.com.mx");
                intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
                intent.setData(Uri.parse("mailto:juan-195@live.com.mx")); // or just "mailto:" for blank
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                startActivity(intent);
                addGroupChangeListener();


            }
        });

        AlumnosAdapter adapter = new AlumnosAdapter(list,this);
        ver =(ListView)findViewById(R.id.alumnosv);
        ver.setAdapter(adapter);


    }




    private void addGroupChangeListener() {
        // User data change listener
       // groupId = mFirebaseDatabase.getKey();
        //if (groupId.ch

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Group group = dataSnapshot.getValue(Group.class);

                // Check for null
                int grado = Integer.parseInt(inputGroupGrade2.getText().toString());
                String letra =  inputGroupLetter2.getText().toString();
                if ((group.groupGrade == grado)) //
                // && (group.groupLetter == letra))
                 {
                    for (int i=0; i<group.groupStudents.size(); i++ ) {
                        list.add(group.groupStudents.get(i));
                        list.add("hola");

                    }
                    Log.e(TAG, "Group data is null!");
                    return;
                }

                Log.e(TAG, "Group data is changed!" + group.groupGrade + ", " + group.groupLetter + ", " + group.groupStudents);

                // Display newly updated name and email
                // crsDetails.setText(course.courseTittle + ", " + course.courseDay + ", " + course.courseTime);

                // clear edit text
                //inputTitle.setText("");
                //inputDay.setText("");
                //inputTime.setText("");


                //toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read group", error.toException());
            }
        });
    }


}
