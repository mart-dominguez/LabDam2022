package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class BusquedaFragment extends Fragment {

    private Spinner tipoAlojamiento, cantOcupantes, ciudad;
    private CheckBox wifi;
    private EditText precioMinimo, precioMaximo;
    private Button btnBuscar, btnLimpiar;

    public interface OnBuscarListener{
        public void buscar();
    }
    private OnBuscarListener listenerBuscar;

    @Override
    public void onAttach(@NonNull Context activity) {
        super.onAttach(activity);
        try {
            listenerBuscar = (OnBuscarListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement interface");
        }
    }

    public BusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_busqueda, container,false);
        btnBuscar = v.findViewById(R.id.btn_Buscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerBuscar.buscar();//A buscar hay que pasarle un conjunto de info a buscar
            }
        });
        return v;
    }
}