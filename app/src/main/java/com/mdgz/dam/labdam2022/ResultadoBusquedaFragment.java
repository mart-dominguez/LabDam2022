package com.mdgz.dam.labdam2022;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;

import org.w3c.dom.Text;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultadoBusquedaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultadoBusquedaFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public ResultadoBusquedaFragment() {
        // Required empty public constructor
    }

    public static ResultadoBusquedaFragment newInstance(String param1, String param2) {
        ResultadoBusquedaFragment fragment = new ResultadoBusquedaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_resultado_busqueda, container, false);
        recyclerView = thisView.findViewById(R.id.recyclerAlojamiento);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AlojamientoRecyclerAdapter(new AlojamientoRepository().listaCiudades());
        recyclerView.setAdapter(mAdapter);


        return thisView;
    }

    public static class AlojamientoRecyclerAdapter extends RecyclerView.Adapter<AlojamientoRecyclerAdapter.AlojamientoViewHolder> {
        private List<Alojamiento> mDataset;
        public static class AlojamientoViewHolder extends RecyclerView.ViewHolder {
            //Aca van los atributos que queremos mostrar del alojamiento
            CardView card;
            TextView titulo;
            TextView capacidad;
            TextView precioBase;
            ImageView imgAloj;
            Button btnDetalle;

            public AlojamientoViewHolder(@NonNull View v) {
                super(v);
                //obtiene los elementos del xml de la tarjetita
                card = v.findViewById(R.id.cardAlojamiento);
                titulo = v.findViewById(R.id.txtTitulo);
                capacidad = v.findViewById(R.id.txtCapacidad);
                btnDetalle = v.findViewById(R.id.btnVerDetalle);
            }
        }
        public AlojamientoRecyclerAdapter(List<Alojamiento> myDataset) {
            mDataset = myDataset;
        }
        @Override
        public AlojamientoRecyclerAdapter.AlojamientoViewHolder
        onCreateViewHolder(ViewGroup prn, int tipo) {
            View v = LayoutInflater.from(prn.getContext())
                    .inflate(R.layout.fila_alojamiento_recycler, prn, false);

            AlojamientoViewHolder vh = new AlojamientoViewHolder(v);
            return vh;

        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, final int position) {
            //Setea todos los atributos al holder de alojamiento
            Alojamiento alojamiento = mDataset.get(position);
           // alojamientoHolder.imgAloj.setTag(position);
            alojamientoHolder.btnDetalle.setTag(position);

            alojamientoHolder.titulo.setText(alojamiento.getTitulo());
            alojamientoHolder.capacidad.setText(alojamiento.getCapacidad().toString());//ponerlo como string
            //alojamientoHolder.precioBase.setText(alojamiento.getPrecioBase().toString());//ponerlo como string
            //alojamientoHolder.imgAloj.setImageResource();//Buscar la imagen

        }
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }
}