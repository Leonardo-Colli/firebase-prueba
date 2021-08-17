package com.example.firebase_prueba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class EventsAdapter extends FirestoreRecyclerAdapter<Eventos, EventsAdapter.ViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public EventsAdapter(@NonNull FirestoreRecyclerOptions<Eventos> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Eventos eventos) {
        holder.Titulo.setText(eventos.getTitulo());
        holder.Hora.setText(eventos.getHora());



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_citas, parent, false);
        return new ViewHolder(view);
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
