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
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.repositorios.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repositorios.FavoritoRepository;
import com.mdgz.dam.labdam2022.utilities.AlojamientoAdapter;
import com.mdgz.dam.labdam2022.persistencia.DatosIniciales;
import com.mdgz.dam.labdam2022.utilities.Utilities;
import com.mdgz.dam.labdam2022.viewmodels.LogViewModel;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class ResultadoBusquedaFragment extends Fragment {

    private FragmentResultadoBusquedaBinding binding;
    private LogViewModel logViewModel;
    private RecyclerView recyclerView;
    private AlojamientoAdapter mAdapter;
    private FloatingActionButton btnBuscar;

    //Clase donde se crea la lista de alojamientos genericos
    private DatosIniciales listaDeAlojamientos;


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
        mAdapter = new AlojamientoAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);

        // BUSCAR LISTA DE ALOJAMIENTOS DESDE LA BD
        buscarDatos();

        //SharedPreferences pref = getContext().getSharedPreferences("com.mdgz.dam.labdam2022_preferences",0);
        logViewModel = new ViewModelProvider(getActivity()).get(LogViewModel.class);

        //Preguntar si la preferencia esta activada
        guardarLog();

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

    private void buscarDatos()
    {
        AlojamientoRepository alojamientoRepository = AlojamientoRepository.getInstance(getContext());
        FavoritoRepository favoritoRepository = FavoritoRepository.getInstance(getContext());
        List<Alojamiento> alojamientos = new ArrayList<>();
        List<Favorito> favoritos = new ArrayList<>();

        alojamientoRepository.getTodasHabitaciones((exito, resultados) ->
        {
            if(exito) alojamientos.addAll(resultados);
        });
        alojamientoRepository.getTodosDepartamentos((exito, resultados) ->
        {
            if(exito) alojamientos.addAll(resultados);
        });
        favoritoRepository.getTodos((exito, resultados) ->
        {
            if(exito) favoritos.addAll(resultados);
        });

        mAdapter.setData(alojamientos,favoritos);
    }

    private void guardarLog()
    {
        if(logViewModel.isGuardado())
        {
            LogViewModel.SearchLog log = logViewModel.getLog();
            LocalDateTime now = Utilities.nowArgentina();
            long ms = ChronoUnit.MILLIS.between(log.getTimestamp(),now);   //Tiempo de navegacion entre los 2 fragmentos
            log.setTiempo_busqueda(ms + " milliseconds");
            //log.setCant_resultados(listaDeAlojamientos.getLista().size());
            log.setCant_resultados(mAdapter.getItemCount());
            logViewModel.guardar(getContext());
            logViewModel.setGuardado(false);
        }
    }

}