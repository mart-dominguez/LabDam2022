package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleAlojamientoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleAlojamientoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FragmentDetalleAlojamientoBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Alojamiento alojamiento;

    public DetalleAlojamientoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleAlojamientoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleAlojamientoFragment newInstance(String param1, String param2) {
        DetalleAlojamientoFragment fragment = new DetalleAlojamientoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            alojamiento = getArguments().getParcelable("alojamiento");
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetalleAlojamientoBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_detalle_alojamiento, container, false);
        TextView tituloText = binding.tituloText;
        TextView descripcionText = binding.descripcionText;thisView.findViewById(R.id.descripcionText);
        TextView capacidadText = binding.capacidadText;
        TextView precioText = binding.precioText;
        /* TOREAD
        https://www.geeksforgeeks.org/how-to-implement-google-map-inside-fragment-in-android/
         */
        tituloText.setText(alojamiento.getTitulo());
        descripcionText.setText(alojamiento.getDescripcion());
        capacidadText.setText(alojamiento.getCapacidad());
        precioText.setText(String.valueOf(alojamiento.getPrecioBase()));
        return thisView;
    }
}