package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

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
import java.util.Collections;

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            //FALTA PONER LA CIUDAD Y EL BOTON TIPO
            //int iCiudad =adapter.getPosition(bundle.getCiudad("ciudad"));
            //sCiudad.setSelection(iCiudad);
            etPersonas.setText(bundle.getString("capacidad"));
            cantidadP = Integer.parseInt(etPersonas.getText().toString()); //chequear
            //btgTipo.setSelected(bundle.getInt("tipo"));
            tbWifi.setChecked(bundle.getBoolean("wifi"));
            rsRangoPrecio.setValues(bundle.getFloat("minRango"), bundle.getFloat("maxRango"));
        }

        bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamar fragmento de resultado y pasarlo los datos del filtro.
                Bundle filtro = new Bundle();
                filtro.putLong("ciudad", sCiudad.getSelectedItemId());
                filtro.putInt("tipo", btgTipo.getCheckedButtonId());
                filtro.putString("capacidad", etPersonas.getText().toString());
                filtro.putBoolean("wifi", tbWifi.isChecked());
                filtro.putFloat("minRango", Collections.min(rsRangoPrecio.getValues()));
                filtro.putFloat("maxRango", Collections.max(rsRangoPrecio.getValues()));

                Navigation.findNavController(view).navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment, filtro);
            };
        });
        bLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ciudad a NULL
                tbWifi.setChecked(true);
                etPersonas.setText("");
                cantidadP=-1;
                btgTipo.check(binding.tb3.getId());
                rsRangoPrecio.setValueFrom(1000);//hardcoded
                rsRangoPrecio.setValueTo(50000);//hardcoded
                //rsRangoPrecio.setValues("@array/initial_slider_values"); //HACERLO ANDAR
            }
        });
        tbWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btgTipo.getCheckedButtonId()!=binding.tb2.getId()){
                    tbWifi.setChecked(true);
                }
            }
        });
        btgTipo.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if(btgTipo.getCheckedButtonId()!=binding.tb2.getId()){
                    tbWifi.setChecked(true);
                }
            }
        });







        return view;
    }
}