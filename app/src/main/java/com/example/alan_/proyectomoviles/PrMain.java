package com.example.alan_.proyectomoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class PrMain extends AppCompatActivity {
    private ImageButton btnAsistencia, btnCalificaciones, btnGrupo, btnActividades, btnComentarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_main);
        btnAsistencia = (ImageButton) findViewById(R.id.asistencia_button);
        btnCalificaciones = (ImageButton) findViewById(R.id.calificaciones_button);
        btnGrupo = (ImageButton) findViewById(R.id.grupo_button);
        btnActividades = (ImageButton) findViewById(R.id.actividades_button);
        btnComentarios = (ImageButton) findViewById(R.id.comentarios_button);


        btnAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrMain.this, Asistencia.class));
            }
        });

        btnCalificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrMain.this, AssignmentActivity.class));
            }
        });

        btnGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrMain.this, AddGroup.class));
            }
        });

        btnActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrMain.this, calendariop.class));
            }
        });

        btnComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrMain.this, Asistencia.class));
            }
        });

    }
}



