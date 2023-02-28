package com.mdgz.dam.labdam2022;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdgz.dam.labdam2022.database.ReservaDataSource;
import com.mdgz.dam.labdam2022.database.room.ReservaRoomDataSource;
import com.mdgz.dam.labdam2022.databinding.FilaAlojamientoRecyclerBinding;
import com.mdgz.dam.labdam2022.databinding.FilaReservaRecyclerBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentVerFavoritosBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentVerReservasBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Reserva;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VerReservasFragment extends Fragment {

    FragmentVerReservasBinding binding;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentVerReservasBinding.inflate(inflater, container, false);

        binding.recyclerReservas.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerReservas.setLayoutManager(layoutManager);

        ReservaDataSource.RecuperarReservaCallback callback = ((exito, resultados) -> {
            if (exito) {
                System.out.println("RESULTADOS reservas" + resultados);

                RecyclerView.Adapter mAdapter = new ReservaRecyclerAdapter(getContext(), resultados);
                binding.recyclerReservas.setAdapter(mAdapter);
            }
        });

        ReservaRoomDataSource reservaRoomDataSource = new ReservaRoomDataSource(getContext());
        reservaRoomDataSource.recuperarReserva(callback);

        return binding.getRoot();
    }
    public static class ReservaRecyclerAdapter extends RecyclerView.Adapter<ReservaRecyclerAdapter.ReservaViewHolder> {
        private List<Reserva> mDataset;
        private Context mContext;

        public static class ReservaViewHolder extends RecyclerView.ViewHolder {
            //Aca van los atributos que queremos mostrar del alojamiento
            CardView card;
            TextView fechaIngreso;
            TextView fechaSalida;
            TextView precio;
            FilaReservaRecyclerBinding binding;

            public ReservaViewHolder(FilaReservaRecyclerBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
                //obtiene los elementos del xml de la tarjetita
                card = binding.cardReserva;
                fechaIngreso = binding.fechaEntrada;
                fechaSalida = binding.fechaSalida;
                precio = binding.monto;
            }
        }
        public ReservaRecyclerAdapter(Context context, List<Reserva> myDataset) {
            mDataset = myDataset;
            mContext = context;
        }
        @Override
        public ReservaRecyclerAdapter.ReservaViewHolder
        onCreateViewHolder(ViewGroup prn, int tipo) {
            // En lugar de inflar la vista manualmente, usamos la clase generada por ViewBinding para inflarla
            FilaReservaRecyclerBinding binding = FilaReservaRecyclerBinding.inflate(
                    LayoutInflater.from(prn.getContext()), prn, false);
            return new ReservaViewHolder(binding);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(ReservaViewHolder reservaHolder, final int position) {
            //Setea todos los atributos al holder de alojamiento
            Reserva reserva = mDataset.get(position);

            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            reservaHolder.fechaIngreso.setText(formatter.format(reserva.getFechaIngreso()));
            reservaHolder.fechaSalida.setText(formatter.format(reserva.getFechaEgreso()));
            reservaHolder.precio.setText(reserva.getMonto().toString());


        }
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }
}