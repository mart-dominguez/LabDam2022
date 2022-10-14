package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.utilities.AlojamientoAdapter;
import com.mdgz.dam.labdam2022.utilities.ListaDeAlojamientos;
import com.mdgz.dam.labdam2022.utilities.ManejoLogs;
import com.mdgz.dam.labdam2022.utilities.Utilities;
import com.mdgz.dam.labdam2022.viewmodels.LogViewModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        //RecyclerView
        recyclerView = binding.recyclerView;

        listaDeAlojamientos = new ListaDeAlojamientos();

        mAdapter = new AlojamientoAdapter(listaDeAlojamientos.getLista());
        recyclerView.setAdapter(mAdapter);

        //--- Manejo de LOG de busqueda
        LogViewModel logViewModel = new ViewModelProvider(getActivity()).get(LogViewModel.class);
        // Si todavia no se guardo el log:
        if(!logViewModel.isGuardado()){

            // Seteo cantidad de resultados y tiempo de busqueda
            logViewModel.setCant_resultados(mAdapter.getItemCount());
            long dif = Utilities.getDateDiff(logViewModel.getTimestamp(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar
                    .getInstance().getTime()));
            logViewModel.setTiempo_busqueda(String.valueOf(dif));

            // Llamar al metodo para guardar el log
            ManejoLogs manejoLogs = new ManejoLogs(getContext());
            manejoLogs.escribirEnArchivo(logViewModel.toJSON());

            logViewModel.setGuardado(true);

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