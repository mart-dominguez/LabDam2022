package com.mdgz.dam.labdam2022;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.utilities.AlojamientoAdapter;
import com.mdgz.dam.labdam2022.utilities.ListaDeAlojamientos;
import com.mdgz.dam.labdam2022.utilities.JSONLogs;
import com.mdgz.dam.labdam2022.utilities.Utilities;
import com.mdgz.dam.labdam2022.viewmodels.LogViewModel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;


public class ResultadoBusquedaFragment extends Fragment {

    private FragmentResultadoBusquedaBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton btnBuscar;

    //Clase donde se crea la lista de alojamientos genericos
    private ListaDeAlojamientos listaDeAlojamientos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResultadoBusquedaBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);

        //RecyclerView
        recyclerView = binding.recyclerView;

        listaDeAlojamientos = new ListaDeAlojamientos();

        mAdapter = new AlojamientoAdapter(listaDeAlojamientos.getLista(),getActivity());
        recyclerView.setAdapter(mAdapter);


        SharedPreferences pref = getContext().getSharedPreferences("com.mdgz.dam.labdam2022_preferences",0);
        LogViewModel logViewModel = new ViewModelProvider(getActivity()).get(LogViewModel.class);

        //Preguntar si la preferencia esta activada
        if(logViewModel.isGuardado())
        {
            LogViewModel.SearchLog log = logViewModel.getLog();
            LocalDateTime now = Utilities.nowArgentina();
            long ms = ChronoUnit.MILLIS.between(log.getTimestamp(),now);   //Tiempo de navegacion entre los 2 fragmentos
            log.setTiempo_busqueda(ms + " milliseconds");
            log.setCant_resultados(listaDeAlojamientos.getLista().size());
            logViewModel.guardar(getContext());
            logViewModel.setGuardado(false);
        }

        //FloatingButton
        btnBuscar = binding.btnNuevaBusqueda;
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = ResultadoBusquedaFragmentDirections.actionResultadoBusquedaFragmentToBusquedaFragment();
                Navigation.findNavController(v).navigate(action);
            }
        });

    }

}