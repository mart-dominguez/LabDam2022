package com.mdgz.dam.labdam2022.utilities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.CardAlojamientoBinding;
import com.mdgz.dam.labdam2022.databinding.CardAlojamientoExpandableBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;

import java.util.List;
import java.util.Random;

public class AlojamientoAdapter extends RecyclerView.Adapter<AlojamientoAdapter.AlojamientoViewHolder> {

    private List<Alojamiento> alojamientos;

    public AlojamientoAdapter(List<Alojamiento> alojamientos){
        this.alojamientos = alojamientos;
    }

    @NonNull
    @Override       //Metodo que se ejecuta una vez por cada fila que se visualiza
    public AlojamientoAdapter.AlojamientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int tipo){
        return new AlojamientoViewHolder(CardAlojamientoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }


    @Override
    public int getItemCount(){
        return alojamientos.size();
    }

    //Por cada fila que se desea visualizar, seteamos los datos (por sobre la existente)
    @Override
    public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, final int position)
    {
        alojamientoHolder.bind(alojamientos.get(position));

    }

    //View Holder, encargado de "buscar los widgets"
    public static class AlojamientoViewHolder extends RecyclerView.ViewHolder
    {
        CardAlojamientoBinding binding;
        CardAlojamientoExpandableBinding expandableBinding;

        public AlojamientoViewHolder(CardAlojamientoBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
            expandableBinding = binding.caExpandable;
        }

        public void bind(Alojamiento alojamiento)
        {
            binding.caFavoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Logica para agregar a favorito y desagregar de favorito
                    //...
                    binding.caFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_filled_24);
                }
            });

            cardBehaviour();
            cardFormat(alojamiento);
        }


        private void cardBehaviour()
        {
            binding.caExpandirButton.setText("Expandir");
            binding.caExpandable.getRoot().setVisibility(ConstraintLayout.GONE);
            binding.caExpandirButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(binding.caExpandable.getRoot().getVisibility() == ConstraintLayout.VISIBLE)
                    {
                        binding.caExpandable.getRoot().setVisibility(ConstraintLayout.GONE);
                        binding.caExpandirButton.setText("Expandir");
                    }
                    else
                    {
                        binding.caExpandable.getRoot().setVisibility(ConstraintLayout.VISIBLE);
                        binding.caExpandirButton.setText("Cerrar");
                    }
                }
            });
        }

        private void cardFormat(Alojamiento alojamiento)
        {
            Random r = new Random();
            float rating = r.nextFloat() * 5;
            int resenias = r.nextInt(1000);
            int banio = r.nextInt(3);
            int cocina = r.nextInt(3);
            int pieza = r.nextInt(6);
            int wifi = r.nextInt(2);
            int desayuno = r.nextInt(2);
            int cochera = r.nextInt(2);
            int abierto = r.nextInt(2);



            if(alojamiento.getClass().equals(Departamento.class))
            {
                binding.caTipoImage.setImageResource(R.drawable.ic_outline_house_24);
                binding.caTipoCaption.setText("Departamento");
            } else {
                binding.caTipoImage.setImageResource(R.drawable.ic_baseline_apartment_24);
                binding.caTipoCaption.setText("Hotel");
            }

            binding.caTitle.setText(alojamiento.getTitulo());
            binding.caRating.setRating(rating);
            binding.caRatingCaption.setText(Utilities.round2(rating) + " (" + resenias + ")");
            binding.caPrecioText.setText("$"+ alojamiento.getPrecioBase() + "/noche");
            binding.caDescription.setText(alojamiento.getDescripcion());


            expandableBinding.caePersonasValor.setText(alojamiento.getCapacidad() + " ocupantes");
            expandableBinding.caeBanioValor.setText("" + banio);
            expandableBinding.caeCocinaValor.setText("" + cocina);
            expandableBinding.caePiezaValor.setText("" + pieza);

            String valor = wifi == 1 ? "Sin Wi-Fi" : "Con Wi-Fi";
            expandableBinding.caeWifiValor.setText(valor);
            valor = desayuno == 1 ? "Sin desayuno" : "Con desayuno";
            expandableBinding.caeDesayunoValor.setText(valor);
            valor = cochera == 1 ? "Sin cochera" : "Con cochera";
            expandableBinding.caeCocheraValor.setText(valor);
            valor = abierto == 1 ? "Cerrado" : "Abierto ahora";
            expandableBinding.caeSituacionTitle.setText(valor);

        }

    }


}
