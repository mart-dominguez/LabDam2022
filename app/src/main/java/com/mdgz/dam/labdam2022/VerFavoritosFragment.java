package com.mdgz.dam.labdam2022;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdgz.dam.labdam2022.database.FavoritoDataSource;
import com.mdgz.dam.labdam2022.database.room.FavoritoRoomDataSource;
import com.mdgz.dam.labdam2022.databinding.FilaAlojamientoRecyclerBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentVerFavoritosBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.BusquedaDTO;
import com.mdgz.dam.labdam2022.model.Favorito;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VerFavoritosFragment extends Fragment {

    FragmentVerFavoritosBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVerFavoritosBinding.inflate(inflater, container, false);

        FavoritoRoomDataSource favoritoRoomDataSource = new FavoritoRoomDataSource(getContext());

        FavoritoDataSource.RecuperarFavoritoCallback callback = (exito, resultados) -> {
            if (exito) {
                List<Alojamiento> alojamientos = new ArrayList<>();
                resultados.forEach(resultado -> alojamientos.add(resultado.getAlojamiento()));

                System.out.println("R" + resultados);

                RecyclerView.Adapter mAdapter = new AlojamientoRecyclerAdapter(getContext(), alojamientos);
                binding.recyclerFavoritos.setAdapter(mAdapter);
            }
        };

        favoritoRoomDataSource.recuperarFavorito(callback);

        return binding.getRoot();
    }

}