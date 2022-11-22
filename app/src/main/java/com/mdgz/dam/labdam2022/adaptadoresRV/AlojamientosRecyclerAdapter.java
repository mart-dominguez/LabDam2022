package com.mdgz.dam.labdam2022.adaptadoresRV;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.ResultadoBusquedaFragment;
import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;

import java.text.DecimalFormat;
import java.util.List;

public class AlojamientosRecyclerAdapter extends RecyclerView.Adapter<AlojamientosRecyclerAdapter.AlojamientosViewHolder> {

    private List<Alojamiento> dataAlojamientos ;

    public static class AlojamientosViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView titulo;
        TextView precio;
        ImageView imgTipo;
        ImageView imgFav;
        TextView capacidad;

        public AlojamientosViewHolder(@NonNull View itemView) {

            super(itemView);
            card = itemView.findViewById(R.id.card_view_alojamiento);
            titulo = itemView.findViewById(R.id.titulo_card_alojamiento);
            precio = itemView.findViewById(R.id.texto_precio);
            imgTipo=itemView.findViewById(R.id.imagen_tipo);
            imgFav= itemView.findViewById(R.id.boton_favorito);
            capacidad=itemView.findViewById(R.id.texto_capacidad);




        }
    }

    public AlojamientosRecyclerAdapter(List<Alojamiento> data) {
        dataAlojamientos=data;
    }

    @NonNull
    @Override
    public AlojamientosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_busqueda_alojamiento, parent, false);
        AlojamientosViewHolder vh = new AlojamientosViewHolder(v);
        return vh;}

    @Override
    public void onBindViewHolder(@NonNull AlojamientosViewHolder holder, int position) {
    Alojamiento alojamiento = dataAlojamientos.get(position);

    if(alojamiento.getClass().equals(Departamento.class))
        holder.imgTipo.setImageResource(R.drawable.ic_baseline_apartment_24);
    else holder.imgTipo.setImageResource(R.drawable.ic_baseline_hotel_24);

    holder.titulo.setText(alojamiento.getTitulo());
        DecimalFormat format= new DecimalFormat();
        format.setMaximumFractionDigits(2);
        holder.precio.setText(format.format(alojamiento.getPrecioBase()));
        holder.capacidad.setText((alojamiento.getCapacidad().toString()));

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle= new Bundle();
                bundle.putInt("id_alojamiento", alojamiento.getId());
                bundle.putString("titulo", alojamiento.getTitulo());
                bundle.putDouble("precio",alojamiento.getPrecioBase());
                bundle.putString("descripcion", alojamiento.getDescripcion());
                bundle.putInt("capacidad", alojamiento.getCapacidad());
                if(alojamiento.getClass().equals(Departamento.class)) {
                    bundle.putString("tipoAlojamiento", "Departamento");
                    bundle.putString("ubicacion", alojamiento.getUbicacion().toString());
                    bundle.putBoolean("wifi", ((Departamento) alojamiento).getTieneWifi());
                    bundle.putDouble("costoLimpieza", ((Departamento) alojamiento).getCostoLimpieza());
                    bundle.putInt("cantHabitaciones", ((Departamento) alojamiento).getCantidadHabitaciones());
                }
                else {
                    bundle.putString("tipoAlojamiento", "Hotel");
                    bundle.putString("ubicacion", ((Habitacion) alojamiento).getHotel().getUbicacion().toString());
                    bundle.putString("nombreHotel", ((Habitacion) alojamiento).getHotel().toString());
                    bundle.putInt("cantCamasInd", ((Habitacion) alojamiento).getCamasIndividuales());
                    bundle.putInt("cantCamasMat", ((Habitacion) alojamiento).getCamasMatrimoniales());
                    bundle.putBoolean("incluyeParking", ((Habitacion) alojamiento).getTieneEstacionamiento());
                }
                Navigation.findNavController(view).navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment,bundle);

            }
        });

    }


    @Override
    public int getItemCount() {
        return dataAlojamientos.size();
    }

}
