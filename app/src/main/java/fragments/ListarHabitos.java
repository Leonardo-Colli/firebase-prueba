package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.firebase_prueba.Citas;
import com.example.firebase_prueba.CitasAdapter;
import com.example.firebase_prueba.Eventos;
import com.example.firebase_prueba.EventsAdapter;
import com.example.firebase_prueba.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListarHabitos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarHabitos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Eventos> Listarcitas;
    RecyclerView recyclercitas;
    EventsAdapter mAdapter;
    FirebaseFirestore mFirestore;

    public ListarHabitos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListarHabitos.
     */
    // TODO: Rename and change types and number of parameters
    public static ListarHabitos newInstance(String param1, String param2) {
        ListarHabitos fragment = new ListarHabitos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_listar_todo, container, false);
        Listarcitas = new ArrayList<>();
        recyclercitas = vista.findViewById(R.id.mostrarcitas);
        recyclercitas.setLayoutManager(new LinearLayoutManager(getContext()));

        mFirestore = FirebaseFirestore.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        Toast.makeText(getContext(),"Actividades para el dia de hoy "+dayOfTheWeek, Toast.LENGTH_LONG).show();

        Query query = mFirestore.collection("Actividad")
                .whereEqualTo(dayOfTheWeek, 1).orderBy("Hora");
        FirestoreRecyclerOptions<Eventos> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Eventos>()
                .setQuery(query, Eventos.class)
                .build();
        mAdapter = new EventsAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        recyclercitas.setAdapter(mAdapter);

        return vista;
    }
    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    public interface OnFragmentInteractionListener {
    }
}