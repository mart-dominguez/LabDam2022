package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgz.dam.labdam2022.databinding.FragmentDetalleLogsBinding;
import com.mdgz.dam.labdam2022.utilities.JSONLogs;
import com.mdgz.dam.labdam2022.viewmodels.LogViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class DetalleLogs extends Fragment {

    FragmentDetalleLogsBinding binding;
    JSONLogs manejoLogs;
    LogViewModel logViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentDetalleLogsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        logViewModel = new ViewModelProvider(getActivity()).get(LogViewModel.class);

        manejoLogs = new JSONLogs(getContext());
        String json = manejoLogs.readFromFile();
        if(!json.isEmpty()) mostrarLogs(json);
        else binding.detalleLogs.setText("No hay historial de búsqueda reciente");

    }

    public void mostrarLogs(String json)
    {
        try
        {
            JSONArray datos = (JSONArray) new JSONTokener(json).nextValue();
            StringBuilder logs = new StringBuilder();

            for(int i = 0 ; i < datos.length(); i++)
            {
                JSONObject fila = datos.getJSONObject(i);

                LogViewModel.SearchLog newLog = logViewModel.loadFromJSON(fila);
                //Mostrar los datos del view model

                String hotel = newLog.isHotel() ? "Si" : "No";
                String dpto =  newLog.isDpto() ? "Si" : "No";
                String wifi = newLog.isWifi() ? "Si" : "No";

                logs.append("Fecha y hora: ").append(newLog.getTimestamp()).append("\n");
                logs.append("Ciudad: ").append(newLog.getCiudad()).append("\n");
                logs.append("Departamento: ").append(dpto).append("\n");
                logs.append("Hotel: ").append(hotel).append("\n");
                logs.append("Wi-Fi: ").append(wifi).append("\n");
                logs.append("Valor máximo: ").append(newLog.getVal_max()).append("\n");
                logs.append("Valor mínimo: ").append(newLog.getVal_min()).append("\n");
                logs.append("Cantidad de ocupantes: ").append(newLog.getCant_ocupantes()).append("\n");
                logs.append("Cantidad de resultados: ").append(newLog.getCant_resultados()).append("\n");
                logs.append("Tiempo de búsqueda: ").append(newLog.getTiempo_busqueda()).append("\n");
                logs.append("-------------").append("\n");

                binding.detalleLogs.setText(logs.toString());
            }
        } catch (JSONException e) {e.printStackTrace();}

    }

}