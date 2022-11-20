package com.mdgz.dam.labdam2022.persistencia.retrofit.impl;

import android.util.Log;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.interfaces.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.retrofit.apirest.RetrofitBD;
import com.mdgz.dam.labdam2022.persistencia.retrofit.daos.FavoritoDao;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entidades.FavoritoEntity;
import com.mdgz.dam.labdam2022.persistencia.retrofit.mapeadores.FavoritoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritoRetrofitDataSource implements FavoritoDataSource {

    private FavoritoDao favoritoDao;

    public FavoritoRetrofitDataSource()
    {
        RetrofitBD bd = RetrofitBD.getInstance();
        favoritoDao = bd.favoritoDao();
    }

    @Override
    public void guardar(Favorito entidad, GuardarCallback callback) {

        Call<FavoritoEntity> call = favoritoDao.insertar(FavoritoMapper.toEntity(entidad));
        call.enqueue(new Callback<FavoritoEntity>() {
            @Override
            public void onResponse(Call<FavoritoEntity> call, Response<FavoritoEntity> response) {
                if(response.isSuccessful()) callback.resultado(true);
                else callback.resultado(false);
            }

            @Override
            public void onFailure(Call<FavoritoEntity> call, Throwable t) {
                callback.resultado(false);
            }
        });
    }

    @Override
    public void getTodos(RecuperarCallback callback) {

        Call<List<FavoritoEntity>> call = favoritoDao.getAll();
        call.enqueue(new Callback<List<FavoritoEntity>>() {
            @Override
            public void onResponse(Call<List<FavoritoEntity>> call, Response<List<FavoritoEntity>> response) {
                if(response.isSuccessful()){
                    List<FavoritoEntity> lista = response.body();
                    // Pasar todos los entities a model
                    List<Favorito> favoritos = new ArrayList<>();
                    for (FavoritoEntity favoritoEntity:lista) {
                        favoritos.add(FavoritoMapper.toModel(favoritoEntity));
                    }
                    callback.resultado(true, favoritos);
                }
                else callback.resultado(false, null);

            }

            @Override
            public void onFailure(Call<List<FavoritoEntity>> call, Throwable t) {
                callback.resultado(false, null);
            }
        });

    }

    @Override
    public void getById(UUID id, GetByIDCallback callback) {
        //La api no admite obtener un favorito por su id
        Log.e("API:","No admite obtener un favorito por su ID.");
    }

    @Override
    // -- Obtener los favoritos de un usuario --
    public void getFromUser(UUID id, RecuperarCallback callback) {

        Call<List<FavoritoEntity>> call = favoritoDao.getFromUser(id);
        call.enqueue(new Callback<List<FavoritoEntity>>() {
            @Override
            public void onResponse(Call<List<FavoritoEntity>> call, Response<List<FavoritoEntity>> response) {
                if(response.isSuccessful()){
                    List<FavoritoEntity> lista = response.body();
                    // Pasar todos los entities a model
                    List<Favorito> favoritos = new ArrayList<>();
                    for (FavoritoEntity favoritoEntity:lista) {
                        favoritos.add(FavoritoMapper.toModel(favoritoEntity));
                    }
                    callback.resultado(true, favoritos);
                }
                else callback.resultado(false, null);
            }

            @Override
            public void onFailure(Call<List<FavoritoEntity>> call, Throwable t) {
                callback.resultado(false, null);
            }
        });

    }

    @Override
    public void eliminar(Favorito favorito, EliminarCallback callback) {

        Call<ResponseBody> call = favoritoDao.eliminar(favorito.getAlojamiento().getId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) callback.resultado(true);
                else callback.resultado(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.resultado(false);
            }
        });

    }
}
