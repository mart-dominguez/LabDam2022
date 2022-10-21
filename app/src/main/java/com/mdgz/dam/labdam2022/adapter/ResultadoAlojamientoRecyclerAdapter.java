package com.mdgz.dam.labdam2022.adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.AlojamientoItemBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ResultadoAlojamientoRecyclerAdapter extends RecyclerView.Adapter<ResultadoAlojamientoRecyclerAdapter.AlojamientoViewHolder> {
    private List<Alojamiento> _dataSet;
    private List<Boolean> favoritos = new ArrayList<Boolean>();

    public ResultadoAlojamientoRecyclerAdapter(List<Alojamiento> alojamientos) {
        _dataSet = alojamientos;
        for (int i = 0; i < _dataSet.size(); i++) {
            favoritos.add(false);
        }
    }

    @Override
    public ResultadoAlojamientoRecyclerAdapter.AlojamientoViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        AlojamientoItemBinding b = AlojamientoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        AlojamientoViewHolder vh = new AlojamientoViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, final int position) {
        Alojamiento alojamiento = _dataSet.get(position);
        Boolean isFav = favoritos.get(position);

        SetFavImage(alojamientoHolder, isFav);

        alojamientoHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("alojamiento_id", alojamiento.getId());
                Navigation.findNavController(view).navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment, bundle);
            }
        });

        alojamientoHolder.binding.favoritoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                Boolean isFavAfterClick = SwitchFav(alojamientoHolder.getBindingAdapterPosition());
                SetFavImage(alojamientoHolder, isFavAfterClick);
            }
        });

        //Popular datos estaticos del cardview
        FillStatic(alojamientoHolder, alojamiento);

        if (alojamiento instanceof Departamento) {
            Departamento d = (Departamento) alojamiento;
            if (d.getTieneWifi())
                alojamientoHolder.binding.wifiItem.setVisibility(View.GONE);
        } else if (alojamiento instanceof Habitacion) {
            Habitacion h = (Habitacion) alojamiento;
            if (h.getTieneEstacionamiento())
                alojamientoHolder.binding.estacionamientoItem.setVisibility(View.GONE);
        }
    }

    private void FillStatic(AlojamientoViewHolder alojamientoHolder, Alojamiento alojamiento) {
        alojamientoHolder.binding.cardTitle.setText(alojamiento.getTitulo());
        alojamientoHolder.binding.cardDescription.setText(alojamiento.getDescripcion());
        Picasso.get().load(alojamiento.getImageUrl()).placeholder(R.drawable.ic_baseline_house_24).into(alojamientoHolder.binding.thumbnail);
        alojamientoHolder.binding.capacidadText.setText(alojamiento.getCapacidad().toString());
        String precioBaseStr = Long.toString(Math.round(alojamiento.getPrecioBase()));
        alojamientoHolder.binding.costoText.setText(precioBaseStr);
    }

    @Override
    public int getItemCount() {
        return _dataSet.size();
    }

    public static class AlojamientoViewHolder extends RecyclerView.ViewHolder {
        AlojamientoItemBinding binding;
        RelativeLayout itemFavorito;


        public AlojamientoViewHolder(AlojamientoItemBinding b) {
            super(b.getRoot());
            binding = b;
            itemFavorito = binding.favoritoItem;
        }
    }

    void SetFavImage(AlojamientoViewHolder alojamientoHolder, Boolean isFav){
        if(isFav){
            alojamientoHolder.binding.favoritoImage.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
        else{
            alojamientoHolder.binding.favoritoImage.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }
    }

    Boolean SwitchFav(int position){
        Boolean value = !(favoritos.get(position));
        favoritos.set(position, value);
        return  value;
    }

}
