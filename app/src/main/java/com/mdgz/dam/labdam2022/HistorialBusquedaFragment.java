package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgz.dam.labdam2022.databinding.FragmentHistorialBusquedaBinding;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HistorialBusquedaFragment extends Fragment {

    FragmentHistorialBusquedaBinding binding;

    public HistorialBusquedaFragment() {
        // Required empty public constructor
    }


    public static HistorialBusquedaFragment newInstance(String param1, String param2) {
        HistorialBusquedaFragment fragment = new HistorialBusquedaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistorialBusquedaBinding.inflate(inflater, container,  false);
        binding.textoHistorial.setText("");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cargarHistorial();
    }

    private void cargarHistorial() {
        binding.textoHistorial.setText("");
        try {
            FileInputStream fis = getActivity().openFileInput("datos_uso_app");
            byte[] data = new byte[256];
            StringBuilder sb = new StringBuilder();
            while (fis.read(data) !=-1){
                sb.append(new String(data));
            }
            binding.textoHistorial.setText(sb.toString());
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}