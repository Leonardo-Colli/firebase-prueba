package com.example.firebase_prueba;

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
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class newActivity extends AppCompatActivity implements View.OnClickListener {
    EditText titulo;
    EditText status;
    EditText descripcion;
    EditText raiting;
    Button enviar;
    TextView fecha;
    TextView hora;
    Button btnlu, btnma,btnmi,btnju,btnvi,btnsa,btndo;
    Button bhora;
    FirebaseFirestore mfirestore;
    private FirebaseAnalytics db;
    private FirebaseAuth mAuth;
    private int time, minutos;
    private int lunes,martes,miercoles,jueves,viernes,sabado,domingo;
    private int blunes=1,bmartes=1,bmiercoles=1,bjueves=1,bviernes=1,bsabado=1,bdomingo=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newactivity);

        mfirestore = FirebaseFirestore.getInstance();

        titulo = findViewById(R.id.titulo);
        hora= findViewById(R.id.textHora);
        descripcion = findViewById(R.id.descripcion);

        enviar = findViewById(R.id.buttonSave);

        btnlu = findViewById(R.id.btnlu);
        btnma = findViewById(R.id.btnMa);
        btnmi = findViewById(R.id.btnMie);
        btnju = findViewById(R.id.btnJu);
        btnvi = findViewById(R.id.btnVi);
        btnsa = findViewById(R.id.btnSa);
        btndo = findViewById(R.id.btnDo);

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        newActivity.this,
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

        btnlu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnlu.setSelected(!btnlu.isSelected());
                if (btnlu.isSelected()) {
                    if(blunes==1){
                        lunes = 1;
                    }
                }else{
                        lunes = 0;
                }
            }
        });
        btnma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnma.setSelected(!btnma.isSelected());
                if (btnma.isSelected()) {
                    if(bmartes==1){
                        martes = 1;
                    }
                }else{
                        martes = 0;
                }

            }
        });
        btnmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnmi.setSelected(!btnmi.isSelected());
                if (btnmi.isSelected()) {
                    if(bmiercoles==1){
                        miercoles = 1;
                    }
                }else{
                        miercoles = 0;
                }

            }
        });
        btnju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnju.setSelected(!btnju.isSelected());
                if (btnju.isSelected()) {
                    if(bjueves==1){
                        jueves = 1;
                    }
                }else{
                        jueves = 0;
                }
            }
        });
        btnvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnvi.setSelected(!btnvi.isSelected());
                if (btnvi.isSelected()) {
                    if(bviernes==1){
                        viernes = 1;
                    }
                }else{
                        viernes = 0;
                }
            }
        });
        btnsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnsa.setSelected(!btnsa.isSelected());
                if (btnsa.isSelected()) {
                    if(bsabado==1){
                        sabado = 1;
                    }
                }else{
                    sabado = 0;
                }
            }
        });
        btndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btndo.setSelected(!btndo.isSelected());
                if (btndo.isSelected()) {
                    if(bdomingo==1){
                        domingo = 1;
                    }
                }else{
                    domingo = 0;
                }
            }
        });



        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creardatos();

            }
        });



    }


    private void creardatos(){

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        String title = titulo.getText().toString();
        String description = descripcion.getText().toString();
        String horaeve = hora.getText().toString();
        Map<String, Object> map = new HashMap<>();
        map.put("Titulo", title);
        map.put("Descripcion", description);
        map.put("Creado", thisDate);
        map.put("Hora", horaeve);
        map.put("lunes", lunes);
        map.put("martes", martes);
        map.put("miercoles", miercoles);
        map.put("jueves", jueves);
        map.put("viernes", viernes);
        map.put("sabado", sabado);
        map.put("domingo", domingo);
        mfirestore.collection("Actividad").document(" "+title).set(map);
        if (map!=null){
            Toast.makeText(this, "Evento agregado con exito", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Algo salio mal", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onClick(View view) {
        //lunes.setTextColor(Color.parseColor("#9E9E9E"));

        if (view==bhora){
            final Calendar calendario = Calendar.getInstance();
            time = calendario.get(Calendar.HOUR_OF_DAY);
            minutos = calendario.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    hora.setText(hourOfDay+":"+minute);
                }
            }, time,minutos, false);
            timePickerDialog.show();
        }
    }



}
