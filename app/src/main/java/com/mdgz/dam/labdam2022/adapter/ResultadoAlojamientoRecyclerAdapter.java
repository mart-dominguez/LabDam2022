package com.mdgz.dam.labdam2022.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.AlojamientoItemBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ResultadoAlojamientoRecyclerAdapter extends RecyclerView.Adapter<ResultadoAlojamientoRecyclerAdapter.AlojamientoViewHolder> {
    private List<Alojamiento> _dataSet;

    public ResultadoAlojamientoRecyclerAdapter(List<Alojamiento> alojamientos){
        _dataSet = alojamientos;
    }

    @Override
    public ResultadoAlojamientoRecyclerAdapter.AlojamientoViewHolder onCreateViewHolder(ViewGroup parent, int type){
        AlojamientoItemBinding b = AlojamientoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        AlojamientoViewHolder vh = new AlojamientoViewHolder(b);
        return  vh;
    }
    @Override
    public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, final int position){
        Alojamiento alojamiento = _dataSet.get(position);
        alojamientoHolder.binding.cardTitle.setText(alojamiento.getTitulo());
        alojamientoHolder.binding.cardDescription.setText(alojamiento.getDescripcion());
//      alojamientoHolder.binding.thumbnail.setImageResource(R.drawable.hotel_placeholder);
        Picasso.get().load(alojamiento.getImageUrl()).placeholder(R.drawable.ic_baseline_house_24).into(alojamientoHolder.binding.thumbnail);

        alojamientoHolder.binding.capacidadText.setText(alojamiento.getCapacidad().toString());
        String precioBaseStr = Long.toString(Math.round(alojamiento.getPrecioBase()));
        alojamientoHolder.binding.costoText.setText(precioBaseStr);

        if(alojamiento instanceof Departamento){
            Departamento d = (Departamento) alojamiento;
            if(d.getTieneWifi())
            alojamientoHolder.binding.wifiItem.setVisibility(View.GONE);
        }
        else if(alojamiento instanceof Habitacion){
            Habitacion h = (Habitacion) alojamiento;
            if(h.getTieneEstacionamiento())
                alojamientoHolder.binding.estacionamientoItem.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount(){
        return _dataSet.size();
    }

    public static class AlojamientoViewHolder extends RecyclerView.ViewHolder{
        AlojamientoItemBinding binding;

        public AlojamientoViewHolder(AlojamientoItemBinding b){
            super(b.getRoot());
            binding = b;
        }
    }
}
