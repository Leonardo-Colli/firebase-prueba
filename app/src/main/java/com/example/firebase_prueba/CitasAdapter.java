package com.example.firebase_prueba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class CitasAdapter  extends FirestoreRecyclerAdapter<Citas, CitasAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CitasAdapter(@NonNull FirestoreRecyclerOptions<Citas> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Citas citas) {
        holder.Titulo.setText(citas.getTitulo());
        holder.Hora.setText(citas.getHora());
    }



    @NonNull
    @Override
    public CitasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_citas, parent, false);
        return new CitasAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Titulo;
        TextView Hora;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Titulo = itemView.findViewById(R.id.titulo);
            Hora = itemView.findViewById(R.id.hora);

        }
    }
}
