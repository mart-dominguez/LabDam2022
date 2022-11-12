package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;

public class DetalleAlojamientoFragment extends Fragment {
    private TextView titulo, descripcion, capacidad, precio;

    private FragmentDetalleAlojamientoBinding binding;
    private Alojamiento alojamiento;

    public DetalleAlojamientoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && !getArguments().isEmpty()) {
            alojamiento = getArguments().getParcelable("alojamiento");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetalleAlojamientoBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_detalle_alojamiento, container, false);

        titulo = thisView.findViewById(R.id.tituloText);
        descripcion = thisView.findViewById(R.id.descripcionText);
        capacidad = thisView.findViewById(R.id.capacidadText);
        precio = thisView.findViewById(R.id.precioText);

        /*
        TextView tituloText = binding.tituloText;
        TextView descripcionText = binding.descripcionText;thisView.findViewById(R.id.descripcionText);
        TextView capacidadText = binding.capacidadText;
        TextView precioText = binding.precioText;
         */


        /* TOREAD
        https://www.geeksforgeeks.org/how-to-implement-google-map-inside-fragment-in-android/
         */
        if (getArguments() != null && !getArguments().isEmpty()) { //TODO: Controlar esta comprobacion, capaz es innecesaria
            alojamiento = getArguments().getParcelable("alojamiento");
            titulo.setText(alojamiento.getTitulo());
            descripcion.setText(alojamiento.getDescripcion());
            capacidad.setText(String.valueOf(alojamiento.getCapacidad()));
            precio.setText(String.valueOf(alojamiento.getPrecioBase()));
        }else{
            Context context = getContext();
            CharSequence text = "ERROR, NO RECIBE LOS ARGUMENTOS";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
        return thisView;
    }
}