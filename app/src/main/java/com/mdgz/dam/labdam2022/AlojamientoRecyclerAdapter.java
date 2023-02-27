package com.mdgz.dam.labdam2022;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.databinding.FilaAlojamientoRecyclerBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;

import java.util.List;

public class AlojamientoRecyclerAdapter extends RecyclerView.Adapter<AlojamientoRecyclerAdapter.AlojamientoViewHolder> {
    private List<Alojamiento> mDataset;
    private Context mContext;
    boolean withButton = true;


    public static class AlojamientoViewHolder extends RecyclerView.ViewHolder {
        //Aca van los atributos que queremos mostrar del alojamiento
        CardView card;
        TextView titulo;
        TextView capacidad;
        TextView precioBase;
        ImageView imgAloj;
        Button btnDetalle;
        FilaAlojamientoRecyclerBinding binding;


        public AlojamientoViewHolder(FilaAlojamientoRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            //obtiene los elementos del xml de la tarjetita
            card = binding.cardAlojamiento;
            titulo = binding.txtTitulo;
            capacidad = binding.txtCapacidad;
            precioBase = binding.txtPrecioBase;
            btnDetalle = binding.btnVerDetalle;
        }
    }
    public AlojamientoRecyclerAdapter(Context context, List<Alojamiento> myDataset, boolean withButton) {
        mDataset = myDataset;
        mContext = context;
        this.withButton = withButton;
    }
    @Override
    public AlojamientoRecyclerAdapter.AlojamientoViewHolder
    onCreateViewHolder(ViewGroup prn, int tipo) {
        // En lugar de inflar la vista manualmente, usamos la clase generada por ViewBinding para inflarla
        FilaAlojamientoRecyclerBinding binding = FilaAlojamientoRecyclerBinding.inflate(
                LayoutInflater.from(prn.getContext()), prn, false);
        return new AlojamientoViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, final int position) {
        //Setea todos los atributos al holder de alojamiento
        Alojamiento alojamiento = mDataset.get(position);
        // alojamientoHolder.imgAloj.setTag(position);
        alojamientoHolder.btnDetalle.setTag(position);
        alojamientoHolder.titulo.setText(alojamiento.getTitulo());
        alojamientoHolder.capacidad.setText(alojamiento.getCapacidad().toString());
        alojamientoHolder.precioBase.setText(alojamiento.getPrecioBase().toString());
        //alojamientoHolder.imgAloj.setImageResource();//Buscar la imagen
        if (!withButton) alojamientoHolder.btnDetalle.setVisibility(View.GONE);
        alojamientoHolder.btnDetalle.setOnClickListener(e->{
            Bundle bundle = new Bundle();
            bundle.putParcelable("alojamiento", (Parcelable) alojamiento);
            FragmentActivity activity = (FragmentActivity) mContext;
            NavController navController = NavHostFragment.findNavController(activity.getSupportFragmentManager().getPrimaryNavigationFragment());
            navController.navigate(R.id.action_global_detalleAlojamientoFragment, bundle);
        });


    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
