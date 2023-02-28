package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgz.dam.labdam2022.database.FavoritoDataSource;
import com.mdgz.dam.labdam2022.database.retrofit.FavoritoRetrofitDataSource;
import com.mdgz.dam.labdam2022.databinding.FragmentVerFavoritosBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;

import java.util.ArrayList;
import java.util.List;

public class VerFavoritosFragment extends Fragment {

    FragmentVerFavoritosBinding binding;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVerFavoritosBinding.inflate(inflater, container, false);

        //FavoritoRoomDataSource favoritoRoomDataSource = new FavoritoRoomDataSource(getContext());
        FavoritoRetrofitDataSource favoritoRetrofitDataSource = new FavoritoRetrofitDataSource();

        binding.recyclerFavoritos.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerFavoritos.setLayoutManager(layoutManager);
        FavoritoDataSource.RecuperarFavoritoCallback callback = (exito, resultados) -> {
            if (exito) {
                List<Alojamiento> alojamientos = new ArrayList<>();
                resultados.forEach(resultado -> {
                    resultado.transformarAAlojamiento(getContext());
                    alojamientos.add(resultado.getAlojamiento());
                });

                System.out.println("RESULTADOS " + resultados);

                RecyclerView.Adapter mAdapter = new AlojamientoRecyclerAdapter(getContext(), alojamientos, false);
                binding.recyclerFavoritos.setAdapter(mAdapter);
            }
        };

        //favoritoRoomDataSource.recuperarFavorito(callback);
        favoritoRetrofitDataSource.recuperarFavorito(callback);

        return binding.getRoot();
    }

}