package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.model.BusquedaDTO;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.repo.CiudadRepository;

import java.io.File;

public class BusquedaFragment extends Fragment {

    private Spinner tipoAlojamiento, cantOcupantes, ciudades;
    private CheckBox wifi;
    private EditText precioMinimo, precioMaximo;
    private Button btnBuscar, btnLimpiar;
    private FragmentBusquedaBinding binding;


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
        binding = FragmentBusquedaBinding.inflate(inflater, container, false);
        CiudadRepository repoCiudad = new CiudadRepository();
        ciudades = binding.spinnerCiudad;
        ArrayAdapter<Ciudad> adapter = new ArrayAdapter<Ciudad>(getActivity(), android.R.layout.simple_spinner_item, repoCiudad.listaCiudades());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ciudades.setAdapter(adapter);
        btnBuscar = binding.btnBuscar;
        btnBuscar.setOnClickListener(e -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity()); // "this" es el contexto
            Boolean detalles_uso = prefs.getBoolean("autorizar", false);
            Bundle bundle = new Bundle();
            if(!TextUtils.isEmpty(binding.precioMinimo.getText().toString()) && !TextUtils.isEmpty(binding.precioMaximo.getText().toString())) {
                if (detalles_uso) {
                    BusquedaDTO busq = new BusquedaDTO();
                    busq.setTipoAlojamiento(binding.spinnerTipoAlojamiento.getSelectedItem().toString());
                    busq.setCantOcupantes(Integer.valueOf(binding.spinnerCantOcupantes.getSelectedItem().toString()));
                    busq.setWifi(binding.chkWifi.isChecked());
                    busq.setPrecioMin(Double.valueOf(binding.precioMinimo.getText().toString()));
                    busq.setPrecioMax(Double.valueOf(binding.precioMaximo.getText().toString()));
                    busq.setCiudad(binding.spinnerCiudad.getSelectedItem().toString());
                    busq.setTimestampInicio(System.currentTimeMillis());
                    bundle.putSerializable("busqueda", busq);
            } else { //si no esta habilitado elimino el archivo
                String filename = "busquedas.json";
                File file = new File(getContext().getFilesDir(), filename);
                if(file.exists()) {
                    file.delete();
                }
            }
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment, bundle);
            } else {
                Toast.makeText(getContext(), "Completa los precios minimos y maximos", Toast.LENGTH_LONG).show();
            }

        });
        return binding.getRoot();
    }



}