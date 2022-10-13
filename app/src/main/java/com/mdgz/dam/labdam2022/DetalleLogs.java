package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgz.dam.labdam2022.databinding.FragmentDetalleLogsBinding;
import com.mdgz.dam.labdam2022.utilities.ManejoLogs;
import com.mdgz.dam.labdam2022.viewmodels.LogViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class DetalleLogs extends Fragment {

    FragmentDetalleLogsBinding binding;
    ManejoLogs manejoLogs;
    LogViewModel logViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetalleLogsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logViewModel = new ViewModelProvider(getActivity()).get(LogViewModel.class);
        manejoLogs = new ManejoLogs(getContext());

        mostrarLogs(manejoLogs.leerDeArchivo());

    }

    public void mostrarLogs(String json){

        try {
            JSONArray datos = (JSONArray) new JSONTokener(json).nextValue();
            for(int i=0;i<datos.length();i++){
                JSONObject fila = datos.getJSONObject(i);
                logViewModel.loadFromJSON(fila);

                //Mostrar los datos del view model

                String hotel = logViewModel.isHotel() ? "Si" : "No";
                String dpto =  logViewModel.isDpto() ? "Si" : "No";
                String wifi = logViewModel.isWifi() ? "Si" : "No";

                binding.detalleLogs.setText( binding.detalleLogs.getText() +
                        "\n" +
                        "Fecha y hora: " + logViewModel.getTimestamp() + "\n" +
                        "Ciudad: " + logViewModel.getCiudad() + "\n" +
                        "Departamento: " + dpto + "\n" +
                        "Hotel: " + hotel + "\n" +
                        "Wifi: " + wifi + "\n" +
                        "Valor minimo: " + logViewModel.getVal_min() + "\n" +
                        "Valor maximo: " + logViewModel.getVal_max() + "\n" +
                        "Cantidad de ocupantes: " + logViewModel.getCant_ocupantes() + "\n" +
                        "Cantidad de resultados: " + logViewModel.getCant_resultados() + "\n" +
                        "Tiempo de búsqueda: " + logViewModel.getTiempo_busqueda() + " ms." + "\n"
                        );

            }
        } catch (JSONException e) {e.printStackTrace();}

    }

}