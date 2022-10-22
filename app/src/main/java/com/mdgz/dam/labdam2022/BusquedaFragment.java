package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.slider.RangeSlider;
import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.model.Ciudad;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class BusquedaFragment extends Fragment {

    FragmentBusquedaBinding binding;
    Spinner sCiudad;
    com.google.android.material.button.MaterialButtonToggleGroup btgTipo;
    EditText etPersonas;
    ToggleButton tbWifi;
    com.google.android.material.slider.RangeSlider rsRangoPrecio;
    Button bBuscar;
    Button bLimpiar;

    int cantidadP= -1;
    double minRango= -1;
    double maxRango = -1;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        binding = FragmentBusquedaBinding.inflate(inflater, container, false);
        tbWifi= binding.wifiTb;
        sCiudad= binding.sCiudad;
        btgTipo= binding.grupo;
        etPersonas= binding.cantpEdit;
        tbWifi= binding.wifiTb;
        rsRangoPrecio= binding.rangoPrecioRs;
        bBuscar= binding.buscarButton;
        bLimpiar= binding.limpiarButton;
        // meter todo en una funcion
        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
        ciudades.add(new Ciudad (1, "Santa Fe", "sfe"));
        ciudades.add(new Ciudad (2, "Rosario", "sfe"));
        ciudades.add(new Ciudad (3, "Buenos Aires", "sfe"));
        ArrayAdapter<Ciudad> adapter =new ArrayAdapter <Ciudad> (getContext(), android.R.layout.simple_spinner_dropdown_item, ciudades);
        sCiudad.setAdapter(adapter);
        view = binding.getRoot();
        return view;
    }
    binding.tbWifi.setOnClickListener(){};



//    private class InputValuesTextWatcher implements TextWatcher {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            if(CheckValidValuesOnInput()){
//                binding.buscarButton.setEnabled(true);
//            }
//            else{
//                binding.busquedaSpinner.setEnabled(false);
//            }
//        }
//
//
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//
//        }
//    }
//    protected Boolean CheckValidValuesOnInput(){
//        try {
//            cantidadP= Integer.parseInt(binding.cantpEdit.getText().toString());
//
//        }catch (Exception e){
//            cantidadP = -1;
//            return false;
//        }
//        try {
//            tasaEfecAnual = Double.parseDouble(binding.tasaEfectivaAnualEditText.getText().toString());
//        }catch (Exception e){
//            tasaEfecAnual = -1;
//            return false;
//        }
//        try {
//            capitalInvertir = Double.parseDouble(binding.capitalInvertirEditText.getText().toString());
//        }catch (Exception e){
//            capitalInvertir = -1;
//            return false;
//        }
//
//
//        if(tasaNomAnual <= 0 || tasaEfecAnual<= 0 || capitalInvertir <= 0){
//            tasaNomAnual = -1;
//            tasaEfecAnual = -1;
//            capitalInvertir = -1;
//            return false;
//        }
//
//        return true;
//    }

}