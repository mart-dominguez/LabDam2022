package com.mdgz.dam.labdam2022;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.model.BusquedaDTO;
import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder> {

    private List<Reserva> reservasList;

    public ReservaAdapter(List<Reserva> reservasList) {
        this.reservasList = reservasList;
    }

    @NonNull
    @Override
    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_reserva_recycler, parent, false);
        return new ReservaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaViewHolder holder, int position) {
        Reserva reserva = reservasList.get(position);

        holder.fechaIngreso.setText("Fecha de ingreso: " + reserva.getFechaIngreso());
        holder.fechaEgreso.setText("Fecha de ingreso: " + reserva.getFechaEgreso());
        holder.precio.setText("Precio: " + reserva.getMonto());

    }

    @Override
    public int getItemCount() {
        return reservasList.size();
    }

    public class ReservaViewHolder extends RecyclerView.ViewHolder {
        public TextView fechaIngreso, fechaEgreso, precio;

        public ReservaViewHolder(View view) {
            super(view);
            fechaIngreso = view.findViewById(R.id.fechaEntrada);
            fechaEgreso = view.findViewById(R.id.fechaSalida);
            precio = view.findViewById(R.id.monto);
        }
    }
}
