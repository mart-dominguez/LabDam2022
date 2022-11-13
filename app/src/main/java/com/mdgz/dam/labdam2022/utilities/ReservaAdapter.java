package com.mdgz.dam.labdam2022.utilities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.CardAlojamientoBinding;
import com.mdgz.dam.labdam2022.databinding.CardAlojamientoExpandableBinding;
import com.mdgz.dam.labdam2022.databinding.ReservaDataBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.repositorios.FavoritoRepository;
import com.mdgz.dam.labdam2022.repositorios.UsuarioRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>
{
    private List<Reserva> reservas = new ArrayList<>();

    @NonNull
    @Override       //Metodo que se ejecuta una vez por cada fila que se visualiza
    public ReservaAdapter.ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int tipo)
    {
        return new ReservaAdapter.ReservaViewHolder(ReservaDataBinding.inflate(LayoutInflater.from(parent.getContext())));
    }


    @Override
    public int getItemCount(){
        return this.reservas.size();
    }

    //Por cada fila que se desea visualizar, seteamos los datos (por sobre la existente)
    @Override
    public void onBindViewHolder(ReservaAdapter.ReservaViewHolder reservaHolder, final int position)
    {
        //Se obtiene el holder con la vista inflada, y se le aplica la logica para setear los nuevos datos
        reservaHolder.bind(this.reservas.get(position));

    }

    //View Holder, encargado de "buscar los widgets"
    public static class ReservaViewHolder extends RecyclerView.ViewHolder
    {
        private ReservaDataBinding binding;
        private static final Format formatter = new SimpleDateFormat("yyyy-MM-dd");

        //Constructor
        public ReservaViewHolder(ReservaDataBinding binding) //Recibe la vista inflada
        {
            super(binding.getRoot());
            this.binding = binding;
        }

        //1. Seteo de los "nuevos" datos del alojamiento de la fila asociada al holder
        public void bind(Reserva reserva)
        {
            String fechaIngreso = formatter.format(reserva.getFechaIngreso());
            String fechaEgreso = formatter.format(reserva.getFechaSalida());

            LocalDateTime date1 = reserva.getFechaIngreso().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime date2 = reserva.getFechaSalida().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            long dias = ChronoUnit.DAYS.between(date1,date2);

            String precio = "$" + Utilities.round2(reserva.getAlojamiento().getPrecioBase() * dias);

            binding.rdTitle.setText(reserva.getAlojamiento().getTitulo());
            binding.rdDescription.setText(reserva.getAlojamiento().getDescripcion());
            binding.rdFechaIngresoChip.setText(fechaIngreso);
            binding.rdFechaEgresoChip.setText(fechaEgreso);
            binding.rdPrecioText.setText(precio);

        }

    }


    public void setData(List<Reserva> reservas)
    {
        this.reservas.clear();
        this.reservas.addAll(reservas);
        notifyDataSetChanged();
    }
}
