package com.example.firebase_prueba;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewCita extends AppCompatActivity {

    EditText titulo;
    EditText descripcion;
    TextView hora;
    TextView fecha;
    Button enviar;

    private int time, minutos;
    FirebaseFirestore mfirestore;
    private FirebaseAnalytics db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cita);
        mfirestore = FirebaseFirestore.getInstance();

        titulo = findViewById(R.id.titulo);
        descripcion = findViewById(R.id.descripcion);

        hora= findViewById(R.id.textHora);
        fecha = findViewById(R.id.textFecha);
        enviar = findViewById(R.id.buttonSave);

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        NewCita.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                time = hourOfDay;
                                minutos = minute;
                                String tiempo =  time+":"+minutos;
                                SimpleDateFormat f24hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24hours.parse(tiempo);
                                    SimpleDateFormat f12hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    hora.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(time, minutos);
                timePickerDialog.show();
            }
        });

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int anio = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(NewCita.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String fechas = dayOfMonth + "/"+(month+1)+"/"+year;
                        fecha.setText(fechas);
                    }
                },anio,mes,dia);
                dpd.show();
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creardatos();

            }
        });
    }

    private void creardatos() {
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        String title = titulo.getText().toString();
        String description = descripcion.getText().toString();
        String horaci = hora.getText().toString();
        String fechaci = fecha.getText().toString();
        Map<String, Object> map = new HashMap<>();
        map.put("Titulo", title);
        map.put("Descripcion", description);
        map.put("Creado", thisDate);
        map.put("Hora", horaci);
        map.put("Fecha", fechaci);

        mfirestore.collection("Cita").document(" "+title).set(map);
        if (map!=null){
            Toast.makeText(this, "Evento agregado con exito", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Algo salio mal", Toast.LENGTH_LONG).show();
        }
    }


}