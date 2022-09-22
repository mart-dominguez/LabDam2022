package com.mdgz.dam.labdam2022.utilities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.ResultadoBusquedaFragmentDirections;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;

import java.util.List;

public class AlojamientoAdapter extends RecyclerView.Adapter<AlojamientoAdapter.AlojamientoViewHolder> {

    //Lista de alojamientos
    private List<Alojamiento> mDataset;

    //View Holder, encargado de "buscar los widgets"
    public static class AlojamientoViewHolder extends RecyclerView.ViewHolder{

        MaterialCardView card;
        TextView titulo, descripcion, txtMatrimonial, txtIndividual, txtPrecio, txtCantHab;
        ImageView imgMatrimonial, imgIndividual, imgEstacionamiento, imgCantHab, imgWifi;
        MaterialButton btnFavorito;

        public AlojamientoViewHolder(View v){
            super(v);
            card = v.findViewById(R.id.cardAlojamiento);
            titulo = v.findViewById(R.id.tituloCard);
            descripcion = v.findViewById(R.id.descripcionCard);
            txtMatrimonial = v.findViewById(R.id.txtMatrimonialCard);
            txtIndividual = v.findViewById(R.id.txtIndividualCard);
            txtPrecio = v.findViewById(R.id.txtPrecioCard);
            imgMatrimonial = v.findViewById(R.id.imgMatrimonialCard);
            imgIndividual = v.findViewById(R.id.imgIndividualCard);
            imgEstacionamiento = v.findViewById(R.id.imgEstacionamientoCard);
            imgCantHab = v.findViewById(R.id.imgCantHabCard);
            txtCantHab = v.findViewById(R.id.txtCantHabCard);
            imgWifi = v.findViewById(R.id.imgWifiCard);

            //Listener boton favorito
            btnFavorito = v.findViewById(R.id.btnFavoritoCard);
            btnFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Logica para agregar a favorito y desagregar de favorito
                    //...

                    btnFavorito.setIconResource(R.drawable.ic_favorite_filled_dark);
                }
            });

            //Listener card
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavDirections action = ResultadoBusquedaFragmentDirections.actionResultadoBusquedaFragmentToDetalleAlojamientoFragment();
                    Navigation.findNavController(v).navigate(action);
                }
            });
        }

    }

    //Constructor
    public AlojamientoAdapter(List<Alojamiento> myDataset){
        mDataset = myDataset;
    }

    //Metodo que se ejecuta una vez por cada fila que se visualiza
    @Override
    public AlojamientoAdapter.AlojamientoViewHolder onCreateViewHolder(ViewGroup prn, int tipo){
        View v = LayoutInflater.from(prn.getContext()).inflate(R.layout.fila_alojamiento, prn, false);
        return new AlojamientoViewHolder(v);
    }

    //Por cada fila que se desea visualizar, seteamos los datos (por sobre la existente)
    @Override
    public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, final int position){

        alojamientoHolder.btnFavorito.setTag(position); //Seteo para identificar a que alojamiento presiona como favorito
        alojamientoHolder.card.setTag(position);  //Seteo para identificar a que alojamiento presiono

        if(mDataset.get(position).getClass() == Habitacion.class){ //Si es habitacion

            Habitacion hab = (Habitacion) mDataset.get(position);
            alojamientoHolder.titulo.setText(hab.getTitulo()); //Titulo
            alojamientoHolder.descripcion.setText(hab.getDescripcion()); //Descripcion
            alojamientoHolder.txtPrecio.setText("$"+hab.getPrecioBase().toString()); //Precio

            //Camas matrimoniales
            alojamientoHolder.imgMatrimonial.setVisibility(View.VISIBLE);
            alojamientoHolder.txtMatrimonial.setVisibility(View.VISIBLE);
            alojamientoHolder.txtMatrimonial.setText(String.valueOf(hab.getCamasMatrimoniales()));

            //Camas individuales
            alojamientoHolder.imgIndividual.setVisibility(View.VISIBLE);
            alojamientoHolder.txtIndividual.setVisibility(View.VISIBLE);
            alojamientoHolder.txtIndividual.setText(String.valueOf(hab.getCamasIndividuales()));

            //Estacionamiento
            if(hab.getTieneEstacionamiento()) alojamientoHolder.imgEstacionamiento.setVisibility(View.VISIBLE);
            else alojamientoHolder.imgEstacionamiento.setVisibility(View.GONE);

            alojamientoHolder.imgWifi.setVisibility(View.GONE); //Wifi deshabilitado

            //Cant de hab deshabilitado
            alojamientoHolder.imgCantHab.setVisibility(View.GONE);
            alojamientoHolder.txtCantHab.setVisibility(View.GONE);

        }else{ //Si es departamento

            Departamento dep = (Departamento) mDataset.get(position);
            alojamientoHolder.titulo.setText(dep.getTitulo()); //Titulo
            alojamientoHolder.descripcion.setText(dep.getDescripcion()); //Descripcion
            alojamientoHolder.txtPrecio.setText("$"+dep.getPrecioBase().toString()); //Precio

            //Cant de hab
            alojamientoHolder.imgCantHab.setVisibility(View.VISIBLE);
            alojamientoHolder.txtCantHab.setVisibility(View.VISIBLE);
            alojamientoHolder.txtCantHab.setText(String.valueOf(dep.getCantidadHabitaciones()));

            //Wifi
            if(dep.getTieneWifi()) alojamientoHolder.imgWifi.setVisibility(View.VISIBLE);
            else alojamientoHolder.imgWifi.setVisibility(View.GONE);

            //Camas matrimoniales deshabilitadas
            alojamientoHolder.imgMatrimonial.setVisibility(View.GONE);
            alojamientoHolder.txtMatrimonial.setVisibility(View.GONE);

            //Camas individuales deshabilitadas
            alojamientoHolder.imgIndividual.setVisibility(View.GONE);
            alojamientoHolder.txtIndividual.setVisibility(View.GONE);

            alojamientoHolder.imgEstacionamiento.setVisibility(View.GONE); //Esatcionamiento deshabilitado

        }

    }


    @Override
    public int getItemCount(){
        return mDataset.size();
    }


}
