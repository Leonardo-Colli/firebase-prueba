package com.example.firebase_prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CitalistActivity extends AppCompatActivity {

    RecyclerView recyclerViewCitas;
    CitasAdapter mAdapter;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citalist);

        recyclerViewCitas = findViewById(R.id.recyclerCitas);
        recyclerViewCitas.setLayoutManager(new LinearLayoutManager(this));
        mFirestore = FirebaseFirestore.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/M/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        thisDate.getBytes().toString();
        Toast.makeText(this,"Citas para el dia de hoy "+thisDate, Toast.LENGTH_LONG).show();

        Query query = mFirestore.collection("Cita")
               .whereEqualTo("Fecha", thisDate).orderBy("Hora");
        FirestoreRecyclerOptions<Citas> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Citas>()
                .setQuery(query, Citas.class)
                .build();
        mAdapter = new CitasAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        recyclerViewCitas.setAdapter(mAdapter);


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