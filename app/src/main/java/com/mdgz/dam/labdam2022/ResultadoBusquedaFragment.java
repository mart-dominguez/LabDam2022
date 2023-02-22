package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mdgz.dam.labdam2022.adaptadoresRV.AlojamientosRecyclerAdapter;
import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;

import java.util.List;


public class ResultadoBusquedaFragment extends Fragment {

    private FragmentResultadoBusquedaBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter rvAdapter;

    private final List<Alojamiento> alojamientos = AlojamientoRepository._ALOJAMIENTOS;
    public ResultadoBusquedaFragment() {
    }

    public static ResultadoBusquedaFragment newInstance() {
        ResultadoBusquedaFragment fragment = new ResultadoBusquedaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentResultadoBusquedaBinding.inflate(inflater, container, false);

        recyclerView= (RecyclerView) binding.recyclerAlojamientos;
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        rvAdapter= new AlojamientosRecyclerAdapter(alojamientos);
        recyclerView.setAdapter(rvAdapter);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.botonNuevaBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Nueva Búsqueda", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(ResultadoBusquedaFragment.this).navigate(R.id.action_resultadoBusquedaFragment_to_busquedaFragment);

            }
        });

    }

}