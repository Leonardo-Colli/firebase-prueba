package com.example.firebase_prueba;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import fragments.ListarCitas;
import fragments.ListarHabitos;
import fragments.inicioFragment;

public class Contenedor extends AppCompatActivity implements ListarCitas.OnFragmentInteractionListener, ListarHabitos.OnFragmentInteractionListener {

    FragmentTransaction transaction;
    Fragment listarcitas,inicio,listarhabitos;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contenedor);
        listarcitas = new ListarCitas();
        listarhabitos = new ListarHabitos();
        inicio = new inicioFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorfragment,inicio).commit();

    }

    public void onFragmentInteraction(Uri uri){

    }

    public void cambio(View view) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.btncitas: transaction.replace(R.id.contenedorfragment, listarcitas).commit();
                break;
            case R.id.btnhabitos:transaction.replace(R.id.contenedorfragment, listarhabitos).commit();
                break;
            case R.id.btnpendientes:transaction.replace(R.id.contenedorfragment, inicio).commit();
        }

    }
}
