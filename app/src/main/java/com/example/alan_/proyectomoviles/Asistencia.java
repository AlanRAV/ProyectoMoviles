package com.example.alan_.proyectomoviles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Asistencia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuactivity, menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
            // lo ideal aquí sería hacer un intent para abrir una nueva clase como lo siguiente

           // Log.i("ActionBar", "Settings!");
            //Intent about = new Intent(getApplicationContext(), PrMain.class);
            //startActivity(about);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
