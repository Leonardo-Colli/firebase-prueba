package com.example.firebase_prueba;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class mostrar extends AppCompatActivity {
    TextView titulo,hora;
    FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        titulo = findViewById(R.id.titulo);
        hora = findViewById(R.id.hora);
        mfirestore = FirebaseFirestore.getInstance();
        mostrardatos();
    }
    public void mostrardatos(){
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        thisDate.getBytes().toString();
        Toast.makeText(this, thisDate, Toast.LENGTH_LONG).show();
        mfirestore.collection("Actividad")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot document : queryDocumentSnapshots){
                    titulo.append("\n "+document.getString("Titulo"));
                }
            }
        });
    }

}

