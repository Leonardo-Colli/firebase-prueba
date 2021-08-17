package com.example.firebase_prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.ObjectInputStream;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventlistActivity extends AppCompatActivity {

    RecyclerView recyclerViewEventos;
    EventsAdapter mAdapter;
    FirebaseFirestore mFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citalist);
        recyclerViewEventos = findViewById(R.id.recyclerCitas);
        recyclerViewEventos.setLayoutManager(new LinearLayoutManager(this));
        mFirestore = FirebaseFirestore.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        Toast.makeText(this,"Actividades para el dia de hoy "+dayOfTheWeek, Toast.LENGTH_LONG).show();

        Query query = mFirestore.collection("Actividad")
                    .whereEqualTo(dayOfTheWeek, 1).orderBy("Hora");
            FirestoreRecyclerOptions<Eventos> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Eventos>()
                    .setQuery(query, Eventos.class)
                    .build();
            mAdapter = new EventsAdapter(firestoreRecyclerOptions);
            mAdapter.notifyDataSetChanged();
            recyclerViewEventos.setAdapter(mAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}