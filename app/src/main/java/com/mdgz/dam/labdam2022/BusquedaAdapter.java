package com.mdgz.dam.labdam2022;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.model.BusquedaDTO;

import java.util.List;

public class BusquedaAdapter extends RecyclerView.Adapter<BusquedaAdapter.BusquedaViewHolder> {

    private List<BusquedaDTO> busquedasList;

    public BusquedaAdapter(List<BusquedaDTO> busquedasList) {
        this.busquedasList = busquedasList;
    }

    @NonNull
    @Override
    public BusquedaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_busqueda, parent, false);
        return new BusquedaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BusquedaViewHolder holder, int position) {
        BusquedaDTO busqueda = busquedasList.get(position);
        holder.tipoAlojamiento.setText("Tipo de alojamiento: " + busqueda.getTipoAlojamiento());
        holder.cantOcupantes.setText("Cantida de ocupantes: " +String.valueOf(busqueda.getCantOcupantes()));
        holder.wifi.setText(busqueda.getWifi() ? "Wifi: Sí" : "Wifi: No");
        holder.precioMin.setText("Precio mínimo: " +String.valueOf(busqueda.getPrecioMin()));
        holder.precioMax.setText("Precio máximo: "+String.valueOf(busqueda.getPrecioMax()));
        holder.ciudad.setText("Ciudad: "+ busqueda.getCiudad());
        holder.timestampInicio.setText("Timestamp (ms): " +String.valueOf(busqueda.getTimestampInicio()));
        holder.tiempoBusqueda.setText("Tiempo de busqueda (ms): " + String.valueOf(busqueda.getTiempoBusqueda()));
        holder.cantidadResultados.setText("Cantidad de resultados: "+ String.valueOf(busqueda.getCantidadResultados()));
    }

    @Override
    public int getItemCount() {
        return busquedasList.size();
    }

    public class BusquedaViewHolder extends RecyclerView.ViewHolder {
        public TextView tipoAlojamiento, cantOcupantes, wifi, precioMin, precioMax, ciudad, timestampInicio, tiempoBusqueda, cantidadResultados;

        public BusquedaViewHolder(View view) {
            super(view);
            tipoAlojamiento = view.findViewById(R.id.tv_tipo_alojamiento);
            cantOcupantes = view.findViewById(R.id.tv_cant_ocupantes);
            wifi = view.findViewById(R.id.tv_wifi);
            precioMin = view.findViewById(R.id.tv_precio_min);
            precioMax = view.findViewById(R.id.tv_precio_max);
            ciudad = view.findViewById(R.id.tv_ciudad);
            timestampInicio = view.findViewById(R.id.tv_timestamp_inicio);
            tiempoBusqueda = view.findViewById(R.id.tv_tiempo_busqueda);
            cantidadResultados = view.findViewById(R.id.tv_cantidad_resultados);
        }
    }
}
