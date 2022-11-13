package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgz.dam.labdam2022.databinding.FragmentReservasBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.repositorios.ReservaRepository;
import com.mdgz.dam.labdam2022.utilities.AlojamientoAdapter;
import com.mdgz.dam.labdam2022.utilities.ReservaAdapter;

import java.util.List;


public class ReservasFragment extends Fragment
{

    private FragmentReservasBinding binding;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReservasBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = binding.frRecyclerView;
        ReservaAdapter adapter = new ReservaAdapter();
        recyclerView.setAdapter(adapter);
        ReservaRepository reservaRepository = ReservaRepository.getInstance();
        reservaRepository.getTodas((exito, resultados) ->
        {
            if(exito) adapter.setData(resultados);
        });

    }
}