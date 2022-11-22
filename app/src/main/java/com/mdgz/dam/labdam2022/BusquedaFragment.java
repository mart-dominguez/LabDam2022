package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.repo.CiudadRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusquedaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusquedaFragment extends Fragment {

    private FragmentBusquedaBinding binding;
    final String [] tiposAlojamientos=new String[]{"Departamento", "Hotel", "Todos"};
    final List<Ciudad> ciudades = CiudadRepository._CIUDADES;



    public static BusquedaFragment newInstance() {
        BusquedaFragment fragment = new BusquedaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentBusquedaBinding.inflate(inflater, container,  false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<String> adapterAlojamientos = new ArrayAdapter<String>(binding.getRoot().getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                tiposAlojamientos);
        binding.spinnerTipoHospedaje.setAdapter(adapterAlojamientos);
        ArrayAdapter<Ciudad> adapterCiudades = new ArrayAdapter<Ciudad>(binding.getRoot().getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                ciudades);
        binding.spinnerCiudad.setAdapter(adapterCiudades);
        binding.botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Bundle filtrosBusqueda = new Bundle();
            String  tipoHospedaje = binding.spinnerTipoHospedaje.getSelectedItem().toString();
            Integer huespedes;
            if(binding.cantidadPersonas.getText().toString().isEmpty())huespedes=0;
            else  huespedes = Integer.parseInt(binding.cantidadPersonas.getText().toString());
            Boolean wifi = binding.wifi.isChecked();
            verificarRangoPrecio();
            Float precioMax;
            if(binding.precioMaximo.getText().toString().isEmpty())precioMax=100000.0f;
            else precioMax = Float.parseFloat(binding.precioMaximo.getText().toString());
            Float precioMin;
            if(binding.precioMinimo.getText().toString().isEmpty()) precioMin=0.0f;
            else precioMin = Float.parseFloat(binding.precioMinimo.getText().toString());
            Ciudad destino = (Ciudad) binding.spinnerCiudad.getSelectedItem();
            filtrosBusqueda.putString("tipoHospedaje", tipoHospedaje);
            filtrosBusqueda.putInt("cantidadHuespedes", huespedes);
            filtrosBusqueda.putBoolean("wifi", wifi);
            filtrosBusqueda.putFloat("precioMax", precioMax);
            filtrosBusqueda.putFloat("precioMin", precioMin);
            filtrosBusqueda.putInt("idCiudad",destino.getId());
            NavHostFragment.findNavController(BusquedaFragment.this).navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment,filtrosBusqueda);
            }
        });
        binding.botonLimpiarFiltros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarfiltros();
            }
        });
    }
    private void verificarRangoPrecio(){
        String maximo = binding.precioMaximo.getText().toString();
        String minimo = binding.precioMinimo.getText().toString();

        if(!maximo.isEmpty() && !minimo.isEmpty()){
            if (Double.parseDouble(minimo) > Double.parseDouble(maximo)) {
                binding.precioMaximo.setText(minimo);
                binding.precioMinimo.setText(maximo);

            }
            return;
        }
    }
    private void limpiarfiltros(){
        binding.spinnerTipoHospedaje.setSelection(0);
        binding.spinnerCiudad.setSelection(0);
        binding.cantidadPersonas.setText("");
        if(binding.wifi.isChecked()) binding.wifi.toggle();
        binding.precioMinimo.setText("");
        binding.precioMaximo.setText("");
    }
}