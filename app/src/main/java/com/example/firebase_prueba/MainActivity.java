package com.example.firebase_prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button agregar, agregarcita, verTodo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregar = findViewById(R.id.add);
        agregarcita = findViewById(R.id.btn_newCita);
        verTodo = findViewById(R.id.btnListadoEventos);


        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, newActivity.class);
                startActivity(intent);
            }
        });


         agregarcita.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this, NewCita.class);
                 startActivity(intent);
             }
         });



         verTodo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Contenedor.class);
                startActivity(intent);

             }
         });


    }

}